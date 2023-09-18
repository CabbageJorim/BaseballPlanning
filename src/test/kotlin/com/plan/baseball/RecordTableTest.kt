package com.plan.baseball

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecord
import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.user_info.UserInfoDO
import com.plan.baseball.model.dto.user_info.UserInfoRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class RecordTableTest(
    @Autowired val passwordEncoder: PasswordEncoder,
    @Autowired private val userInfoRepository: UserInfoRepository,
    @Autowired private val batterBasicSeasonRecordRepository: BatterBasicSeasonRecordRepository
) {
    @Test
    fun createUser(){
        val userData = UserInfoDO(
            "wkdgyfla97@naver.com", passwordEncoder.encode("1234"), "Jorim", getCalendar(2000, 3, 30),"01085945143", LocalDateTime.now()
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
    fun createBasicData(){
        val batterBasicSeasonRecord= userInfoRepository.findByEmail("wkdgyfla97@naver.com")?.let {
            BatterBasicSeasonRecord(
                userInfoDO = it,
                game = 1,
                pa = 1,
                ab = 1,
                run = 1,
                hit = 1,
                homerun = 1,
                rbi = 1,
                sb = 1,
                bb = 1,
                kk = 1
            )
        }
        if (batterBasicSeasonRecord != null) {
            batterBasicSeasonRecordRepository.save(batterBasicSeasonRecord)
        }
    }

    @Test
    fun readBasicData(){
        println(batterBasicSeasonRecordRepository.findByUserInfoDOEmail("wkdgyfla97@naver.com").toString())
    }

    @Test
    fun deleteBasicData(){
        batterBasicSeasonRecordRepository.deleteById(1)
        println(batterBasicSeasonRecordRepository.findAll())
    }
}