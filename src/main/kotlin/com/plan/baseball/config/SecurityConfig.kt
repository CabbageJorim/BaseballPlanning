package com.plan.baseball.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: CustomUserDetailService,
    private val customAuthenticationProvider: CustomAuthenticationProvider,
    @Autowired
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    @Autowired
    private val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf().disable()
        http
            .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
        http
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/action")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()
        http
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()

        return http.build()
    }

    @Bean
    fun requestCache(): RequestCache {
        return HttpSessionRequestCache()
    }
}