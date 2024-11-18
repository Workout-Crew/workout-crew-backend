package com.comtongsu.exercise.domain.account.dto.response

import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex

class KakaoResponseDto {
    data class KakaoResponse(
            val id: String,
            val nickname: String?,
            val sex: Sex?,
            val height: Int?,
            val weight: Int?,
            val age: Int?,
            val goal: Goal?,
    )
}
