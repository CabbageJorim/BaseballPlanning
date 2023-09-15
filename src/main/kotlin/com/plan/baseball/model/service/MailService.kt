package com.plan.baseball.model.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage

@Service
class MailService(
    @Value("\${spring.mail.username}")
    private val sender:String,
    private val emailSender:JavaMailSender,
    private val smsService: SmsService,
) {
    companion object{
        private lateinit var key:String
    }
    fun validateMail(toMail:String): String{
        key = smsService.createCode()
        sendMail(toMail)
        return key
    }

    private fun sendMail(toMail:String) {
        val emailForm:MimeMessage = createMailForm(toMail)
        emailSender.send(emailForm)
    }

    private fun createMailForm(toMail: String): MimeMessage {
        val sender = "$sender@naver.com"
        val title = "이메일 인증"

        val message:MimeMessage = emailSender.createMimeMessage()
        message.addRecipients(MimeMessage.RecipientType.TO, toMail)
        message.subject = title
        message.setFrom(sender)
        message.setText("이메일 인증 번호: $key", "utf-8", "html")

        return message
    }
}