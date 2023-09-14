package com.plan.baseball.model.service

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder:PasswordEncoder
) {
    fun register(userInfoDO: UserInfoDO){
        //TODO: passwordEncoding 변환
        userInfoDO.password = passwordEncoder.encode(userInfoDO.password)
        userInfoRepository.save(userInfoDO)
    }

    fun selectByTel(tel: String): UserInfoDO? {
        return userInfoRepository.findByTel(tel)
    }
}