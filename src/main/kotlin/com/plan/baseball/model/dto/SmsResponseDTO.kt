package com.plan.baseball.model.dto

import java.time.LocalDateTime

data class SmsResponseDTO(
    val requestId:String,
    val requestTime:LocalDateTime,
    val statusCode:String,
    val statusName:String
) {
}