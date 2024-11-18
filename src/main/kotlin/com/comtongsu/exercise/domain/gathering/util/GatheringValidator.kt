package com.comtongsu.exercise.domain.gathering.util

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import com.comtongsu.exercise.domain.gathering.exception.AlreadyJoinedGatheringException
import org.springframework.stereotype.Component

@Component
class GatheringValidator {
    fun isLeader(accountGatheringList: List<AccountGathering>, account: Account): Boolean {
        return accountGatheringList.find { it.account == account }?.isLeader ?: false
    }

    fun isAlreadyJoined(accountGatheringList: List<AccountGathering>, account: Account) {
        accountGatheringList
                .find { it.account == account }
                ?.let { throw AlreadyJoinedGatheringException() }
    }
}
