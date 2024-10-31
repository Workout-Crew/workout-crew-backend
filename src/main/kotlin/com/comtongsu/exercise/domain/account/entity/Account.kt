package com.comtongsu.exercise.domain.account.entity

import com.comtongsu.exercise.domain.account.entity.enums.Goal
import com.comtongsu.exercise.domain.account.entity.enums.Sex
import com.comtongsu.exercise.domain.board.entity.Comment
import com.comtongsu.exercise.domain.gathering.AccountGathering
import com.comtongsu.exercise.domain.medal.entity.AccountMedal
import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(
        @Id var id: Long = 0,
        var nickname: String? = null,
        var height: Int? = null,
        var weight: Int? = null,
        @Enumerated(EnumType.STRING) var sex: Sex? = null,
        @Column(name = "body_fat") var bodyFat: Int? = null,
        @Column(name = "muscle_percentage") var musclePercentage: Int? = null,
        @Enumerated(EnumType.STRING) var goal: Goal? = null,
        @Column(name = "goal_percentage") var goalPercentage: Int = 0,
        @OneToMany(mappedBy = "account") var medalList: MutableList<AccountMedal> = mutableListOf(),
        @OneToMany(mappedBy = "account") var commentList: MutableList<Comment> = mutableListOf(),
        @OneToMany(mappedBy = "account")
        var gatheringList: MutableList<AccountGathering> = mutableListOf(),
) {
    companion object {
        fun createAccount(id: Long): Account {
            return Account(id = id)
        }
    }
}
