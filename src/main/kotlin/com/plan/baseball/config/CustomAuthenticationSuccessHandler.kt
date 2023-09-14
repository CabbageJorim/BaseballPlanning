package com.plan.baseball.config

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    private val requestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        // 이전에 요청한 URL 정보를 가져옴
        val savedRequest = requestCache.getRequest(request, response)

        // 이전 페이지 정보가 있다면 해당 URL로 리디렉션, 없다면 기본 URL로 리디렉션
        val targetUrl = savedRequest?.redirectUrl ?: "/"

        // 성공한 URL로 리디렉션
        response?.sendRedirect(targetUrl)
    }
}
