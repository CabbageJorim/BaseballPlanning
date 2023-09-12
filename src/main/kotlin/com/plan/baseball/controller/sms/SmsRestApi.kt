package com.plan.baseball.controller.sms

import com.plan.baseball.model.dto.sms.MessageDTO
import com.plan.baseball.model.dto.sms.SmsResponseDTO
import com.plan.baseball.model.service.SmsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SmsRestApi(
    private val smsService: SmsService
) {
    @PostMapping("/sms/send")
    fun sendSms(@RequestBody messageDTO: MessageDTO): SmsResponseDTO? {
        return smsService.sendSms(messageDTO)
    }
}