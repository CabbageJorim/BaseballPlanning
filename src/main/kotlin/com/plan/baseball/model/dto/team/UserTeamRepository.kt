package com.plan.baseball.model.dto.team

import org.springframework.data.jpa.repository.JpaRepository

interface UserTeamRepository:JpaRepository<UserTeamDO, Long> {
    fun findUserTeamByTeamDOIdAndBackNumber(teamDOId: Long, backNumber:Int): List<UserTeamDO>
}