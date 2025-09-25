package org.server

//Базовый синтаксис

fun main() {
    var a = 10; //изменяемая
    val b = 20; //неизменяемая

    //b = 30; ошибка
    a = 5;

    println("$a $b");


    //Указание типа
    val c : Long = 1;
    println(c);


    //Создание объекта (нет new)
    val list = ArrayList<Int>();
    list.add(a);
    list.add(b);
    list.add(c.toInt());

    println(list);


    //Функции
    val functions = listOf(::add, ::sub, ::mul);

    println(functions[0](10,20));
    println(doOp(5, 10, ::mul));

    meowQ();
    meow();


    //Строковые шаблоны
    println("Без " + "Шаблонов");
    val s = "meow";
    println("Шаблон: $s");
    println("Выражение: ${15 + 10}");

    //Условные выражения
    val i = if (a > b) a else b;
    println(i);

    var x = 10;

    when (x) {
        0 -> println("x is 0");
        in 1..10 -> println("x is in 1..10");
        !in 11..20 -> println("x is not in 11..20");
        else -> println("other");
    }

//    val obj = "123";
//    when (obj) {
//        is Int -> println("is Int");
//        is String -> println("is String");
//        else -> println("other");
//    }
//
//    var y = 1;
//    when {
//        y is Long -> println("is Long");
//        y > 0 && y < 10 -> println("0 < y < 10");
//    }
}

fun add(a:Int, b:Int) : Int = a + b;

fun sub(a:Int, b:Int) = a - b; //автоматически определяет возвращаемый тип

fun mul(a:Int, b:Int): Int { //блочная функция не может автоматически определить возвращаемый тип
    return a * b;
}

fun doOp(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b);
}

fun meow() {
    println("MEOW!!!");
}

fun meowQ() : Unit {
    println("meow?");
}