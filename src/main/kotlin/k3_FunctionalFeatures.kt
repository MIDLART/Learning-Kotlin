package org.server

//Функциональные возможности

fun main() {
    //Лямбда
    val doubler = fun(x: Int): Int { return x * 2 } //без лямбды
    val doubler2: (Int) -> Int = { x -> x * 2 } //с лямбдой (эквивалентно)
    val doubler3: (Int) -> Int = { it * 2 } //с неявным параметром it


    //Функции высшего порядка
    val nums = listOf(1, 2, 3, 4, 5)

    val even = nums.filter { it % 2 == 0 } //фильтрация элементов
    val squared = nums.map { it * it } //преобразование элементов

    nums.forEach { println(it) } //выполнение действия для каждого элемента

    val sum = nums.reduce { sum, element -> sum + element } //свертка коллекции в одно значение
    println(sum)
    val sum2 = nums.fold(1) { sum, element -> sum + element } //то же, что reduce, но с начальным значением
    println(sum2)


    //Функции-расширения
    fun List<Int>.avg() : Double { //функции можно объявлять внутри других функций
        if (isEmpty()) return 0.0
        return sum().toDouble() / size
    }

    val avg = nums.avg()
    println(avg)


    //Inline функции
    inlFun(doubler) //Этот код будет "встроен" и не создаст объект лямбды


    //Scope Functions
    val s = null
    data class Person(var name: String, var age: Int)
    val person = Person("Alice", 25)

    //let выполняет блок кода для объекта в виде лямбда-выражения
    s.let { println(it) }

    //аналогично also - функция,
    //которая позволяет выполнять дополнительные операции над объектом,
    //но при этом возвращает оригинальный объект.
    s.also { value -> println("Обрабатываем: $value") }

    //apply - настройка объекта (возвращает this)
    person.apply {
        age += 5
        name = "ALICE"
    }
    println(person)

    //with - работа с объектом как с получателем
    with(person) {
        println("$name is $age years old")
    }

    //run
    val text = "Hello".run {
        println(this.length)
        println(length)
        uppercase()
    }
    println(text)


    //Sequence
    val result = nums.asSequence() //Для ленивых вычислений
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(1)
        .toList()
    println(result)
}

//Inline функции
inline fun inlFun(f: (Int) -> Int) { //inline функции нельзя объявить внутри другой функции
    println("Inline функция")
    println(f(10))
}


//Функциональные типы и typealias
typealias StrMap = (String) -> String

fun processText(text: String, mapper: StrMap): String { //Str Map вместо (String) -> String
    return mapper(text)
}
