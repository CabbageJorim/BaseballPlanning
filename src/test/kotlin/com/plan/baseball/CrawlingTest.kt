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
import java.util.Optional

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
            "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2023"
        )
        crawlingService.loadBatterData()
    }

    @Test
    fun makeUsrTeam(){
        val userTeamDO = UserTeamDO(
            userInfoDO = userInfoRepository.findById("wkdgyfla97@naver.com").orElseThrow(),
            teamDO = teamRepository.findById(1L).orElseThrow(),
            role = "PLAYER",
            backNumber = 89,
            //batterBasicSeasonRecordDO = listOf()
        )
        userTeamRepository.save(userTeamDO)
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
        getData().forEach { item ->
            runCatching {
                val userTeamEntity = userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(1L, getUniformNum(item[1]))[0]
                println(userTeamEntity)
                val entityData = userTeamEntity.id?.let {
                    id -> batterBasicSeasonRecordRepository.findByUserTeamDOIdAndSeason(id, 2023)
                }
                if (entityData != null) {
                    println(entityData.isEmpty)
                }

                entityData?.let { data ->
                    if (data.isEmpty) {
                        println("store")
                        storeData(item)
                    } else {
                        println("update")
                        updateData(data.get(), item)
                    }
                } ?: run {
                    println("store")
                    storeData(item)
                }

//                entityData?.ifPresent{
//                    updateData(it, item)
//                }?: storeData(item)
            } //예외를 무시하는 것이 기본동작이라고 함
        }
        /*
            정리
            - update를 하려면 해당 데이터 객체를 완전히 가져와서 해결해야함
            - 그렇지 않다면, 서로 다른 id의 데이터로 판단하고 계속 적재를 해버림
            - 어떻게보면 서로 필요한 데이터일 수도 있지만, 현재 개발 의도는 그 방향이 아니기 때문에 그에 맞는 조건을 걸고 해야할 것
        */

    }

    @Test
    fun getTest(){
        //println(userTeamRepository.findAll())
        println(userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(1L, 89))
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
            "http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2023"
        )
        return crawlingService.loadBatterData()
    }
    private fun storeData(item:List<String>){
        println("store")
        val tmpBasicDo = BatterBasicSeasonRecordDO(
            game = item[3].toInt(),
            season = 2023,
            userTeamDO = userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(1L, getUniformNum(item[1]))[0],
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
            userTeamRepository.findUserTeamByTeamDOIdAndBackNumber(teamId, backNumber)[0]
        )
    }
}