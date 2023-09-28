package com.plan.baseball

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.team.TeamDO
import com.plan.baseball.model.dto.team.TeamRepository
import com.plan.baseball.model.dto.team.UserTeamDO
import com.plan.baseball.model.dto.team.UserTeamRepository
import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import com.plan.baseball.model.service.CrawlingService
import com.plan.baseball.model.service.MyTeamService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.Optional

@SpringBootTest
class CrawlingTest(
    @Autowired val batterBasicSeasonRecordRepository: BatterBasicSeasonRecordRepository,
    @Autowired val userInfoRepository: UserInfoRepository,
    @Autowired val teamRepository: TeamRepository,
    @Autowired val userTeamRepository: UserTeamRepository,
    @Autowired val myTeamService: MyTeamService,
) {
    /**
     * 단순 크롤링 테스트
     */
    @Test
    fun crawlingTeam(){
        val crawlingService = CrawlingService(
            "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2023"
        )
        crawlingService.loadBatterData()
    }

    @Test
    fun makeUsrTeam(){
        val userTeamDO = UserTeamDO(
            userInfoDO = userInfoRepository.findById("wkdgyfla97@naver.com").orElseThrow(),
            teamDO = teamRepository.findById(3L).orElseThrow(),
            role = "PLAYER",
            backNumber = 89,
            //batterBasicSeasonRecordDO = listOf()
        )
        userTeamRepository.save(userTeamDO)
    }

    @Test
    fun createUserTeam(){
        val numList = listOf(0, 60, 1, 84, 16, 66, 8)
        for(i in 1 .. 6){
            val userData = UserTeamDO(
                userInfoDO = userInfoRepository.findById("test${i}@naver.com").orElseThrow(),
                teamDO = teamRepository.findById(1L).orElseThrow(),
                role = "PLAYER",
                backNumber = numList[i]
            )
            userTeamRepository.save(userData)
        }
    }

    @Test
    fun createTeam(){
        val team:TeamDO = TeamDO(
            name = "Sol",
        )
        teamRepository.save(team)
    }

    val season = 2016

    @Test
    fun makeBasicDO(){
        getData().forEach { item ->
            runCatching {
                val userTeamEntity = userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(1L, getUniformNum(item[1])).get()
                val entityData = userTeamEntity.id?.let {
                    id -> batterBasicSeasonRecordRepository.findByUserTeamDOIdAndSeason(id, season)
                }

                entityData?.let {
                    data -> if (data.isEmpty) storeData(item) else updateData(data.get(), item)
                } ?: run { storeData(item) }

            } //예외를 무시하는 것이 기본동작이라고 함
        }
    }

    private fun updateData(entityData: BatterBasicSeasonRecordDO, item:List<String>) {
        println("update")
        entityData.game = item[3].toInt()
        entityData.pa = item[4].toInt()
        entityData.ab = item[5].toInt()
        entityData.run = item[6].toInt()
        entityData.hit = item[7].toInt()
        entityData.homerun = item[11].toInt()
        entityData.rbi = item[13].toInt()
        entityData.sb = item[14].toInt()
        entityData.bb = item[18].toInt()
        entityData.kk = item[21].toInt()

        batterBasicSeasonRecordRepository.saveAndFlush(entityData)
    }
    private fun getData(): MutableList<List<String>>{
        val crawlingService = CrawlingService(
            "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=${season}"
        )
        return crawlingService.loadBatterData()
    }
    private fun storeData(item:List<String>){
        println("store")
        val tmpBasicDo = BatterBasicSeasonRecordDO(
            game = item[3].toInt(),
            season = season,
            userTeamDO = userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(1L, getUniformNum(item[1])).get(),
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
        batterBasicSeasonRecordRepository.saveAndFlush(tmpBasicDo)
    }
    private fun getUniformNum(item:String): Int{
        return item.substringAfter("(")
                    .substringBeforeLast(")")
                    .toInt()
    }

    @Test
    fun selectThatPlayer(){
        //TODO: 팀 등록시에 번호 체크하고 나서 등록하도록 해야합니다!
        val teamId = 1L
        val backNumber = 89

        println(
            userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(teamId, backNumber)
        )
    }

    @Test
    fun repositoryTest(){
        println(myTeamService.getMyTeamRecordBySeason(teamId = 1L, season = 2023))
    }
}