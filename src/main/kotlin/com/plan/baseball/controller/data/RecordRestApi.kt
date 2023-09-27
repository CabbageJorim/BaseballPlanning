package com.plan.baseball.controller.data

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RecordRestApi {

    @GetMapping("/data")
    fun getSeasonDataBasic(@RequestParam teamName:String, @RequestParam season:String){

    }
}