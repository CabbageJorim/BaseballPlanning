package com.plan.baseball.controller.main

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController() {
    @GetMapping("/")
    fun main(): String {
        return "main"
    }

    @GetMapping("/main")
    fun main2(): String {
        return "main"
    }
}