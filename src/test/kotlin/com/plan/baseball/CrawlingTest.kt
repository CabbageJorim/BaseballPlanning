package com.plan.baseball

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.team.TeamDO
import com.plan.baseball.model.dto.team.TeamRepository
import com.plan.baseball.model.dto.team.UserTeamDO
import com.plan.baseball.model.dto.team.UserTeamRepository
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import com.plan.baseball.model.service.CrawlingService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CrawlingTest(
    @Autowired val batterBasicSeasonRecordRepository: BatterBasicSeasonRecordRepository,
    @Autowired val userInfoRepository: UserInfoRepository,
    @Autowired val teamRepository: TeamRepository,
    @Autowired val userTeamRepository: UserTeamRepository
) {
    @Test
    fun crawlingTeam(){
        val crawlingService = CrawlingService(
            "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2022"
        )
        crawlingService.loadBatterData()
    }

    @Test
    fun makeUsrTeam(){
        val userTeamDO = UserTeamDO(
            userInfoDO = userInfoRepository.findById("tester+1@naver.com").orElseThrow(),
            teamDO = teamRepository.findById(2L).orElseThrow(),
            role = "PLAYER",
            backNumber = 33
        )
        userTeamRepository.save(userTeamDO)
    }

    private fun getData(): MutableList<List<String>>{
        val crawlingService = CrawlingService(
        "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2023"
        )
        return crawlingService.loadBatterData()
    }

    @Test
    fun createTeam(){
        val team:TeamDO = TeamDO(
            name = "Sol",
        )
        teamRepository.save(team)
    }

    @Test
    fun makeBasicDO(){
        for(item in getData()){
            val tmpBasicDo = BatterBasicSeasonRecordDO(
                game = item[3].toInt(),
                userTeamDO = userTeamRepository.findById(1).orElseThrow(),
                pa = item[4].toInt(),
                ab = item[5].toInt(),
                run = item[6].toInt(),
                hit = item[7].toInt(),
                homerun = item[11].toInt(),
                rbi = item[13].toInt(),
                sb = item[14].toInt(),
                bb = item[18].toInt(),
                kk = item[21].toInt()
            )
            batterBasicSeasonRecordRepository.save(tmpBasicDo)
        }
    }

    @Test
    fun selectThatPlayer(){
        //TODO: 팀 등록시에 번호 체크하고 나서 등록하도록 해야합니다!
        val teamId = 1L
        val backNumber = 0

        println(
            userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(teamId, backNumber)
        )
    }
}