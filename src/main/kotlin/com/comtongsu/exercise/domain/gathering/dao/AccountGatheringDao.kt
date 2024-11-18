package com.comtongsu.exercise.domain.gathering.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.gathering.entity.QAccountGathering.accountGathering
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AccountGatheringDao(private val queryFactory: JPAQueryFactory) {
    fun getMyAccountGathering(account: Account, isLeader: Boolean): List<AccountGathering> {
        return queryFactory
                .selectFrom(accountGathering)
                .where(accountGathering.account.eq(account).and(accountGathering.isLeader.eq(isLeader)))
                .fetch()
    }
}
