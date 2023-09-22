package com.plan.baseball.model.dto.team

import org.springframework.data.jpa.repository.JpaRepository

interface UserTeamRepository:JpaRepository<UserTeamDO, Long> {
    //fun findByUserInfoEmail(email:String): List<Entry>
}