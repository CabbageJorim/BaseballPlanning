package com.plan.baseball.model.service

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.team.UserTeamDO
import com.plan.baseball.model.dto.team.UserTeamRepository
import org.springframework.stereotype.Service

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

    fun getMyPlayerRecordBySeason(teamId: Long, num: Int): List<BatterBasicSeasonRecordDO>? {
        val userTeamId = userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(teamId, num).get().id
        return userTeamId?.let { batterBasicSeasonRecordRepository.findByUserTeamDOId(it) }
    }

    fun getMyName(teamId: Long, num: Int): String {
        return userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(teamId, num).orElseThrow().userInfoDO.name
    }
}