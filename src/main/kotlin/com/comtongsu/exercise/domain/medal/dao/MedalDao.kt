package com.comtongsu.exercise.domain.medal.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.exerciseLog.entity.QExerciseLog.exerciseLog
import com.comtongsu.exercise.domain.gathering.entity.QAccountGathering.accountGathering
import com.comtongsu.exercise.domain.gathering.entity.QGathering.gathering
import com.comtongsu.exercise.domain.medal.entity.Medal
import com.comtongsu.exercise.domain.medal.entity.QMedal.medal
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MedalDao(private val queryFactory: JPAQueryFactory) {
    // 운동 기록 수
    fun getExerciseLogCount(account: Account): Int {
        return queryFactory.selectFrom(exerciseLog).where(exerciseLog.account.eq(account)).fetch().size
    }

    // 모임 운동 기록 수
    fun getGatheringExerciseLogCount(account: Account): Int {
        return queryFactory
                .selectFrom(exerciseLog)
                .where(exerciseLog.account.eq(account).and(exerciseLog.gathering.isNotNull))
                .fetch()
                .size
    }

    // 개설한 모임 수
    fun getGatheringCount(account: Account): Int {
        return queryFactory
                .selectFrom(gathering)
                .innerJoin(gathering.accountList, accountGathering)
                .fetchJoin()
                .where(accountGathering.account.eq(account).and(accountGathering.isLeader.isTrue))
                .fetch()
                .size
    }

    // 운동 종류 수
    fun getExerciseLogTypeCount(account: Account): Int {
        return queryFactory
                .selectFrom(exerciseLog)
                .where(exerciseLog.account.eq(account))
                .groupBy(exerciseLog.exerciseType)
                .fetch()
                .size
    }

    fun getNextMedal(value: Int, medalType: MedalType): Medal? {
        return queryFactory
                .selectFrom(medal)
                .where(medal.medalType.eq(medalType).and(medal.value.gt(value)))
                .orderBy(medal.value.asc())
                .fetchFirst()
    }

    fun getCurrentMedal(value: Int, medalType: MedalType): Medal? {
        return queryFactory
                .selectFrom(medal)
                .where(medal.medalType.eq(medalType).and(medal.value.loe(value)))
                .orderBy(medal.value.desc())
                .fetchFirst()
    }
}
