package com.plan.baseball.model.service

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val userInfoRepository: UserInfoRepository
) {
    fun register(userInfoDO: UserInfoDO){
        //TODO: passwordEncoding 변환
        userInfoDO.password = (userInfoDO.password)
        userInfoRepository.save(userInfoDO)
    }
}