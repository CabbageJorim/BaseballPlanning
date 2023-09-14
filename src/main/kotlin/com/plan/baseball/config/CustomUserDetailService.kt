package com.plan.baseball.config

import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userInfoRepository: UserInfoRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userInfoRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.: $username")

        val authorities = listOf<GrantedAuthority>()

        return User(user.email, user.password, authorities)
    }
}