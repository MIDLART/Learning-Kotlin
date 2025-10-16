package org.server

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Делегирование

// Делегирование классов
interface Repository {
    fun getData(): String
}

class NetworkRepository : Repository {
    override fun getData() = "Data from network"
}

class CachedRepository(private val repository: Repository) : Repository by repository {
    // Можно добавить дополнительную логику
}


fun main() {
    // Делегирование свойств

    // lazy - отложенная инициализация
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    println(lazyValue) // Вычисление произойдет только здесь
    println(lazyValue) // Значение берется из кэша


    // observable - наблюдение за изменениями
    class User {
        var name: String by Delegates.observable("<no name>") {
                _, old, new ->
            println("$old -> $new")
        }
    }

    val user = User()
    user.name = "first"
    user.name = "second"

    // vetoable - наблюдение за изменениями с возможностью вето
    class PositiveNumber {
        var value: Int by Delegates.vetoable(0) { _, old, new ->
            if (new >= 0) {
                println("Изменение принято: $old -> $new")
                true
            } else {
                println("Отрицательное значение $new отклонено")
                false
            }
        }
    }

    val number = PositiveNumber()
    number.value = 10  // Принято
    number.value = -5  // Отклонено
    println(number.value) // Останется 10


    // Создание собственных делегатов для свойств
    class RangeDelegate( // Делегат для ограничения диапазона значений
        private var value: Int,
        private val min: Int = Int.MIN_VALUE,
        private val max: Int = Int.MAX_VALUE
    ) {
        // Any - это аналог Object, корневой тип всей иерархии классов в Kotlin.
        // KProperty - это класс из Kotlin Reflection, который содержит метаинформацию о свойстве.
        // <*> - это star projection, означает "любой тип".

        operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = value

        operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
            if (newValue in min..max) {
                value = newValue
            } else {
                println("Ошибка: значение $newValue вне диапазона [$min, $max]")
            }
        }
    }

    class GameCharacter {
        var health: Int by RangeDelegate(100, 0, 100)
        var level: Int by RangeDelegate(1, 1, 100)
    }

    val character = GameCharacter()

    character.health = 80
    println("Здоровье: ${character.health}") // Здоровье: 80

    character.health = 150 // Ошибка: значение 150 вне диапазона [0, 100]
    println("Здоровье осталось: ${character.health}") // Здоровье осталось: 80
}