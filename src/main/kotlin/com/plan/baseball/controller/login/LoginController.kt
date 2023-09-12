package com.plan.baseball.controller.login

import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.service.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class LoginController {
    @Autowired
    private lateinit var loginService: LoginService

    /**
     * localhost:8080/login
     */
    @GetMapping("/login")
    fun login(): String {
        return "account/login" // login.html 연결
    }

    /**
     * 아이디, 비밀번호를 입력한 후 로그인 버튼을 눌렀을 때
     * User_infoDO로 객체 주입해도 문제 없는 것으로 확인되어
     * email, password만 필드로 가지는 loginCommand를 사용할 필요 없음
     */
    @PostMapping("/login")
    fun tryLogin(user_infoDO: UserInfoDO, model: Model): String {
//      UserService의 loginUser method 호출(반환값 -> Boolean)
//      해당 결과에 따라 표출할 메시지 model을 통해 전달

        if (loginService.loginUser(user_infoDO.email, user_infoDO.password)) {
            model.addAttribute("msg", "로그인 성공!")
        }
        else {
            model.addAttribute("msg", "로그인 실패!")
        }

        return "account/login"
    }
}