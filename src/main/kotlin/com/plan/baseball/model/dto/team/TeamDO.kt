//TODO: merge 할 때 반드시 제거하세요!
package com.plan.baseball.model.dto.team

import java.time.LocalDateTime
import javax.persistence.*

/**
 * 해당 데이터 클래스는 테스트용입니다. 반드시 병합할 시점에 제거하세요!
 */
@Entity
@Table(name = "TEAM")
data class TeamDO (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L,

    @Column(name = "NAME", nullable = false)
    val name:String,
    @Column(name="CREATEDATE", nullable = false)
    var createDate: LocalDateTime? = LocalDateTime.now()
)