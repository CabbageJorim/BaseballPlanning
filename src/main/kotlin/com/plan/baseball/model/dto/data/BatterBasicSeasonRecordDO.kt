package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.team.UserTeamDO
import javax.persistence.*

@Entity
@Table(name = "S_B_BASIC")//테이블 명: SEASON_BATTER_BASIC
data class BatterBasicSeasonRecordDO(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L, //고작해봐야 이건데, 이거 알아내는데 시간 너무 먹었습니다ㅠㅠㅠㅠ

    //@OneToMany(targetEntity = UserTeamDO::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERTEAM_ID", nullable = false)
    val userTeamDO: UserTeamDO,

    @Column(name="SEASON", nullable = false)
    var season:Int,

    @Column(name="GAME", nullable = false)
    var game:Int,   //출장수
    @Column(name="PA", nullable = false)
    var pa:Int,     //타석 Plate Appearance
    @Column(name="AB", nullable = false)
    var ab:Int,     //타수 At Bat
    @Column(name="RUN", nullable = false)
    var run:Int,    //득점
    @Column(name="HIT", nullable = false)
    var hit:Int,    //총안타
    @Column(name="HOMERUN", nullable = false)
    var homerun: Int,    //홈런
    @Column(name="RBI", nullable = false)
    var rbi: Int,        //타점
    @Column(name="SB", nullable = false)
    var sb: Int,     //도루
    @Column(name="BB", nullable = false)
    var bb: Int,         //볼넷
    @Column(name="KK", nullable = false)
    var kk: Int        //삼진
)