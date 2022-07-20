package com.company;

public class JvmComprehension {         // Область памяти Metaspace (данные о классе)
// Сперва происходит подгрузка класса через подсистему загрузчиков классов. ClassLoader'ы: Application -- Platform --
//    -- Bootstrap потом происходит связывание Linking: проверка -> подготовка -> разрешение символьных ссылок.
//    После следует инициализация. Выполняются static инициализаторы.
    public static void main(String[] args) {
        int i = 1;                      // Переменная i -> Фрейм(кадр) №1 в стеке
        Object o = new Object();        // new Object() -> в heep(куча); Ссылка на объект o -> Фрейм №1 в стеке
        Integer ii = 2;                 // Integer -> в куча; Ссылка на ii -> Фрейм №1 в стеке
        printAll(o, i, ii);             // Создается фрейм №2 в стеке + ссылки на ii, о + Переменная i в стеке
        System.out.println("finished"); // Создается фрейм №3 в стеке и после выполнения sout фрейм №3 удаляется
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // Integer -> в куча; Ссылка на uselessVar -> Фрейм №2 в стеке
        System.out.println(o.toString() + i + ii);  // Создается фрейм №3 в стеке; Ссылка на объект o -> Фрейм №3 в стеке
    }       //Создается фрейм №4 в стеке для toString и после выполнения фрейм №4 удаляется;
            // Переменная i -> Фрейм №3 в стеке. Ссылка на ii -> Фрейм №3 в стеке.
            // Далее, после выполнения sout, Фрейм №3 удаляется.
            // После выполнения printAll, Фрейм №2 удаляется.
            // И, наконец, после выполнения main, Фрейм №1 удаляется.
            // Периодически сборщик мусора собирает объекты из памяти в кучи(хипа).
            // В текущий момент сборщик мусора удалит: Object o, Integer ii, Integer uselessVar.
}