package org.server

//Null Safety

fun main() {
    //Nullable types
    var a: Int? = null //Type? означает, что переменная может быть null
    //var s: String = null //не может быть null


    //Safe call operator
    var s: String? = null
    val len: Int? = s?.length //если s = null, вернет null


    //Значение по умолчанию
    val safeLen = len ?: 0 //если null, вернет 0


    //Non-null assertion
    val s2 = "s"
    val forcedLength = s2!!.length  // если null - NullPointerException


    //Безопасное приведение типа
    val value = 123
    val stringValue: String? = value as? String  // если не String - null
    println(stringValue)


    //Безопасная работа с коллекциями
    val list: List<Int?> = listOf(1, 2, null, 4)
    println(list)
    val nonNullList: List<Int> = list.filterNotNull()
    println(nonNullList)


    //let + safe call
    //let выполняет блок кода для объекта в виде лямбда-выражения
    s?.let {
        // выполнится только если s != null (за счёт safe call)
        println(it.length)  // it = не-null s
    }

    //аналогично also - функция,
    //которая позволяет выполнять дополнительные операции над объектом,
    //но при этом возвращает оригинальный объект.
    s2?.also { value ->
        println("Обрабатываем: $value")
    }


    //Функции для работы с null
    val nullableString: String? = null
    val safeString: String = nullableString.orEmpty()  // ""

    val number = 0
    val result = number.takeIf { it > 0 }  // вернет number если > 0, иначе null
    val result2 = number.takeUnless { it <= 0 }  // вернет number если не <= 0
    println(result)
    println(result2)
}