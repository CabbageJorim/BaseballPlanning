package com.plan.baseball.model.service

import com.plan.baseball.model.dto.user_info.UserInfo
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val userInfoRepository: UserInfoRepository
) {
    fun register(userInfo: UserInfo){
        userInfoRepository.save(userInfo)
    }
}