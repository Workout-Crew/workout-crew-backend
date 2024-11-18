package com.comtongsu.exercise.domain.medal.controller

import com.comtongsu.exercise.domain.medal.dto.response.MedalResponseDto
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

    @Operation(summary = "메달 성취도 조회")
    @GetMapping("/mission")
    fun getExerciseLogCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalMissionListResponse> {
        return ResponseEntity(medalService.getMedalMission(token), HttpStatus.OK)
    }

    @Operation(summary = "보유 메달 개수 조회")
    @GetMapping("/count")
    fun getMedalCount(
            @RequestHeader token: String,
    ): ResponseEntity<MedalResponseDto.MedalCountResponse> {
        return ResponseEntity(medalService.getMedalCount(token), HttpStatus.OK)
    }

    @Operation(summary = "전체(성취) 메달 조회")
    @GetMapping("/total")
    fun getTotalMedal(
            @RequestHeader token: String
    ): ResponseEntity<MedalResponseDto.TotalMedalResponse> {
        return ResponseEntity(medalService.getTotalMedal(token), HttpStatus.OK)
    }
}
