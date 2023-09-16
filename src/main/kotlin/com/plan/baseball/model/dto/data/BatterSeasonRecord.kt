package com.plan.baseball.model.dto.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class BatterSeasonRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val registerNum:Long,
    val game:Int,   //출장수
    val PA:Int,     //타석 Plate Appearance
    val AB:Int,     //타수 At Bat
    val run:Int,    //득점
    val hit:Int,    //총안타
    val single: Int,   //1루타 -> 중요도 떨어짐
    val double: Int,   //2루타 -> 중요도 떨어짐
    val triple: Int,   //3루타 -> 중요도 떨어짐
    val homer: Int,    //홈런
    val totalBases:Int, //전체루타 -> 중요도 떨어짐
    val RBI:Int,        //타점
    val steal: Int,     //도루
    val failureSteal: Int, //도실 -> 중요도 떨어짐
    val BB: Int,         //볼넷
    val intentionalBB: Int, //고의사구 -> 중요도 떨어짐
    val PBP: Int,        //사구 -> 중요도 떨어짐
    val KK: Int,        //삼진
    val doublePlay:Int  //병살타 -> 중요도 떨어짐
)