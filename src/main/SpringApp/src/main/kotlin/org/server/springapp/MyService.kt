package org.server.springapp

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MyService (
    private val repository: MyRepository
) {
    fun create(entity: SQLEntity) = repository.save(entity)

    fun exist(id: Long) : Boolean = repository.existsById(id)

    fun getById(id: Long) : SQLEntity? = repository.findById(id).orElse(null)

    fun delete(id: Long) : Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }

        return false
    }

    @Transactional
    fun update(id: Long, newStr: String) : Boolean {
        return repository.findById(id)
            .map {
                it.str = newStr
                // repository.save(it) не нужно - @Transactional сам сохранит изменения
                true
            }.orElse(false)
    }

    fun findByStr(str: String): List<SQLEntity> = repository.findByStr(str)
}