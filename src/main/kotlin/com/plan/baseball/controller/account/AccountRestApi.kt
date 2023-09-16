package com.plan.baseball.controller.account

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/account/api")
/**
 * 계정 생성에 관한 모든 부분 담당
 */
class AccountRestApi(
    private val accountService: AccountService
) {
    @PostMapping("/register")
    fun create(@RequestBody userInfoDO:UserInfoDO){
        userInfoDO.createDate = LocalDateTime.now()
        accountService.register(userInfoDO)
    }

    @GetMapping("/email-dup")
    fun emailDupCheck(@RequestParam email:String): UserInfoDO?{
        return accountService.selectByEmail(email)
    }

    @PutMapping("/change-pw")
    fun passwordChange(@RequestBody data:HashMap<String, String>): Int{
        return accountService.modify(data);
    }
}