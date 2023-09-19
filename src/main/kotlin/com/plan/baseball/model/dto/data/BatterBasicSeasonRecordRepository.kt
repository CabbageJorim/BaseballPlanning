package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.user_info.UserInfoDO
import org.springframework.data.jpa.repository.JpaRepository

interface BatterBasicSeasonRecordRepository:JpaRepository<BatterBasicSeasonRecord, Long> {
    //fun findByUserInfoDOEmail(email:String): List<BatterBasicSeasonRecord>
}