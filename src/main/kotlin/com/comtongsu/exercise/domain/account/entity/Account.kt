package com.comtongsu.exercise.domain.account.entity

import com.comtongsu.exercise.domain.account.dto.request.AccountRequestDto
import com.comtongsu.exercise.domain.account.dto.response.KakaoResponseDto
import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex
import com.comtongsu.exercise.domain.board.entity.Comment
import com.comtongsu.exercise.domain.exerciseRecommendation.entity.ExerciseRecommendation
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.medal.entity.AccountMedal
import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(
        @Id @Column(length = 64) var id: String = "",
        var nickname: String? = null,
        var height: Int? = null,
        var weight: Int? = null,
        var age: Int? = null,
        @Enumerated(EnumType.STRING) var sex: Sex? = null,
        @Column(name = "body_fat") var bodyFat: Int? = null,
        @Column(name = "muscle_percentage") var musclePercentage: Int? = null,
        @Enumerated(EnumType.STRING) var goal: Goal? = null,
        @OneToMany(mappedBy = "account")
        var exerciseRecommendationList: MutableList<ExerciseRecommendation> = mutableListOf(),
        @OneToMany(mappedBy = "account") var medalList: MutableList<AccountMedal> = mutableListOf(),
        @OneToMany(mappedBy = "account") var commentList: MutableList<Comment> = mutableListOf(),
        @OneToMany(mappedBy = "account")
        var gatheringList: MutableList<AccountGathering> = mutableListOf(),
) {
    companion object {
        fun createAccount(id: String): Account {
            return Account(id = id)
        }
    }

    fun updateAccountInfo(request: AccountRequestDto.AccountRequest) {
        sex = request.sex
        age = request.age
        weight = request.weight
        height = request.height
    }

    fun updateAccountGoal(request: AccountRequestDto.AccountGoalRequest) {
        goal = request.goal
    }

    fun updateAccountNickname(request: AccountRequestDto.AccountNicknameRequest) {
        nickname = request.nickname
    }

    fun toKakaoResponse(): KakaoResponseDto.KakaoResponse {
        return KakaoResponseDto.KakaoResponse(
                id = id,
                nickname = nickname,
                sex = sex,
                height = height,
                weight = weight,
                age = age,
                goal = goal)
    }
}
