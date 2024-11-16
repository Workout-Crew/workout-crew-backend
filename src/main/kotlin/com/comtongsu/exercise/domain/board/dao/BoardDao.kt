package com.comtongsu.exercise.domain.board.dao

import com.comtongsu.exercise.domain.account.entity.Account
import com.comtongsu.exercise.domain.board.entity.Board
import com.comtongsu.exercise.domain.board.entity.Category
import com.comtongsu.exercise.domain.board.entity.QBoard.board
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class BoardDao(private val queryFactory: JPAQueryFactory) {
    fun getBoardList(category: Category): List<Board> {
        return queryFactory
                .selectFrom(board)
                .where(board.category.eq(category))
                .orderBy(board.createdDate.desc())
                .fetch()
    }

    fun getBoardListByAccount(account: Account): List<Board> {
        return queryFactory
                .selectFrom(board)
                .where(board.account.eq(account))
                .orderBy(board.createdDate.desc())
                .fetch()
    }

    fun getBoardListByMyComment(account: Account): List<Board> {
        return queryFactory
                .selectFrom(board)
                .where(board.commentList.any().account.eq(account))
                .orderBy(board.createdDate.desc())
                .fetch()
    }
}
