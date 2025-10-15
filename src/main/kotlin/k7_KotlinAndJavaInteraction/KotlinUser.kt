package k7_KotlinAndJavaInteraction

import java.io.IOException

class KotlinUser(val name: String, val age: Int) {
    fun greet() = "Hello, I'm $name"

    companion object {
        const val MAX_AGE = 100 // const используется для объявления констант времени компиляции

        fun createDefault() = KotlinUser("Default", 25)


        // Статические методы в Java не требуют обращения .Companion
        @JvmStatic
        fun add(a: Int, b: Int): Int = a + b
    }


    // Аннотации для улучшения совместимости Kotlin-Java
    // Изменение имени в байткоде
    @JvmName("getStringList") // С аннотацией в Java будет вызваться через getStringList()
    fun getList(): List<String> = listOf("a", "b", "c")

    // Генерирует перегрузки для функции, заменяя значения параметров, заданные по умолчанию
    @JvmOverloads
    fun show(message: String = "Hello", duration: Int = 1000) {
        println("$message for ${duration}ms")
    }

    // Прямой доступ к полям (по умолчанию Kotlin генерирует геттеры и сеттеры)
    @JvmField
    var field: Int = 0

    // Объявление проверяемых исключений (Kotlin не различает проверяемые и непроверяемые исключения)
    @Throws(IOException::class)
    fun readFile(path: String): String {
        if (path.isEmpty()) throw IOException("Empty path")
        return "File content"
    }

    // Скрытие элементов для Java
    @JvmSynthetic
    fun internalLogic() { /* для внутреннего использования */ }

    // wildcard
    // С wildcard - List<? extends String> в Java
    fun processList(list: List<@JvmWildcard String>) { }

    // Без wildcard - List<String> в Java
    fun processExactList(list: List<@JvmSuppressWildcards String>) { }
}