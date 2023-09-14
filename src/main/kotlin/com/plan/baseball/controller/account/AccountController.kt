package com.plan.baseball.controller.account

import com.plan.baseball.model.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AccountController(
    @Autowired
    private val accountService: AccountService
) {

    @GetMapping("/account")
    fun accountPage(model: Model): String {
        model.addAttribute("headTitle", "회원가입")
        return "account/account"
    }

    /**
     * localhost:8080/login
     */
    @GetMapping("/login")
    fun login(): String {
        return "account/login" // login.html 연결
    }

    @GetMapping("/findEmail")
    fun findEmail(): String {
        return "account/findEmail"
    }

    @PostMapping("/findEmail")
    fun findEmailResult(tel: String, model: Model): String {
        val userInfoDO = accountService.selectByTel(tel)

        if(userInfoDO == null) {
            model.addAttribute("msg", "입력하신 번호와 일치하는 회원 정보가 없습니다.")
            return "account/findEmail"
        }
        else {
            model.addAttribute("msg", "회원님의 email은 ${userInfoDO.email} 입니다")
            return "account/findEmailResult"
        }
    }
}