package com.plan.baseball.model.dto.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository:JpaRepository<TeamDO, Long> {
}