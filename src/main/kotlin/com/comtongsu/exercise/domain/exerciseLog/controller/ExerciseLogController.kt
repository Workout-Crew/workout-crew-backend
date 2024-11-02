package com.comtongsu.exercise.domain.exerciseLog.controller

import com.comtongsu.exercise.domain.exerciseLog.dto.request.ExerciseLogRequestDto
import com.comtongsu.exercise.domain.exerciseLog.service.ExerciseLogService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/exerciselog")
@Tag(name = "Exercise Log", description = "EXERCISE LOG API")
class ExerciseLogController(private val exerciseLogService: ExerciseLogService) {

    @PostMapping
    fun createExerciseLog(
            @RequestHeader accessToken: String,
            @RequestPart(value = "image") imageList: List<MultipartFile>?,
            @RequestBody request: ExerciseLogRequestDto.ExerciseLogRequest
    ): ResponseEntity<Unit> {
        exerciseLogService.createExerciseLog(accessToken, imageList, request)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }
}
