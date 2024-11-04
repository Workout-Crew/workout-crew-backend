package com.comtongsu.exercise.domain.exerciseLog.controller

import com.comtongsu.exercise.domain.exerciseLog.dto.request.ExerciseLogRequestDto
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.domain.exerciseLog.service.ExerciseLogService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.time.LocalDate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/exerciselog")
@Tag(name = "Exercise Log", description = "운동 기록 API")
class ExerciseLogController(private val exerciseLogService: ExerciseLogService) {

    @Operation(summary = "운동 기록 저장", description = "이미지와 저장 정보를 따로 받습니다. ")
    @PostMapping
    fun createExerciseLog(
            @RequestHeader accessToken: String,
            @RequestPart(value = "image") imageList: List<MultipartFile>?,
            @RequestBody request: ExerciseLogRequestDto.ExerciseLogRequest
    ): ResponseEntity<Unit> {
        exerciseLogService.createExerciseLog(accessToken, imageList, request)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "운동 현황 조회", description = "이번 달 운동 합계 현황을 조회합니다.")
    @GetMapping
    fun getTotalExerciseLog(
            @RequestHeader accessToken: String
    ): ResponseEntity<ExerciseLogResponseDto.TotalExerciseLogListResponse> {
        return ResponseEntity(exerciseLogService.getTotalExerciseLog(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "날짜별 운동 기록 조회")
    @GetMapping("/date/{currentDate}")
    fun getExerciseLogByDate(
            @RequestHeader accessToken: String,
            @PathVariable currentDate: LocalDate
    ): ResponseEntity<ExerciseLogResponseDto.ExerciseLogByDateListResponse> {
        return ResponseEntity(
                exerciseLogService.getExerciseLogByDate(accessToken, currentDate), HttpStatus.OK)
    }
}
