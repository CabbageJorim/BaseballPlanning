package com.plan.baseball

import com.plan.baseball.model.dto.data.BatterBasicSeasonRecordRepository
import com.plan.baseball.model.dto.team.UserTeamRepository
import com.plan.baseball.model.dto.team.TeamDO
import com.plan.baseball.model.dto.team.TeamRepository
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
    @Autowired private val teamRepository: TeamRepository,
) {
    @Test
    fun createUser(){
        val userData = UserInfoDO(
            "lsd@naver.com", passwordEncoder.encode("1234"), "Jorim", getCalendar(1997, 3, 30),"01085945142", LocalDateTime.now()
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
    fun createTeam(){
        val team = TeamDO(
            name = "GwanAk pirates"
        )
        teamRepository.save(team)
    }
}