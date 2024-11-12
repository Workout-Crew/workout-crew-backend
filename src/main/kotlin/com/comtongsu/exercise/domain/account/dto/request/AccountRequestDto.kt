package com.comtongsu.exercise.domain.account.dto.request

import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex
import io.swagger.v3.oas.annotations.media.Schema

class AccountRequestDto {
    data class AccountRequest(
            @Schema(description = "성별", example = "[\"MALE\", \"FEMALE\"]") val sex: Sex,
            val age: Int,
            val height: Int,
            val weight: Int,
    )

    data class AccountGoalRequest(
            @Schema(description = "운동 목표", example = "[\"GAIN_MUSCLE\", \"LOSE_BODY_FAT\"]")
            val goal: Goal
    )

    data class AccountNicknameRequest(val nickname: String)
}
