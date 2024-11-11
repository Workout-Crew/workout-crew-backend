package com.comtongsu.exercise.domain.account.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.EntityNotFoundException

class AccountNotFoundException : EntityNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND)
