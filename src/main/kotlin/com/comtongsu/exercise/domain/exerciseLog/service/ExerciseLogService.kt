package com.comtongsu.exercise.domain.exerciseLog.service

import com.comtongsu.exercise.domain.account.service.KakaoService
import com.comtongsu.exercise.domain.exerciseLog.dto.request.ExerciseLogRequestDto
import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLog
import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLogImage
import com.comtongsu.exercise.domain.exerciseLog.repository.ExerciseLogImageRepository
import com.comtongsu.exercise.domain.exerciseLog.repository.ExerciseLogRepository
import com.comtongsu.exercise.global.s3.S3ImageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class ExerciseLogService(
        private val kakaoService: KakaoService,
        private val exerciseLogRepository: ExerciseLogRepository,
        private val exerciseLogImageRepository: ExerciseLogImageRepository,
        private val s3ImageService: S3ImageService,
) {

    @Transactional
    fun createExerciseLog(
            accessToken: String,
            imageList: List<MultipartFile>?,
            request: ExerciseLogRequestDto.ExerciseLogRequest
    ) {
        val account = kakaoService.getAccountFromAccessToken(accessToken)

        val exerciseLog = ExerciseLog.createExerciseLog(account, request)
        exerciseLogRepository.save(exerciseLog)

        imageList?.forEach {
            val imageUrl: String = s3ImageService.uploadImageToS3(it)
            exerciseLogImageRepository.save(
                    ExerciseLogImage.createExerciseLogImage(exerciseLog, imageUrl))
        }
    }
}
