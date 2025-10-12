package org.server

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Корутины

// Корутины — это легковесные потоки (нити),
// которые позволяют писать асинхронный код в последовательном стиле.

// Основные концепции:
// 1. Легковесность
//    Корутины намного эффективнее потоков:
//      Занимают мало памяти (десятки килобайт против мегабайтов у потоков)
//      Можно создавать тысячи корутин одновременно
//      Быстрый запуск и переключение контекста
// 2. Структурированный параллелизм
//    Корутины следуют принципу структурированного параллелизма:
//      Родительская корутина ждет завершения всех дочерних
//      Отмена родителя отменяет всех детей
//      Ошибка в дочерней корутине может отменить родителя


// Запуск корутины
fun coroutineFun() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
// Вывод:
// Hello,
// World!


fun main() = runBlocking { // runBlocking - блокирует текущий поток до завершения
    coroutineFun()


    // launch - запускает корутину без возврата результата
    val job = launch {
        // асинхронная работа
        delay(100L)
        println("Job completed")
    }
    println(job) // StandaloneCoroutine{Active}@7cdbc5d3


    // async - запускает корутину с возвратом результата
    val deferred: Deferred<Int> = async {
        delay(1000L)
        return@async 10 // можно не писать return@async
    }
    val result = deferred.await()
    println(result) // 10


    // Диспетчеры
    // Они определяют, на каком потоке будет выполняться корутина.
    fun d () {
        println("Main thread: ${Thread.currentThread().name}")
        launch(Dispatchers.Default) { // По умолчанию, если явно не указан другой диспетчер.
            // CPU-intensive задачи
            delay(10L)
            println("Default: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            // I/O операции (сеть, файлы)
            delay(10L)
            println("IO: ${Thread.currentThread().name}")
        }
//        launch(Dispatchers.Main) { // если есть UI контекст
//            // Работа с UI (в Android)
//            delay(10L)
//            println("Main: ${Thread.currentThread().name}")
//        }
        launch(Dispatchers.Unconfined) {
            // Без привязки к конкретному потоку
            delay(10L)
            println("Unconfined: ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyThread")) {
            // Собственный поток
            delay(10L)
            println("MyThread: ${Thread.currentThread().name}")

        }
    }
    d()


    // Отмена корутин
    val job2 = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i...")
                delay(500L)
            }
        } catch (e: CancellationException) {
            println("job: I was cancelled")
            throw e
        } finally {
            // Блок finally выполнится даже при отмене
            println("job: I'm running finally")
        }
    }

    delay(1300L)
    println("main: I'm tired of waiting!")
    job2.cancelAndJoin() // Отмена и ожидание завершения


    // Scope
    // это контекст, в котором запускаются и управляются корутины
    // Он содержит:
    //      Диспетчер (CoroutineDispatcher)
    //      Job для управления жизненным циклом
    //      Обработчик исключений (CoroutineExceptionHandler)

    // GlobalScope
    GlobalScope.launch { // Живет все время работы приложения
        delay(1000L)
        println("GlobalScope coroutine")
    }
    Thread.sleep(2000L)

    // runBlocking
    //      Блокирует текущий поток до завершения
    //      Используется в main функциях и тестах

    // CoroutineScope - кастомный scope
    // Создаёт временную область видимости, не блокирует текущий поток, а лишь приостанавливает его.
    val customScope = CoroutineScope(Dispatchers.Default + Job() + CoroutineName("MyScope"))

    customScope.launch {
        delay(100L)
        println("Custom scope coroutine")
    }
    // Если Scope создается как поле класса - его нужно очищать при уничтожении этого класса.

    // Job — контролирует жизненный цикл.
    // Job (по умолчанию) - Ошибка в одном ребенке → отменяет всех
    // SupervisorJob - Ошибка в одном ребенке → не влияет на других


    // Обработка исключений
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    val scope = CoroutineScope(Job() + Dispatchers.Default + handler)
    fun e() {
        scope.launch {
            throw RuntimeException("Something went wrong!")
        }
    }
    e() // Caught java.lang.RuntimeException: Something went wrong!


    // В отличие от Thread.sleep приостанавливает корутину, но не поток
    delay(1000L)


    // Suspend функции
    // Это функции, которые могут быть приостановлены и возобновлены без блокировки потока.
    suspend fun getData1(): Int {
        delay(1000)
        return 5
    }

    suspend fun getData2(data1: Int, name: String): String {
        delay(500)
        return "Результат: $data1, имя: $name"
    }

    val data1: Int = getData1()
    println(data1)
    val data2: String = getData2(data1, "name")
    println(data2)


    // Flow<T>
    // Это cold stream (холодный поток) данных, который может эмитить множественные значения over time.
    fun getNumbers(): Flow<Int> = flow {
        // flow builder
        for (i in 1..5) {
            delay(1000L) // Асинхронная работа
            emit(i)      // Отправка значения
        }
    }

    getNumbers()
        .collect { value ->  // Коллектор (подписчик)
            println("Received: $value")
        }
}