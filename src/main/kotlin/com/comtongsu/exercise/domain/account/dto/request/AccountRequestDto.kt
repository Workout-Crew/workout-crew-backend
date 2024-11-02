package com.comtongsu.exercise.domain.account.dto.request

import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex

class AccountRequestDto {
    data class AccountRequest(
            val nickname: String,
            val sex: Sex,
            val age: Int,
            val height: Int,
            val weight: Int,
    )

    data class AccountGoalRequest(val goal: Goal)
}
