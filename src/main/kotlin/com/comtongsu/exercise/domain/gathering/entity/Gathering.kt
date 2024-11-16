package com.comtongsu.exercise.domain.gathering.entity

import com.comtongsu.exercise.domain.gathering.dto.request.GatheringRequestDto
import com.comtongsu.exercise.domain.gathering.dto.response.GatheringResponseDto
import com.comtongsu.exercise.domain.gathering.entity.enums.Place
import com.comtongsu.exercise.global.common.BaseEntity
import com.comtongsu.exercise.global.enums.ExerciseType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "gathering")
class Gathering(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var title: String? = null,
        var content: String? = null,
        @Column(name = "like_count") var likeCount: Int = 0,
        @Column(name = "view_count") var viewCount: Int = 0,
        @Column(name = "maximum_number") var maximumNumber: Int? = null,
        @Column(name = "start_date") var startDate: LocalDateTime? = null,
        @Enumerated(EnumType.STRING)
        @Column(name = "exercsise_type")
        var exerciseType: ExerciseType? = null,
        @Enumerated(EnumType.STRING) var place: Place? = null,
        @OneToMany(mappedBy = "gathering")
        var accountList: MutableList<AccountGathering> = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun createGathering(request: GatheringRequestDto.GatheringRequest): Gathering {
            return Gathering(
                    title = request.title,
                    content = request.content,
                    maximumNumber = request.maximumNumber,
                    startDate = request.startDate,
                    exerciseType = request.exerciseType,
                    place = request.place,
            )
        }
    }

    fun toGatheringDetailResponse(
            leaderNickname: String,
            isLeader: Boolean
    ): GatheringResponseDto.GatheringDetailResponse {
        return GatheringResponseDto.GatheringDetailResponse(
                title = title,
                content = content,
                leaderNickname = leaderNickname,
                place = place,
                exerciseType = exerciseType,
                startDate = startDate,
                maximumNumber = maximumNumber,
                isLeader = isLeader)
    }
}
