package com.comtongsu.exercise.domain.account.dto.response

import java.io.Serializable

class AccountResponseDto {
    data class AccountForRecommendation(
            val height: Int?,
            val weight: Int?,
            val recType: String,
    ) : Serializable
}
