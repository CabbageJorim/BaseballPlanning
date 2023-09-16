package com.plan.baseball.model.service

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder:PasswordEncoder
) {
    fun register(userInfoDO: UserInfoDO){
        userInfoDO.password = passwordEncoder.encode(userInfoDO.password)
        userInfoRepository.save(userInfoDO)
    }

    fun selectByTel(tel: String): UserInfoDO? {
        return userInfoRepository.findByTel(tel)
    }

    fun selectByEmail(email:String): UserInfoDO?{
        return userInfoRepository.findByEmail(email)
    }

    fun modify(data: HashMap<String, String>): Int {
        var result:Int
        try {
            val userInfo = data["email"]?.let { userInfoRepository.findByEmail(it) }
            if (userInfo != null){
                userInfo.password = passwordEncoder.encode(data["password"])
                userInfoRepository.save(userInfo)
                result = 1
            }else{
                result = 0
            }
        }catch (e:Exception){
            result = -1
            print(e.message)
        }
        return result
    }
}