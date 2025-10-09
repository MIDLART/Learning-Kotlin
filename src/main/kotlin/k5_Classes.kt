package org.server

import java.util.*

//Классы


// Стиль классов Java
class Person {
    // Поля объявляются отдельно как в Java
    val name: String
    var age: Int
    private var salary: Double = 0.0

    // Конструкторы
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int, salary: Double) {
        this.name = name
        this.age = age
        this.salary = salary
    }

    // Методы
    fun display() {
        println("Name: $name, Age: $age")
    }
}


// Обычный класс
class PersonK(val name: String, var age: Int) {
    // Методы
}
// Автоматически генерируются:
//      Геттер для val полей
//      Геттер и сеттер для var полей
//      Конструктор с параметрами
//      equals() - сравнение по ссылкам (не по содержанию)
//      hashCode() - на основе адреса в памяти
//      toString() - в формате "Person@1a2b3c"


// data-класс
data class User(val name: String, var age: Int)
// Автоматически генерируются:
//      Геттер для val полей
//      Геттер и сеттер для var полей
//      Конструктор с параметрами
//      equals() - сравнение по всем свойствам
//      hashCode() - на основе всех свойств
//      toString() - "User(name=John, age=25)"
//      copy() - для создания копии с изменениями
//      componentN() - функции для деструктуризации


// Собственные конструкторы
// Первичный конструктор
class Person1(val name: String, val age: Int) { // Поля объявляются прямо в конструкторе
    init {
        println("Создан Person: $name, $age")
    }
}

class Person2(name: String, age: Int) { // С параметрами без полей (не указаны val или var)
    val personName: String
    var personAge: Int

    init {
        personName = name.uppercase()
        personAge = if (age > 0) age else 0
    }
}

// Вторичный конструктор
class Person3 {
    val name: String
    var age: Int

    // Конструктор по умолчанию
    constructor() {
        name = "Unknown"
        age = 0
    }

    // Конструктор с одним параметром
    constructor(name: String) {
        this.name = name
        this.age = 0
    }

    // Полный конструктор
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

// Комбинация первичного и вторичных конструкторов
class Person4(val name: String, var age: Int) {
    // Вторичный конструктор должен делегировать к первичному
    constructor(name: String) : this(name, 0) {
        println("Создан через вторичный конструктор")
    }

    constructor(age: Int) : this("Unknown", age)

    // Конструктор копирования
    constructor(other: Person) : this(other.name, other.age)
}

class Person5(
    // Некоторые поля в первичном конструкторе
    val id: Long,
    var email: String
) {
    // Другие поля объявляются отдельно
    var name: String = ""
    var age: Int = 0
    private var createdAt: String

    init {
        createdAt = java.time.LocalDateTime.now().toString()
    }

    // Вторичный конструктор
    constructor(id: Long, email: String, name: String, age: Int) : this(id, email) {
        this.name = name
        this.age = age
    }
}

// Конструкторы с валидацией
class PersonV(val age: Int) {
    init { // в constructor тоже работает
        require(age > 0) { "age must be positive" }
    }

    // val person = PersonV(-1) вызовет ошибку:
    // Exception in thread "main" java.lang.IllegalArgumentException: age must be positive
}


// Модификаторы доступа:
//      public (по умолчанию) - виден везде
//      internal - виден только внутри модуля
//      protected - виден в классе и наследниках
//      private - виден только в этом классе

// Модификаторы конструкторов
class PublicClass public constructor(val name: String) // public можно опустить

class InternalClass internal constructor(val name: String) // Конструктор доступен только внутри модуля

open class ProtectedClass protected constructor(val id: Int) // Защищенный конструктор - только для наследников

class PrivateClass private constructor(val secret: String) { // Приватный конструктор - только внутри класса
    // companion object используется для создания объекта-компаньона внутри класса.
    // Такой объект связан с классом, а не с его экземплярами,
    // и позволяет определить свойства и методы, общие для всех объектов этого класса.
    companion object {
        fun create(): PrivateClass {
            return PrivateClass("secret") // Доступно в companion object
        }
    }
}


// Модификаторы final и open
// По умолчанию все классы и методы являются final.
open class OpenClass { // Явно открыт для наследования
    // методы по умолчанию final
    fun finalMethod() { } // Нельзя переопределить
    final fun finalMethod2() { }

    open fun openMethod() { } // Можно переопределить
}

// Модификаторы final и open для полей в Kotlin определяют,
// можно ли переопределять свойства в дочерних классах.
// По умолчанию поля в Kotlin имеют модификатор final,
// но open позволяет явно указать, что свойство доступно для переопределения.
open class BankAccount {
    open val balance: Double = 0.0 // Можно переопределить
    open var accountNumber: String = "" // Можно переопределить

    val id: Long = 1L // Нельзя переопределить (final по умолчанию)
}


// override - ключевое слово для переопределения метода


// Собственные реализации стандартный методов
class Person6(name: String, var age: Int) {
    var name: String = name
        get() = "Name: $field"
        set(value) {
            if (value.isNotBlank()) {
                field = value.trim()
            }
        }

    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }

    fun copy(
        name: String = this.name,
        age: Int = this.age
    ) = Person(name, age)

    // componentN() - это специальные функции, которые позволяют
    // использовать деструктурирующее присваивание (destructuring declaration).
    operator fun component1(): String = name
    operator fun component2(): Int = age

    // Деструктурирующее присваивание
    // val (name, age) = person
}


fun main() {
    //Инициализация класса
    val person = PersonK("meow", 0)
    val person2 = PersonK(age = 10, name = "meow")

    //Геттеры и сеттеры
    person.age = 18
    println(person.name + " " + person.age)
}
