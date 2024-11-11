package com.comtongsu.exercise.global.scheduler

import com.comtongsu.exercise.global.scheduler.exception.ExecuteErrorException
import org.quartz.JobExecutionException
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BatchScheduler(private val jobLauncher: JobLauncher, private val recommendationJob: Job) {

    @Scheduled(cron = "0 0 0 * * SUN")
    fun runRecommendationJob() {
        val jobParameters: JobParameters =
                JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters()

        try {
            jobLauncher.run(recommendationJob, jobParameters)
        } catch (e: JobExecutionException) {
            throw ExecuteErrorException()
        }
    }
}
