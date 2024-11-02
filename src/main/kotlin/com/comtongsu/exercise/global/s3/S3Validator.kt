package com.comtongsu.exercise.global.s3

import com.comtongsu.exercise.global.s3.exception.EmptyImageException
import com.comtongsu.exercise.global.s3.exception.NotValidateFileException
import java.util.*
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3Validator(
        val allowedImageExtensionList: List<String> = listOf("jpg", "jpeg", "png", "gif")
) {
    fun isNotEmptyImage(image: MultipartFile) {
        if (image.isEmpty || Objects.isNull(image.originalFilename)) {
            throw EmptyImageException()
        }
    }

    fun isValidExtension(fileName: String) {
        val dotIndex = fileName.lastIndexOf(".")
        if (dotIndex == -1) {
            throw NotValidateFileException()
        }

        val extension = fileName.substring(dotIndex + 1).lowercase()

        if (!allowedImageExtensionList.contains(extension)) {
            throw NotValidateFileException()
        }
    }
}
