package com.comtongsu.exercise.global.config

import com.comtongsu.exercise.global.batch.ExerciseDataTasklet
import com.comtongsu.exercise.global.batch.RecommendationTasklet
import com.comtongsu.exercise.global.batch.SaveTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.listener.ExecutionContextPromotionListener
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class BatchConfig(
        private val exerciseDataTasklet: ExerciseDataTasklet,
        private val recommendationTasklet: RecommendationTasklet,
        private val saveTasklet: SaveTasklet,
) {

    @Bean
    fun recommendationJob(
            jobRepository: JobRepository,
            transactionManager: PlatformTransactionManager
    ): Job {
        return JobBuilder("recommendationJob", jobRepository)
                .start(getExerciseDataStep(jobRepository, transactionManager))
                .next(recommendStep(jobRepository, transactionManager))
                .next(saveStep(jobRepository, transactionManager))
                .build()
    }

    @Bean
    fun getExerciseDataStep(
            jobRepository: JobRepository,
            platformTransactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder("getExerciseDataStep", jobRepository)
                .tasklet(exerciseDataTasklet, platformTransactionManager)
                .listener(promotionListener())
                .build()
    }

    @Bean
    fun recommendStep(
            jobRepository: JobRepository,
            platformTransactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder("getRecommendationStep", jobRepository)
                .tasklet(recommendationTasklet, platformTransactionManager)
                .listener(promotionListener())
                .build()
    }

    @Bean
    fun saveStep(
            jobRepository: JobRepository,
            platformTransactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder("getSaveStep", jobRepository)
                .tasklet(saveTasklet, platformTransactionManager)
                .listener(promotionListener())
                .build()
    }

    @Bean
    fun promotionListener(): ExecutionContextPromotionListener {
        val listener = ExecutionContextPromotionListener()
        listener.setKeys(arrayOf("exerciseAccountDataList", "recommendationResultList"))
        return listener
    }
}
