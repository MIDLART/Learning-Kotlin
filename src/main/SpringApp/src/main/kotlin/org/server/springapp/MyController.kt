package org.server.springapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/my_api")
class MyController (
    private val service: MyService
) {
    data class DTO(val str: String)

    @GetMapping("/{id}")
    fun getById(@PathVariable id : Long) : ResponseEntity<SQLEntity> {
        return service.getById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody entity: DTO) = service.create(SQLEntity(str = entity.str))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        if (!service.delete(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody entity: DTO
    ) : ResponseEntity<String> {
        return if (service.update(id, entity.str)) {
            ResponseEntity.ok("The entity has been updated")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/find-by-str")
    fun findByStr(@RequestParam str: String) : List<SQLEntity> = service.findByStr(str)
}