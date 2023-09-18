package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.user_info.UserInfoDO
import javax.persistence.*

@Entity
data class BatterDetailSeasonRecord (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(targetEntity = UserInfoDO::class, fetch = FetchType.LAZY)
    @JoinColumn(name="EMAIL")
    val userInfoDO: UserInfoDO,

    val single: Int,   //1루타 -> 중요도 떨어짐
    val doubleHit: Int,   //2루타 -> 중요도 떨어짐
    val triple: Int,   //3루타 -> 중요도 떨어짐

    val base:Int, //전체루타 -> 중요도 떨어짐

    val failureSteal: Int, //도실 -> 중요도 떨어짐

    val iBB: Int, //고의사구 -> 중요도 떨어짐
    val PBP: Int,        //사구 -> 중요도 떨어짐

    val doublePlay:Int  //병살타 -> 중요도 떨어짐
)