package com.plan.baseball.controller

import com.plan.baseball.model.dto.sms.MessageDTO
import com.plan.baseball.model.dto.sms.SmsResponseDTO
import com.plan.baseball.model.service.SmsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TestController(
    private val smsService: SmsService
) {
    @GetMapping("/")
    fun testPage():String{
        return "test"
    }

    @GetMapping("/sms")
    fun sendPage(): String{
        return "send"
    }
    @PostMapping("/sms/send")
    fun sendSms(messageDTO: MessageDTO, model:Model): String{
        println(messageDTO.toString())
        val response: SmsResponseDTO? = smsService.sendSms(messageDTO)
        return "redirect:/"
    }
}