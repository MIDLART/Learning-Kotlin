package org.server.springapp

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MyRepository : JpaRepository<SQLEntity, Long> {
    fun findByStr(str: String): List<SQLEntity>
}