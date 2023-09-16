package com.plan.baseball

import com.plan.baseball.model.service.CrawlingService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CrawlingTest(

) {
    @Test
    fun crawlingTeam(){
        val crawlingService = CrawlingService("http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2022")
        crawlingService.loadBatterData()
    }
}