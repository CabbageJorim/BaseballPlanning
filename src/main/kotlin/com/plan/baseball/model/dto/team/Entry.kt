package com.plan.baseball.model.dto.team

import com.plan.baseball.model.dto.user_info.UserInfoDO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ENTRY")
data class Entry(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L,
    @OneToOne(targetEntity = UserInfoDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="EMAIL", nullable = false)
    val userInfoDO: UserInfoDO?,
    @OneToOne(targetEntity = TeamDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="TEAM_ID", nullable = false)
    val teamDO: TeamDO?
)