package com.comtongsu.exercise.domain.gathering.entity

import com.comtongsu.exercise.domain.account.entity.Account
import jakarta.persistence.*

@Entity
@Table(name = "account_gathering")
class AccountGathering(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "account_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var account: Account? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "gathering_id",
                nullable = false,
                foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
        var gathering: Gathering? = null,
        @Column(name = "is_leader") var isLeader: Boolean? = null,
) {
    companion object {
        fun createAccountGathering(
                account: Account,
                gathering: Gathering,
                isLeader: Boolean
        ): AccountGathering {
            return AccountGathering(account = account, gathering = gathering, isLeader = isLeader)
        }
    }
}
