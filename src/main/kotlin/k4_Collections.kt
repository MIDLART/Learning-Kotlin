package org.server

// Eclipse Collections
// зависимость implementation("org.eclipse.collections:eclipse-collections:11.0.0")
import org.eclipse.collections.api.list.primitive.*
import org.eclipse.collections.impl.factory.primitive.*
import java.util.*
import kotlin.collections.ArrayList

// Коллекции

fun main() {
    // Коллекции Kotlin - обертки над Java-коллекциями
    // Read-Only (неизменяемые коллекции)
    val readOnlyList: List<String> = listOf("a", "b", "c")
    val readOnlyListInt = listOf(1, 2, 3)
    println(readOnlyList[2]) //альтернатива get()

    val set = setOf("a", "b", "c")
    val map = mapOf(1 to "one", 2 to "two")
    println(map[1])

    // Read-only коллекции в Kotlin не всегда являются иммутабельными на уровне исполнения.
    // Если у вас есть read-only ссылка (List) на изменяемую коллекцию (MutableList),
    // то при изменении исходной коллекции изменится и read-only представление.


    // Mutable (изменяемые)
    // Эти интерфейсы наследуются от read-only и добавляют методы для изменения
    val mutableList: MutableList<String> = mutableListOf("a", "b", "c")
    mutableList.add("d")
    println(mutableList)

    val mutableSet: MutableSet<String> = mutableSetOf()
    val mutableMap = mutableMapOf<String, String>()
    mutableMap.put("key", "value")

    // list - это обёртка над ArrayList
    // set - LinkedHashSet
    // map - LinkedHashMap

    // hashSetOf - HashSet
    // hashMap - HashMap
    // sortedSetOf - TreeSet
    // sortedMapOf - TreeMap


    // Классы Java
    val arrayList = ArrayList<String>()
    arrayList.add("Kotlin")
    val treeSet = TreeSet<Int>()
    val treeMap = TreeMap<Int, String>()
    val queue: Queue<String> = LinkedList() //в стандартной библиотеке Kotlin нет собственных реализаций очередей
    val queueFromCollection: Queue<Int> = LinkedList(listOf(1, 2, 3))


    // Поиск элемента
    val words = listOf("apple", "banana", "cherry")

    // Поиск первого
    // Оба метода синонимы
    val firstLongWord = words.find { it.length > 5 } // "banana"
    val result = words.firstOrNull { it.startsWith("z") } // null (безопаснее first, который выбросит исключение)

    // Поиск последнего
    val LastLongWord = words.findLast { it.length > 5 } // "cherry"
    val result2 = words.lastOrNull { it.startsWith("a") } // "apple"


    // Проверка условий
    val numbers = listOf(1, 2, 3)

    // any - хотя бы 1
    val hasEven = numbers.any { it % 2 == 0 } // true
    // all - все
    val allEven = numbers.all { it % 2 == 0 } // false
    // none - ни одного
    val noZeros = numbers.none { it == 0 } // true


    // Группировка элементов по ключу
    val words2 = listOf("a", "abc", "ab", "b", "bc")
    val byLength: Map<Int, List<String>> = words2.groupBy { it.length }
    println(byLength) // {1=[a, b], 3=[abc], 2=[ab, bc]}


    // flatMap - преобразование каждого элемента в коллекцию, а затем "выравнивание" в один список.
    val list = listOf("Hello", "World")
    val letters = list.flatMap { it.toList() }
    println(letters) // [H, e, l, l, o, W, o, r, l, d]


    // Коллекции для примитивов
    val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val doubleArray: DoubleArray = doubleArrayOf(1.1, 2.2, 3.3)

    // Преобразования между типами
    // К объектному массиву (происходит упаковка)
    val objectArray: Array<Int> = intArray.toTypedArray()

    // К списку (происходит упаковка)
    val list2: List<Int> = intArray.toList()

    // Обратно из списка в массив примитивов
    val newArray: IntArray = list2.toIntArray()

    // Eclipse Collections: List для примитивов
    val intList: IntList = IntLists.immutable.with(1, 2, 3, 4, 5) //динамический массив
    val fastSum = intList.sum() // Без упаковки!

    val mutableIntList: MutableIntList = IntLists.mutable.empty()
    mutableIntList.add(10)
    mutableIntList.addAll(1, 2, 3)


    // Подсписки
    val fullList = listOf("a", "b", "c", "d", "e", "f")

    // метод subList() - возвращает "view" оригинального списка O(1)
    // Изменения в sublist влияют на оригинальный список
    val sublist = fullList.subList(1, 4) //правая граница не включается
    println(sublist) // [b, c, d]

    // метод slice() - возвращает новый список O(n)
    // По диапазону
    val slice1 = fullList.slice(1..3) // [b, c, d]
    println(slice1)
    val slice2 = fullList.slice(1 until 3) // [b, c]
    println(slice2)

    // По индексам
    val slice3 = fullList.slice(listOf(0, 2, 4)) // [a, c, e]
    println(slice3)
    val slice4 = fullList.slice(setOf(1, 3)) // [b, d]
    println(slice4)

    // С шагом через диапазон
    val slice5 = fullList.slice(0..fullList.lastIndex step 2) // [a, c, e]
    println(slice5)

    // методы take() и drop() - возвращают новый список O(n)
    val listNums = listOf(1, 2, 3, 4, 5)

    // take - взять первые N элементов
    val firstThree = listNums.take(3) // [1, 2, 3]
    // takeLast - взять последние N
    val lastThree = listNums.takeLast(3) // [3, 4, 5]

    // drop - пропустить первые N элементов
    val afterFirstThree = listNums.drop(3) // [4, 5, 6]
    // dropLast - отбросить последние N
    val withoutLastTwo = listNums.dropLast(2) // [1, 2, 3]

    // Комбинация
    val fromSecondToFourth = listNums.drop(1).take(3) // [2, 3, 4]


    // Создание списка через диапазон индексов
    val range = (1..5)
    println(range) // 1..5
    val rangeList = range.toList()
    println(rangeList) // [1, 2, 3, 4, 5]

    val chars = 'a'..'z'
    println(chars) // a..z


    // Преобразование коллекции в строку
    val wordslList = listOf("Hello", "world", "Kotlin")

    val str = wordslList.joinToString(" ")
    println(str) // "Hello world Kotlin"

    val strNums = listNums.joinToString("-")
    println(strNums) // "1-2-3-4-5"


    // Деструктуризация
    val listD = listOf("Alice", "Bob", "Charlie")
    val (first, second, third) = listD
    println("$first, $second, $third") // Alice, Bob, Charlie

    val mapD = mapOf("name" to "Alice", "age" to 25)
    val (key, value) = mapD.entries.first()
    println("$key, $value") // name, Alice


    // Операторы
    val listO = mutableListOf("a", "b", "c")
    listO += "d"
    listO -= "a"

    val mapO = mutableMapOf("a" to 1)
    mapO["b"] = 2
    val v = mapO["a"]


    // Окна и группировки
    val listW = listOf(1, 2, 3, 4, 5)

    // Скользящее окно
    val windows = listW.windowed(3)
    println(windows) // [[1,2,3], [2,3,4], [3,4,5]]

    // Разбиение на пары
    val pairs = listW.zipWithNext()
    println(pairs) // [(1,2), (2,3), (3,4), (4,5)]

    // Разделение по условию
    val (even, odd) = listW.partition { it % 2 == 0 }
    println(even) //[2, 4]
    println(odd)  //[1, 3, 5]


    // Минимаксные и статистические функции
    val numbers2 = listOf(5, 2, 8, 1, 9)

    println(numbers2.min())       // 1
    println(numbers2.maxOrNull()) // 9
    println(numbers2.average())   // 5.0
    println(numbers2.sum())       // 25

    val strings = listOf("apple", "zoo", "banana")
    println(strings.minBy { it.length }) // "zoo"
    println(strings.maxWith(compareBy { it.first() })) // "zoo"


    // Генераторы последовательностей
    val infinite = generateSequence(1) { it + 1 } // seed - начальное значение
    val first10 = infinite.take(10).toList()
    println(first10) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


    // Ассоциации
    val keys = listOf("a", "b", "c")
    val values = listOf(1, 2, 3)

    val map1 = keys.associateWith { it.length } // {a=1, b=1, c=1}
    println(map1)
    val map2 = keys.associateBy { it.uppercase() } // {A=a, B=b, C=c}
    println(map2)
    val map3 = keys.zip(values).toMap() // {a=1, b=2, c=3}
    println(map3)


    // Модификации in-place
    val mutableList2 = mutableListOf(1, 2, 3, 4, 5)

    mutableList2.removeAll { it % 2 == 0 } // удаляет четные [1, 3, 5]
    mutableList2.retainAll { it > 2 }     // оставляет только >2 [3, 5]

    mutableList2.sort()                   // [3, 5]
    mutableList2.sortDescending()         // [5, 3]
    println(mutableList2)


    // Конвертация в Java Stream
    val stream = list.stream()           // Java Stream
    val parallel = list.parallelStream() // Параллельный stream

    // Обратно
    val fromStream = stream.toList()
}