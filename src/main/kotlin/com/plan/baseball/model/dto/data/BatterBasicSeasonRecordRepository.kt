package com.plan.baseball.model.dto.data

import org.springframework.data.jpa.repository.JpaRepository

interface BatterBasicSeasonRecordRepository:JpaRepository<BatterBasicSeasonRecordDO, Long> {
    //fun findByUserInfoDOEmail(email:String): List<BatterBasicSeasonRecord>
}