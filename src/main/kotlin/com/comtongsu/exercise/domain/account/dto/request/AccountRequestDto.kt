package com.comtongsu.exercise.domain.account.dto.request

import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

class AccountRequestDto {
    data class AccountRequest(
            @Schema(description = "성별", example = "[\"MALE\", \"FEMALE\"]") val sex: Sex,
            val age: Int,
            val height: Int,
            val weight: Int,
    )

    data class AccountGoalRequest
    @JsonCreator
    constructor(
            @Schema(description = "운동 목표", example = "[\"GAIN_MUSCLE\", \"LOSE_BODY_FAT\"]")
            @JsonProperty("goal")
            val goal: Goal
    )

    data class AccountNicknameRequest
    @JsonCreator
    constructor(@JsonProperty("nickname") val nickname: String)
}
