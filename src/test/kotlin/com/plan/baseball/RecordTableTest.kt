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
import com.plan.baseball.model.dto.team.UserTeamDO as TeamEntry

@SpringBootTest
class RecordTableTest(
    @Autowired val passwordEncoder: PasswordEncoder,
    @Autowired private val userInfoRepository: UserInfoRepository,
    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val userTeamRepository: UserTeamRepository,
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
    fun createTeam(){
        val team:TeamDO = TeamDO(
            name = "secondTeam"
        )
        teamRepository.save(team)
    }

    @Test
    fun createEntry(){
        println(userInfoRepository.findByEmail("wkdgyfla97@naver.com"))
        println(teamRepository.findById(1).get())
        val entry =
            TeamEntry(
                userInfoDO = userInfoRepository.findByEmail("wkdgyfla97@naver.com"),
                teamDO = teamRepository.findById(2).get()
            )
        userTeamRepository.save(entry)
    }

}