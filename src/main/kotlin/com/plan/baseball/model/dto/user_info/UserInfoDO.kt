package com.plan.baseball.model.dto.user_info

import java.time.LocalDateTime
import java.util.Calendar
import javax.persistence.*

//TODO: 2023.09.11 Entity 생성 후 문제 발생 시 연락주세요. - Jorim
@Entity
@Table(name = "USER_INFO")
data class UserInfoDO(
    @Id
    @Column(length = 256, name = "email")
    val email:String,
    @Column(nullable = false, length = 512, name = "password")
    var password:String,
    @Column(nullable = false, length = 21, name = "name")
    val name:String,
    @Column(nullable = false, columnDefinition = "DATE", name = "birth")
    val birth:Calendar,
    @Column(nullable = false, length = 13, name = "tel")
    val tel:String,
    @Column(nullable = false, name = "createDate")
    var createDate:LocalDateTime?
) {
}