package com.comtongsu.exercise.global.s3.exception

import com.comtongsu.exercise.global.error.ErrorCode
import com.comtongsu.exercise.global.error.exception.S3Exception

class NotValidateFileException : S3Exception(ErrorCode.NOT_VALIDATE_FILE)
