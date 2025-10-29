package org.server.springapp

import jakarta.persistence.*

@Entity
@Table(name = "my_table")
// Hibernate создает прокси-классы для ленивой загрузки, поэтому сущности должны быть не-final.
// Альтернативное решение: kotlin-allopen plugin
open class SQLEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Должен быть nullable

    var str: String = "")
// Тк пустой конструктор не генерируется, задаём дефолтные значения полей