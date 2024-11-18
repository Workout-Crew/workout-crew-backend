package com.comtongsu.exercise.domain.medal.entity

import com.comtongsu.exercise.domain.medal.entity.enums.MedalRank
import com.comtongsu.exercise.domain.medal.entity.enums.MedalType
import jakarta.persistence.*

@Entity
@Table(name = "medal")
class Medal(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @Column(nullable = false) var image: String? = null,
        @Enumerated(EnumType.STRING)
        @Column(name = "medal_type", nullable = false)
        var medalType: MedalType? = null,
        var value: Int? = null,
        @Enumerated(EnumType.STRING)
        @Column(name = "medal_rank", nullable = false)
        var medalRank: MedalRank = MedalRank.NONE,
)
