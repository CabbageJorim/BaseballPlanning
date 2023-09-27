package com.plan.baseball.model.service

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.team.UserTeamDO
import com.plan.baseball.model.dto.team.UserTeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyTeamService(
    private val userTeamRepository: UserTeamRepository,
    private val batterBasicSeasonRecordRepository: BatterBasicSeasonRecordRepository
) {
    fun getMyTeam(email:String): List<UserTeamDO> {
        return userTeamRepository.findUserTeamByUserInfoDOEmail(email)
    }

    fun getMyTeamRecordBySeason(teamId:Long, season:Int): MutableList<BatterBasicSeasonRecordDO> {
        val teamUserList = userTeamRepository.findUserTeamByTeamDOId(teamId)
        val recordList = mutableListOf<BatterBasicSeasonRecordDO>()
        for (member in teamUserList){
            member.id?.let { batterBasicSeasonRecordRepository.findByUserTeamDOIdAndSeason(it,season).orElse(null) }
                ?.let {
                    recordList.add(it)
                }
        }
        return recordList
    }
}