package com.plan.baseball.model.dto.user_info

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserInfoRepository:JpaRepository<UserInfo, String> {
    fun findByEmail(email:String):UserInfo
}