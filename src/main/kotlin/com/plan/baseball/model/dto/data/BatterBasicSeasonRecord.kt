package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.user_info.UserInfoDO
import javax.persistence.*

@Entity
data class BatterBasicSeasonRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @ManyToOne(targetEntity = UserInfoDO::class, fetch = FetchType.LAZY)
    @JoinColumn(name="EMAIL")
    val userInfoDO: UserInfoDO,

    val game:Int,   //출장수
    val pa:Int,     //타석 Plate Appearance
    val ab:Int,     //타수 At Bat
    val run:Int,    //득점
    val hit:Int,    //총안타
    val homer: Int,    //홈런
    val rbi: Int,        //타점
    val steal: Int,     //도루
    val bb: Int,         //볼넷
    val kk: Int,        //삼진
)