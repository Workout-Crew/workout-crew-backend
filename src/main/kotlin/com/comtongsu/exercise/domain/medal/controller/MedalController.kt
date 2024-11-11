package com.comtongsu.exercise.domain.medal.controller

import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import com.comtongsu.exercise.domain.medal.service.MedalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/medal")
@Tag(name = "Medal", description = "메달 API")
class MedalController(private val medalService: MedalService) {

    @Operation(summary = "운동 기록 작성 횟수 조회")
    @GetMapping("/exerciselog")
    fun getExerciseLogCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(
                medalService.getMedalMission(token, MedalType.WRITE_EXERCISE_LOG), HttpStatus.OK)
    }

    @Operation(summary = "모임 운동 작성 횟수 조회")
    @GetMapping("/gathering_exerciselog")
    fun getGatheringExerciseLogCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(
                medalService.getMedalMission(token, MedalType.WRITE_GATHERING_EXERCISE_LOG), HttpStatus.OK)
    }

    @Operation(summary = "모임 개설 횟수 조회")
    @GetMapping("/gathering")
    fun getGatheringCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(
                medalService.getMedalMission(token, MedalType.MAKE_GATHERING), HttpStatus.OK)
    }

    @Operation(summary = "작성한 기록의 운동 종류 조회")
    @GetMapping("/exercisetype")
    fun getExerciseLogTypeCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionResponse> {
        return ResponseEntity(
                medalService.getMedalMission(token, MedalType.WRITE_VARIOUS_EXERCISE_TYPE), HttpStatus.OK)
    }

    @Operation(summary = "보유 메달 개수 조회")
    @GetMapping("/count")
    fun getMedalCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalCountResponse> {
        return ResponseEntity(medalService.getMedalCount(token), HttpStatus.OK)
    }

    @Operation(summary = "보유 메달 조회")
    @GetMapping("/mine")
    fun getMyMedal(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MyMedalResponse> {
        return ResponseEntity(medalService.getMyMedal(token), HttpStatus.OK)
    }

    @Operation(summary = "전체 메달 조회")
    @GetMapping("/total")
    fun getTotalMedal(): ResponseEntity<MedalResponseDto.TotalMedalResponse> {
        return ResponseEntity(medalService.getTotalMedal(), HttpStatus.OK)
    }
}
