package com.comtongsu.exercise.domain.exerciseLog.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.exerciseLog.dao.ExerciseLogDao
import com.comtongsu.exercise.domain.exerciseLog.dto.request.ExerciseLogRequestDto
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLog
import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLogImage
import com.comtongsu.exercise.domain.exerciseLog.repository.ExerciseLogImageRepository
import com.comtongsu.exercise.domain.exerciseLog.repository.ExerciseLogRepository
import com.comtongsu.exercise.domain.gathering.service.GatheringService
import com.comtongsu.exercise.global.s3.S3ImageService
import java.time.Duration
import java.time.LocalDate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class ExerciseLogService(
        private val exerciseLogDao: ExerciseLogDao,
        private val exerciseLogRepository: ExerciseLogRepository,
        private val exerciseLogImageRepository: ExerciseLogImageRepository,
        private val kakaoService: KakaoService,
        private val s3ImageService: S3ImageService,
        private val gatheringService: GatheringService
) {

    @Transactional
    fun createExerciseLog(
            accessToken: String,
            imageList: List<MultipartFile>?,
            request: ExerciseLogRequestDto.ExerciseLogRequest
    ) {
        val account = kakaoService.getAccountFromAccessToken(accessToken)
        val exerciseTime: Int = Duration.between(request.startTime, request.endTime).toHours().toInt()

        val exerciseLog =
                ExerciseLog.createExerciseLog(
                        account,
                        exerciseTime,
                        request.gatheringId?.let { gatheringService.getGatheringById(it) },
                        request)
        exerciseLogRepository.save(exerciseLog)

        imageList?.forEach {
            val imageUrl: String = s3ImageService.uploadImageToS3(it)
            exerciseLogImageRepository.save(
                    ExerciseLogImage.createExerciseLogImage(exerciseLog, imageUrl))
        }
    }

    fun getTotalExerciseLog(
            accessToken: String
    ): ExerciseLogResponseDto.TotalExerciseLogListResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)

        return ExerciseLogResponseDto.TotalExerciseLogListResponse(
                exerciseLogDao.getTotalExerciseLog(account))
    }

    fun getExerciseLogByDate(
            accessToken: String,
            currentDate: LocalDate
    ): ExerciseLogResponseDto.ExerciseLogByDateListResponse {
        val account = kakaoService.getAccountFromAccessToken(accessToken)

        return ExerciseLogResponseDto.ExerciseLogByDateListResponse(
                exerciseLogDao.getExerciseLogByDate(account, currentDate).map { it.toExerciseLogByDate() })
    }
}
