package com.plan.baseball.controller.account

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.AccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class AccountRestApi(
    private val accountService: AccountService
) {
    @PostMapping("/register")
    fun create(@RequestBody userInfoDO:UserInfoDO){
        userInfoDO.createDate = LocalDateTime.now()
        accountService.register(userInfoDO)
    }
}