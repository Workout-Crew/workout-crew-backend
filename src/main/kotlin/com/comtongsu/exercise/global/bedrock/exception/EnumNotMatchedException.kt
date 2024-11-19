package com.comtongsu.exercise.global.bedrock.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.BedrockException

class EnumNotMatchedException : BedrockException(ErrorCode.ENUM_NOT_MATCHED)
