package com.plan.baseball.model.dto

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Test(
    @Id
    val email:String,
    @Column(length = 255)
    val name:String
) {
}