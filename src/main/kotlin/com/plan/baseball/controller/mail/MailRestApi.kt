package com.plan.baseball.controller.mail

import com.plan.baseball.model.dto.mail.RequestMailDO
import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.MailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
/**
 * 이메일 인증 번호 발송에 한 함
 */
class MailRestApi(
    private val mailService: MailService
) {
    @PostMapping("/mail/send-code")
    fun sendCode(@RequestBody data: RequestMailDO):String{
        return mailService.validateMail(data.email)
    }
}