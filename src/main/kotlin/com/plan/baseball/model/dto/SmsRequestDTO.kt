package com.plan.baseball.model.dto

data class SmsRequestDTO(
    val type:String,
    val contentType:String,
    val countryCode:String,
    val from:String,
    val content:String,
    val messages:List<MessageDTO>
) {

}