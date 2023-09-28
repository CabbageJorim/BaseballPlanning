package com.plan.baseball.model.dto.team

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserTeamRepository:JpaRepository<UserTeamDO, Long> {
    fun findUserTeamByTeamDOIdAndBackNumber(teamDOId: Long, backNumber:Int): Optional<UserTeamDO>
    fun findUserTeamByUserInfoDOEmail(email:String): List<UserTeamDO>
    fun findUserTeamByTeamDOId(teamDOId:Long): List<UserTeamDO>

    //fun findUserInfoDONameAndBackNumber(name:String, backNumber: Int): List<UserTeamDO>
    /*불가능하다고 합니다. 이메일을 가져오면 어떻게든 될 거 같은데, 조회할 땐 이메일이 없어서 안될 거 같습니다*/
}