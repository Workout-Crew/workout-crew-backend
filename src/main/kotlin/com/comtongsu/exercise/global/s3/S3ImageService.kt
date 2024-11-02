package com.comtongsu.exercise.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.comtongsu.exercise.global.s3.exception.PutImageException
import java.io.InputStream
import java.util.UUID
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3ImageService(
        private val amazonS3: AmazonS3,
        private val s3Validator: S3Validator,
        @Value("\${cloud.aws.s3.bucket.name}") private val bucket: String,
) {
    fun uploadImageToS3(image: MultipartFile): String {
        // 빈 파일 및 이름없는 파일 검증
        s3Validator.isNotEmptyImage(image)

        // 확장자 올바른지 검증
        val imageFileName = image.originalFilename!!
        s3Validator.isValidExtension(imageFileName)

        val extension: String = imageFileName.substring(imageFileName.lastIndexOf("."))
        val s3FileName: String = UUID.randomUUID().toString().substring(0, 10) + imageFileName

        val inputStream: InputStream = image.inputStream
        val bytes: ByteArray = IOUtils.toByteArray(inputStream)

        val metadata = ObjectMetadata()
        metadata.contentType = "image/$extension"
        metadata.contentLength = bytes.size.toLong()
        val byteArrayInputStream = bytes.inputStream()

        try {
            val putObjectRequest: PutObjectRequest =
                    PutObjectRequest(bucket, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            amazonS3.putObject(putObjectRequest)
        } catch (e: Exception) {
            throw PutImageException()
        } finally {
            byteArrayInputStream.close()
            inputStream.close()
        }

        return amazonS3.getUrl(bucket, s3FileName).toString()
    }
}
