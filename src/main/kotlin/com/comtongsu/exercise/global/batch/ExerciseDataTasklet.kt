package com.comtongsu.exercise.global.batch

import com.comtongsu.exercise.domain.account.dto.response.AccountResponseDto
import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.account.service.AccountService
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.domain.exerciseLog.service.ExerciseLogService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class ExerciseDataTasklet(
        private val accountService: AccountService,
        private val exerciseLogService: ExerciseLogService
) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val executionContext = chunkContext.stepContext.stepExecution.executionContext
        val accountList = accountService.findAllAccount()

        val exerciseAccountDatList =
                accountList.map { account ->
                    val accountForRecommendation = accountService.getAccountForRecommendation(account)
                    val exerciseForRecommendation = exerciseLogService.getExerciseForRecommendation(account)
                    ExerciseAccountData(account, accountForRecommendation, exerciseForRecommendation)
                }

        executionContext.put("exerciseAccountDataList", exerciseAccountDatList)

        return RepeatStatus.FINISHED
    }

    data class ExerciseAccountData(
            val account: Account,
            val accountForRecommendation: AccountResponseDto.AccountForRecommendation,
            val exerciseForRecommendation: List<ExerciseLogResponseDto.ExerciseForRecommendation>
    )
}
