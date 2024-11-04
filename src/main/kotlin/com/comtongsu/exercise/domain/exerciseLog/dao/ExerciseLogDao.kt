package com.comtongsu.exercise.domain.exerciseLog.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.exerciseLog.dto.response.ExerciseLogResponseDto
import com.comtongsu.exercise.domain.exerciseLog.entity.QExerciseLog.exerciseLog
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDate
import org.springframework.stereotype.Repository

@Repository
class ExerciseLogDao(private val queryFactory: JPAQueryFactory) {

    // 운동 종류별 그룹핑
    // 날짜 총합, 시간 총압 계산
    // 이번달 필터처리
    // 유저 필터처리
    fun getTotalExerciseLog(account: Account): List<ExerciseLogResponseDto.TotalExerciseLog> {
        return queryFactory
                .select(
                        Projections.fields(
                                ExerciseLogResponseDto.TotalExerciseLog::class.java,
                                exerciseLog.exerciseType,
                                exerciseLog.createdDate.count().`as`("day"),
                                exerciseLog.exerciseTime.sum().`as`("hour")))
                .from(exerciseLog)
                .where(exerciseLog.account.eq(account).and(equalCurrentMonth()))
                .groupBy(exerciseLog.exerciseType)
                .fetch()
    }

    fun equalCurrentMonth(): BooleanExpression {
        return exerciseLog.createdDate.month().eq(LocalDate.now().monthValue)
    }
}
