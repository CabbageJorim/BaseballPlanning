package com.plan.baseball.model.dto

import javax.persistence.Entity

data class MessageDTO(
    val to:String,
    val content:String
) {
}