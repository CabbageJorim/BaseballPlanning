package com.plan.baseball.controller.mail

import com.plan.baseball.model.dto.mail.RequestMailDO
import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.MailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MailRestApi(
    private val mailService: MailService
) {
    @PostMapping("/mail/send-code")
    fun sendCode(@RequestBody data: RequestMailDO):String{
        println(data.email)
        return mailService.validateMail(data.email)
    }
}