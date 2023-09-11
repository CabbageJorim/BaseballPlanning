package com.plan.baseball.model.dto.user_info

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import javax.persistence.*

@Entity
data class UserInfo(
    @Id
    @Column(length = 256)
    val email:String,
    @Column(nullable = false, length = 512)
    val password:String,
    @Column(nullable = false, length = 21)
    val name:String,
    @Column(nullable = false, columnDefinition = "DATE")
    val birth:Calendar,
    @Column(nullable = false, length = 13)
    val tel:String,
    @Column(nullable = false)
    val createDate:LocalDateTime
) {
}