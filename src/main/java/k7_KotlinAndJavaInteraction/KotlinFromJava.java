package k7_KotlinAndJavaInteraction;

import org.server.k7_KotlinAndJavaInteraction.StringUtilsKt;

import java.util.List;

public class KotlinFromJava {
  public static void main(String[] args) {
    // Класс
    // Создание объекта
    KotlinUser user = new KotlinUser("John", 30);

    // Вызов метода
    String greeting = user.greet();
    System.out.println(greeting);

    // Статические поля и методы через Companion
    int maxAge = KotlinUser.MAX_AGE;
    KotlinUser defaultUser = KotlinUser.Companion.createDefault();


    // Функции верхнего уровня
    String result = StringUtilsKt.someFun("text"); // к классу добавилось окончание Kt
    System.out.println(result);
    String result2 = StringUtilsKt.MEOW;
    System.out.println(result2);


    // Тестирование аннотаций для улучшения совместимости Kotlin-Java
    // @JvmName
    List<String> list = user.getStringList();
    // @JvmOverloads
    user.show();
    user.show("Hi");
    user.show("Hi", 5000);
    // @JvmField
    user.field = 1;
    System.out.println(user.field);
    // @JvmStatic
    int r = KotlinUser.add(1, 2);
  }
}
