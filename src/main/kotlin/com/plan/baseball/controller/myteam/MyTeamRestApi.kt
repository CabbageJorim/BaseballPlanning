package com.plan.baseball.controller.myteam

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordDO
import com.plan.baseball.model.service.MyTeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myteam/api/")
class MyTeamRestApi(
    private val myTeamService: MyTeamService
) {
    @GetMapping("season/all")
    fun getSeasonDataAll(@RequestParam teamId:Long, @RequestParam(defaultValue = 2023.toString()) season:Int): MutableList<BatterBasicSeasonRecordDO> {
        return myTeamService.getMyTeamRecordBySeason(teamId, season)
    }
}