package com.plan.baseball.model.dto.data

import org.springframework.data.jpa.repository.JpaRepository

interface BatterDetailSeasonRecordRepository:JpaRepository<BatterDetailSeasonRecordDO, Long> {
}