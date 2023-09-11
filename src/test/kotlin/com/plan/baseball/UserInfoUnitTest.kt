package com.plan.baseball

import com.plan.baseball.model.dto.user_info.UserInfo
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class UserInfoUnitTest(
    @Autowired
    val userInfoRepository: UserInfoRepository
) {
    @Test
    fun create(): Unit {
        val userData = UserInfo(
            "Test@naver.com", "1234", "Jorim", getCalendar(2000, 3, 30),"01085945142", LocalDateTime.now()
        )
        userInfoRepository.save(userData)
    }

    private fun getCalendar(year: Int, month: Int, date: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, date)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar
    }

    @Test
    fun read(): Unit{
        println(userInfoRepository.findByEmail("Test@naver.com").toString())
    }

    @Test
    fun delete(): Unit{
        userInfoRepository.deleteById("Test@naver.com")
        println("Delete Fin")
    }
}