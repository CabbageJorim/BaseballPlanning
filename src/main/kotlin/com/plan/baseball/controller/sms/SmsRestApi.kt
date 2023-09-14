package com.plan.baseball.controller.sms

import com.plan.baseball.model.dto.sms.MessageDTO
import com.plan.baseball.model.dto.sms.SmsResponseDTO
import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.AccountService
import com.plan.baseball.model.service.SmsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SmsRestApi(
    private val smsService: SmsService,
    private val accountService: AccountService
) {
    @PostMapping("/sms/send")
    fun sendSms(@RequestBody messageDTO: MessageDTO): SmsResponseDTO? {
        return smsService.sendSms(messageDTO)
    }

    @GetMapping("/sms/code")
    fun getCode(): String{
        return smsService.createCode()
    }

    @GetMapping("/sms/check-dup")
    fun checkDup(@RequestParam tel:String): UserInfoDO? {
        return accountService.dupTelCheck(tel)
    }
}