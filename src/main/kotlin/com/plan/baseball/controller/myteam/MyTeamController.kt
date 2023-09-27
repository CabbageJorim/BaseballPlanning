package com.plan.baseball.controller.myteam

import com.plan.baseball.model.service.MyTeamService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MyTeamController(
    private val myTeamService: MyTeamService
) {
    @GetMapping("/myteam/main")
    fun myTeamPage(@AuthenticationPrincipal user:User, model:Model): String{
        model.addAttribute("myTeamList", myTeamService.getMyTeam(user.username))
        return "myteam/myTeamMain"
    }

    @GetMapping("/myteam")
    fun myTeamDetail(@RequestParam teamId:Long, @RequestParam season:Int, model: Model): String{
        model.addAttribute("teamSeasonRecord", myTeamService.getMyTeamRecordBySeason(teamId, season))
        return "myTeam/myTeamDetail"
    }
}