package com.comtongsu.exercise.global.batch

import com.comtongsu.exercise.domain.exerciseRecommendation.service.ExerciseRecommendationService
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class SaveTasklet(private val exerciseRecommendationService: ExerciseRecommendationService) :
        Tasklet {
    companion object {
        private val logger = LoggerFactory.getLogger(SaveTasklet::class.java)
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val executionContext = chunkContext.stepContext.stepExecution.jobExecution.executionContext
        val exerciseAccountDataList =
                executionContext["recommendationResultList"]
                        as List<RecommendationTasklet.RecommendationResult>

        exerciseAccountDataList.forEach() { recommendationResult ->
            try {
                exerciseRecommendationService.createExerciseRecommendation(
                        recommendationResult.account,
                        recommendationResult.type,
                        recommendationResult.recommendation)
            } catch (e: Exception) {
                logger.error("SaveTaskLet Error: ${e.message}")
            }
        }

        return RepeatStatus.FINISHED
    }
}
