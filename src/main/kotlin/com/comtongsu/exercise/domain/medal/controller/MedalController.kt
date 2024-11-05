package com.comtongsu.exercise.domain.medal.controller

import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.service.MedalService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/medal")
class MedalController(private val medalService: MedalService) {

    @Operation(summary = "운동 기록 작성 횟수 조회")
    @RequestMapping("/exerciselog")
    fun getExerciseLogCount(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(medalService.getExerciseLogCount(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "모임 운동 작성 횟수 조회")
    @RequestMapping("/gathering_exerciselog")
    fun getGatheringExerciseLogCount(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(medalService.getGatheringExerciseLogCount(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "모임 개설 횟수 조회")
    @RequestMapping("/gathering")
    fun getGatheringCount(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(medalService.getGatheringCount(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "작성한 기록의 운동 종류 조회")
    @RequestMapping("/exercisetype")
    fun getExerciseLogTypeCount(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(medalService.getExerciseLogTypeCount(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "보유 메달 개수 조회")
    @RequestMapping("/count")
    fun getMedalCount(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.MedalCountResponse> {
        return ResponseEntity(medalService.getMedalCount(accessToken), HttpStatus.OK)
    }

    @Operation(summary = "보유 메달 조회")
    @RequestMapping("/total")
    fun getTotalMedal(
            @RequestHeader accessToken: String,
    ): ResponseEntity<MedalResponseDto.TotalMedalResponse> {
        return ResponseEntity(medalService.getTotalMedal(accessToken), HttpStatus.OK)
    }
}
