package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.team.UserTeamDO
import javax.persistence.*

@Entity
@Table(name = "S_B_BASIC")//테이블 명: SEASON_BATTER_BASIC
data class BatterBasicSeasonRecordDO(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L, //고작해봐야 이건데, 이거 알아내는데 시간 너무 먹었습니다ㅠㅠㅠㅠ

    @ManyToOne(targetEntity = UserTeamDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="USERTEAM_ID", nullable = false)
    val userTeamDO: UserTeamDO,

    @Column(name="GAME", nullable = false)
    val game:Int,   //출장수
    @Column(name="PA", nullable = false)
    val pa:Int,     //타석 Plate Appearance
    @Column(name="AB", nullable = false)
    val ab:Int,     //타수 At Bat
    @Column(name="RUN", nullable = false)
    val run:Int,    //득점
    @Column(name="HIT", nullable = false)
    val hit:Int,    //총안타
    @Column(name="HOMERUN", nullable = false)
    val homerun: Int,    //홈런
    @Column(name="RBI", nullable = false)
    val rbi: Int,        //타점
    @Column(name="SB", nullable = false)
    val sb: Int,     //도루
    @Column(name="BB", nullable = false)
    val bb: Int,         //볼넷
    @Column(name="KK", nullable = false)
    val kk: Int        //삼진
)