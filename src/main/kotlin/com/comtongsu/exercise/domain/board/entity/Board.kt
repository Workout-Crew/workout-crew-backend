package com.comtongsu.exercise.domain.board.entity

import com.comtongsu.exercise.global.common.BaseEntity
import com.comtongsu.exercise.global.enums.ExerciseType
import jakarta.persistence.*

@Entity
@Table(name = "board")
class Board(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @Enumerated(EnumType.STRING)
        @Column(name = "exercise_type")
        var exerciseType: ExerciseType? = null,
        var title: String? = null,
        var content: String? = null,
        @Column(name = "like_count") var likeCount: Int = 0,
        @Column(name = "view_count") var viewCount: Int = 0,
        @OneToMany(mappedBy = "board") var commentList: MutableList<Comment> = mutableListOf(),
        @OneToMany(mappedBy = "board") var imageList: MutableList<BoardImage> = mutableListOf(),
) : BaseEntity()
