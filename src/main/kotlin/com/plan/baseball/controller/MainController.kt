package com.plan.baseball.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/")
    fun mainPage(): String{
        return "main"
    }
}