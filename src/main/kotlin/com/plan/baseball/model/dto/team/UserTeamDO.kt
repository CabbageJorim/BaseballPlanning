package com.plan.baseball.model.dto.team

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.dto.user_info.UserInfoDO
import javax.persistence.*

@Entity
@Table(name = "USER_TEAM")
data class UserTeamDO(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L,
    @OneToOne(targetEntity = UserInfoDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="EMAIL", nullable = false)
    val userInfoDO: UserInfoDO,
    @OneToOne(targetEntity = TeamDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="TEAM_ID", nullable = false)
    val teamDO: TeamDO,
    @Column(nullable = false, length = 20, name = "ROLE")
    val role: String,
    @Column(nullable = false)
    val backNumber: Int,

//    @OneToMany(mappedBy = "userTeamDO", fetch = FetchType.EAGER)
//    val batterBasicSeasonRecordDO: List<BatterBasicSeasonRecordDO>
)