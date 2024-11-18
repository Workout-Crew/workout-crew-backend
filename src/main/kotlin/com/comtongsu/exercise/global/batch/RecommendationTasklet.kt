package com.comtongsu.exercise.global.batch

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.global.bedrock.BedrockService
import java.io.Serializable
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class RecommendationTasklet(private val bedrockService: BedrockService) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val executionContext = chunkContext.stepContext.stepExecution.jobExecution.executionContext
        val exerciseAccountDataList =
                executionContext["exerciseAccountDataList"] as List<ExerciseDataTasklet.ExerciseAccountData>

        val recommendationResultList =
                exerciseAccountDataList.map { exerciseAccountData ->
                    val recommendation =
                            bedrockService.invokeModel(
                                    exerciseAccountData.accountForRecommendation,
                                    exerciseAccountData.exerciseForRecommendation)
                    RecommendationResult(
                            exerciseAccountData.account, recommendation.type, recommendation.description)
                }

        executionContext.put("recommendationResultList", recommendationResultList)

        return RepeatStatus.FINISHED
    }

    data class RecommendationResult(
            val account: Account,
            val type: String,
            val recommendation: String
    ) : Serializable
}
