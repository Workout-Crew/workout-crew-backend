package com.comtongsu.exercise.global.bedrock.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.BedrockException

class InvokeErrorException : BedrockException(ErrorCode.INVOKE_ERROR)
