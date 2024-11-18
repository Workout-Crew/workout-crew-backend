package com.comtongsu.exercise.domain.gathering.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.gathering.entity.Gathering
import com.comtongsu.exercise.domain.gathering.entity.QGathering.gathering
import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.global.enums.ExerciseType
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class GatheringDao(private val queryFactory: JPAQueryFactory) {
    fun getGatheringListByPlaceAndExerciseType(
            place: Place?,
            exerciseType: ExerciseType?
    ): List<Gathering> {
        return queryFactory
                .selectFrom(gathering)
                .where(eqPlace(place), eqExerciseType(exerciseType))
                .fetch()
    }

    fun eqPlace(place: Place?): BooleanExpression {
        return place?.let { gathering.place.eq(it) } ?: Expressions.asBoolean(true).isTrue
    }

    fun eqExerciseType(exerciseType: ExerciseType?): BooleanExpression {
        return exerciseType?.let { gathering.exerciseType.eq(it) } ?: Expressions.asBoolean(true).isTrue
    }

    fun getPossibleJoinGathering(accont: Account): List<Gathering> {
        return queryFactory
                .selectFrom(gathering)
                .where(gathering.accountList.any().account.ne(accont))
                .fetch()
    }
}
