package com.comtongsu.exercise.domain.gathering.util

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.gathering.entity.AccountGathering
import org.springframework.stereotype.Component

@Component
class Validator {
    fun isLeader(accountGatheringList: List<AccountGathering>, account: Account): Boolean {
        return accountGatheringList.find { it.account == account }?.isLeader ?: false
    }
}
