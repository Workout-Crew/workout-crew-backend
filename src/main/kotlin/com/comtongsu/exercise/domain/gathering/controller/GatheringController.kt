package com.comtongsu.exercise.domain.gathering.controller

import com.comtongsu.exercise.domain.gathering.dto.request.GatheringRequestDto
import com.comtongsu.exercise.domain.gathering.dto.response.GatheringResponseDto
import com.comtongsu.exercise.domain.gathering.service.GatheringService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/gathering")
@Tag(name = "Gathering", description = "모임 API")
class GatheringController(private val gatheringService: GatheringService) {
    @Operation(summary = "모임 생성")
    @PostMapping
    fun createGathering(
            @RequestHeader token: String,
            @RequestBody request: GatheringRequestDto.GatheringRequest
    ): ResponseEntity<Unit> {
        gatheringService.createGathering(token, request)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "모임 참가 신청")
    @PostMapping("/join")
    fun joinGathering(
            @RequestHeader token: String,
            @RequestBody request: GatheringRequestDto.GatheringJoinRequest
    ): ResponseEntity<Unit> {
        gatheringService.joinGathering(token, request)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @Operation(summary = "모임 상세 조회")
    @GetMapping("/detail/{gatheringId}")
    fun getGatheringDetail(
            @RequestHeader token: String,
            @PathVariable gatheringId: Long,
    ): ResponseEntity<GatheringResponseDto.GatheringDetailResponse> {
        return ResponseEntity(gatheringService.getGatheringDetail(token, gatheringId), HttpStatus.OK)
    }
}
