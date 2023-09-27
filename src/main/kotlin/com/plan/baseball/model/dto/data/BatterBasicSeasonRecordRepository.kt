package com.plan.baseball.model.dto.data

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BatterBasicSeasonRecordRepository:JpaRepository<BatterBasicSeasonRecordDO, Long> {
    //fun findByUserInfoDOEmail(email:String): List<BatterBasicSeasonRecord>
    fun findByUserTeamDOId(id:Long): Optional<BatterBasicSeasonRecordDO>
    fun findByUserTeamDOIdAndSeason(id:Long, season:Int): Optional<BatterBasicSeasonRecordDO>
}