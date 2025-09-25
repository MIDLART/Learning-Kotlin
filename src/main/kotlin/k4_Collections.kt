package org.server

//Eclipse Collections
//зависимость implementation("org.eclipse.collections:eclipse-collections:11.0.0")
import org.eclipse.collections.api.list.primitive.*
import org.eclipse.collections.impl.factory.primitive.*
import java.util.*
import kotlin.collections.ArrayList

//Коллекции

fun main() {
    //Коллекции Kotlin - обертки над Java-коллекциями
    //Read-Only (неизменяемые коллекции)
    val readOnlyList: List<String> = listOf("a", "b", "c")
    val readOnlyListInt = listOf(1, 2, 3)
    println(readOnlyList[2]) //альтернатива get()

    val set = setOf("a", "b", "c")
    val map = mapOf(1 to "one", 2 to "two")
    println(map[1])

    //Read-only коллекции в Kotlin не всегда являются иммутабельными на уровне исполнения.
    // Если у вас есть read-only ссылка (List) на изменяемую коллекцию (MutableList),
    // то при изменении исходной коллекции изменится и read-only представление.


    //Mutable (изменяемые)
    //Эти интерфейсы наследуются от read-only и добавляют методы для изменения
    val mutableList: MutableList<String> = mutableListOf("a", "b", "c")
    mutableList.add("d")
    println(mutableList)

    val mutableSet: MutableSet<String> = mutableSetOf()
    val mutableMap = mutableMapOf<String, String>()
    mutableMap.put("key", "value")

    //list - это обёртка над ArrayList
    //set - LinkedHashSet
    //map - LinkedHashMap

    //hashSetOf - HashSet
    //hashMap - HashMap
    //sortedSetOf - TreeSet
    //sortedMapOf - TreeMap


    //Классы Java
    val arrayList = ArrayList<String>()
    arrayList.add("Kotlin")
    val treeSet = TreeSet<Int>()
    val treeMap = TreeMap<Int, String>()


    //Поиск элемента
    val words = listOf("apple", "banana", "cherry")

    //Поиск первого
    //Оба метода синонимы
    val firstLongWord = words.find { it.length > 5 } // "banana"
    val result = words.firstOrNull { it.startsWith("z") } // null (безопаснее first, который выбросит исключение)

    //Поиск последнего
    val LastLongWord = words.findLast { it.length > 5 } // "cherry"
    val result2 = words.lastOrNull { it.startsWith("a") } // "apple"


    //Проверка условий
    val numbers = listOf(1, 2, 3)

    //any - хотя бы 1
    val hasEven = numbers.any { it % 2 == 0 } // true
    //all - все
    val allEven = numbers.all { it % 2 == 0 } // false
    //none - ни одного
    val noZeros = numbers.none { it == 0 } // true


    //Группировка элементов по ключу
    val words2 = listOf("a", "abc", "ab", "b", "bc")
    val byLength: Map<Int, List<String>> = words2.groupBy { it.length }
    println(byLength) // {1=[a, b], 3=[abc], 2=[ab, bc]}


    //flatMap - преобразование каждого элемента в коллекцию, а затем "выравнивание" в один список.
    val list = listOf("Hello", "World")
    val letters = list.flatMap { it.toList() }
    println(letters) // [H, e, l, l, o, W, o, r, l, d]


    //Коллекции для примитивов
    val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val doubleArray: DoubleArray = doubleArrayOf(1.1, 2.2, 3.3)

    //Преобразования между типами
    // К объектному массиву (происходит упаковка)
    val objectArray: Array<Int> = intArray.toTypedArray()

    // К списку (происходит упаковка)
    val list2: List<Int> = intArray.toList()

    // Обратно из списка в массив примитивов
    val newArray: IntArray = list2.toIntArray()

    //Eclipse Collections: List для примитивов
    val intList: IntList = IntLists.immutable.with(1, 2, 3, 4, 5) //динамический массив
    val fastSum = intList.sum() // Без упаковки!

    val mutableIntList: MutableIntList = IntLists.mutable.empty()
    mutableIntList.add(10)
    mutableIntList.addAll(1, 2, 3)

///val r = words.subList(1, words.size).joinToString(" ")
}