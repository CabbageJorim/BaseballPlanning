package com.plan.baseball.model.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.plan.baseball.model.dto.MessageDTO
import com.plan.baseball.model.dto.SmsRequestDTO
import com.plan.baseball.model.dto.SmsResponseDTO
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.StringBuilder
import java.net.URI
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Service
class MobileService(
    @Value("\${naver-cloud-sms.accessKey}")
    private val accessKey:String,
    @Value("\${naver-cloud-sms.secretKey}")
    private val secretKey:String,
    @Value("\${naver-cloud-sms.serviceId}")
    private val serviceId:String,
    @Value("\${naver-cloud-sms.senderPhone}")
    private val phone:String
) {
    fun makeSignature(time: Long): String {
        val space = " "
        val method = "POST"
        val newLine = "\n"
        val url = "/sms/v2/services/" + this.serviceId + "/messages"
        val timeStamp = time.toString()
        val accessKey = this.accessKey
        val secretKey = this.secretKey

        val message = StringBuilder()
            .append(method)
            .append(space)
            .append(url)
            .append(newLine)
            .append(timeStamp)
            .append(newLine)
            .append(accessKey)
            .toString()

        val signKey = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "HmacSHA256")
        val mac: Mac = Mac.getInstance("HmacSHA256")
        mac.init(signKey)

        val rawHmac = mac.doFinal(message.toByteArray(Charsets.UTF_8))
        return Base64.encodeBase64String(rawHmac)
    }

    fun sendSms(messageDTO: MessageDTO): SmsResponseDTO? {
        val time: Long = System.currentTimeMillis()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("x-ncp-apigw-timestamp", time.toString())
        headers.set("x-ncp-iam-access-key", accessKey)
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time))

        val messages = ArrayList<MessageDTO>()
        messages.add(messageDTO)

        val request = SmsRequestDTO(
            "SMS", "COMM", "82", phone, messageDTO.content, messages
        )

        val objectMapper = ObjectMapper()
        val body = objectMapper.writeValueAsString(request)
        val httpBody = HttpEntity<String>(body, headers)
        val restTemplate = RestTemplate()
        restTemplate.setRequestFactory(HttpComponentsClientHttpRequestFactory())

        return restTemplate.postForObject(
            URI("https://sens.apigw.ntruss.com/sms/v2/services/$serviceId/messages"),
            httpBody,
            SmsResponseDTO::class.java
        )
    }
}