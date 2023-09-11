package com.plan.baseball.model.dto.user_info

import java.time.LocalDateTime
import java.util.Calendar
import javax.persistence.*

//TODO: 2023.09.11 Entity 생성 후 문제 발생 시 연락주세요. - Jorim
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
    var createDate:LocalDateTime?
) {
}