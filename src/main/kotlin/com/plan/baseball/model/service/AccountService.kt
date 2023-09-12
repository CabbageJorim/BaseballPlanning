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

    /**
     * 로그인 시 작성한 email으로 데이터베이스 검색
     * 반환값이 null이면 없는 이메일, 입력한 비밀번호와 데이터베이스에서 가져온 비밀번호가 다르면 잘못된 비밀번호 입력
     */
    fun loginUser(email: String, password: String): Boolean {
        val user = userInfoRepository.findByEmail(email)

        return user != null && password == user.password
    }
}