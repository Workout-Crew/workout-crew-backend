package com.comtongsu.exercise.global.scheduler.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.BatchException

class ExecuteErrorException : BatchException(ErrorCode.EXECUTE_ERROR)
