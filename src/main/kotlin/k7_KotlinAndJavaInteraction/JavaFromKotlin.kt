package org.server.k7_KotlinAndJavaInteraction

import k7_KotlinAndJavaInteraction.JavaClass

fun main() {
    // Класс
    // Создание объекта
    val cl = JavaClass("Scientific")

    // Вызов методов
    val result = cl.add(5, 3)

    // Статический метод
    val version = JavaClass.getVersion()

    // Работа с nullable (платформенные типы)
    val name: String? = cl.findNameById(2)
    name?.let { println(it) }

    // Геттеры становятся свойствами
    println(cl.model)
    // Сеттеры становятся присваиванием
    cl.model = "New model"


    // Null-безопасность
    // name будет non-null в Kotlin если в Java есть @NotNull
    println(cl.name.length)
    println(cl.email)
}