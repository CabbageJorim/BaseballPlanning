package com.plan.baseball.controller.account

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AccountController(

) {
    @GetMapping("/account")
    fun accountPage(model: Model): String {
        model.addAttribute("headTitle", "회원가입")
        return "account/account"
    }
}