package com.plan.baseball.model.dto.user_info

import org.springframework.data.jpa.repository.JpaRepository


interface UserInfoRepository:JpaRepository<UserInfoDO, String> {
    fun findByEmail(email:String):UserInfoDO
}