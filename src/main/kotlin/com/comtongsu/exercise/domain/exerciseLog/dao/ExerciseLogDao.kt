package com.comtongsu.exercise.domain.exerciseLog.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.domain.exerciseLog.entity.ExerciseLog
import com.comtongsu.exercise.domain.exerciseLog.entity.QExerciseLog.exerciseLog
import com.comtongsu.exercise.global.enums.ExerciseType
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDate
import org.springframework.stereotype.Repository

@Repository
class ExerciseLogDao(private val queryFactory: JPAQueryFactory) {

    fun getTotalExerciseLog(account: Account): List<ExerciseLogResponseDto.TotalExerciseLog> {
        return queryFactory
                .select(
                        Projections.constructor(
                                ExerciseLogResponseDto.TotalExerciseLog::class.java,
                                exerciseLog.exerciseType,
                                exerciseLog.startTime.count().`as`("day"),
                                exerciseLog.exerciseTime.sum().`as`("hour")))
                .from(exerciseLog)
                .where(exerciseLog.account.eq(account).and(equalCurrentMonth()))
                .groupBy(exerciseLog.exerciseType)
                .fetch()
    }

    fun equalCurrentMonth(): BooleanExpression {
        return exerciseLog.startTime
                .month()
                .eq(LocalDate.now().monthValue)
                .and(exerciseLog.startTime.year().eq(LocalDate.now().year))
    }

    fun getExerciseLogByDate(account: Account, currentDate: LocalDate): List<ExerciseLog> {
        return queryFactory
                .selectFrom(exerciseLog)
                .where(exerciseLog.account.eq(account).and(equalCurrentDate(currentDate)))
                .fetch()
    }

    fun equalCurrentDate(currentDate: LocalDate): BooleanExpression {
        return exerciseLog.startTime.between(
                currentDate.atStartOfDay(), currentDate.plusDays(1).atStartOfDay())
    }

    fun getExerciseLogByTwoWeeks(account: Account): List<ExerciseLog> {
        return queryFactory
                .selectFrom(exerciseLog)
                .where(
                        exerciseLog.account
                                .eq(account)
                                .and(
                                        exerciseLog.exerciseType
                                                .eq(ExerciseType.RUNNING)
                                                .or(exerciseLog.exerciseType.eq(ExerciseType.HEALTH)))
                                .and(betweenTwoWeeks()))
                .fetch()
    }

    fun betweenTwoWeeks(): BooleanExpression {
        return exerciseLog.startTime.between(
                LocalDate.now().minusDays(14).atStartOfDay(), LocalDate.now().atStartOfDay())
    }

    fun getExerciseLogByMonth(account: Account, currentMonth: LocalDate): List<ExerciseLog> {
        return queryFactory
                .selectFrom(exerciseLog)
                .where(exerciseLog.account.eq(account).and(equalCurrentMonth()))
                .fetch()
    }
}
