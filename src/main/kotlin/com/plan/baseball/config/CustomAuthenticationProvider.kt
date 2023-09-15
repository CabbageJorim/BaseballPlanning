package com.plan.baseball.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userDetailsService: CustomUserDetailService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val userDetails = userDetailsService.loadUserByUsername(username)

        if (passwordEncoder.matches(password, userDetails.password)) {
            val authorities = userDetails.authorities
            return UsernamePasswordAuthenticationToken(userDetails, password, authorities)
        } else {
            throw BadCredentialsException("인증 실패")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
