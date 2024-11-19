package com.comtongsu.exercise.domain.gathering.controller

import com.comtongsu.exercise.domain.gathering.dto.request.GatheringRequestDto
import com.comtongsu.exercise.domain.gathering.dto.response.GatheringResponseDto
import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.domain.gathering.service.GatheringService
import com.comtongsu.exercise.global.enums.ExerciseType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @Operation(summary = "모임 리스트 조회")
    @GetMapping
    fun getGatheringList(
            @RequestHeader token: String,
            @Parameter(required = false) @RequestParam(required = false) place: Place?,
            @Parameter(required = false) @RequestParam(required = false) exerciseType: ExerciseType?,
    ): ResponseEntity<GatheringResponseDto.GatheringListResponse> {
        return ResponseEntity(
                gatheringService.getGatheringList(token, place, exerciseType), HttpStatus.OK)
    }

    @Operation(summary = "참가 가능한 모임 조회")
    @GetMapping("/possible")
    fun getPossibleJoinGathering(
            @RequestHeader token: String,
    ): ResponseEntity<GatheringResponseDto.GatheringListResponse> {
        return ResponseEntity(gatheringService.getPossibleJoinGathering(token), HttpStatus.OK)
    }

    @Operation(summary = "내가 신청한 모임 조회")
    @GetMapping("/apply")
    fun getAppliedGathering(
            @RequestHeader token: String,
    ): ResponseEntity<GatheringResponseDto.GatheringListResponse> {
        return ResponseEntity(gatheringService.getAppliedGathering(token), HttpStatus.OK)
    }

    @Operation(summary = "내가 개설한 모임 조회")
    @GetMapping("/me")
    fun getMakedGathering(
            @RequestHeader token: String,
    ): ResponseEntity<GatheringResponseDto.MakedGatheringListResponse> {
        return ResponseEntity(gatheringService.getMakedGathering(token), HttpStatus.OK)
    }
}
