package com.plan.baseball.model.dto.team

import org.springframework.data.jpa.repository.JpaRepository

interface EntryRepository:JpaRepository<Entry, Long> {
    //fun findByUserInfoEmail(email:String): List<Entry>
}