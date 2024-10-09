Обеспечивают возможность создания обобщенных коллекций и других структур данных, сохраняя при этом безопасность типов и избегая необходимости использовать приведение типов. Давайте подробнее рассмотрим реализацию Generics, включая стирание типов, Wildcards, а также принципы PECS.

1. Реализация Generics в Java
Generics были введены в Java 5 для обеспечения типобезопасности и улучшения читаемости кода. Использование Generics позволяет разработчикам определять параметризованные типы.

Пример обобщенного класса:
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
В этом примере T — это параметр типа, который может быть заменен конкретным типом при создании экземпляра класса Box.

Использование обобщенного класса:

Box<String> stringBox = new Box<>();
stringBox.setItem("Hello Generics");
String item = stringBox.getItem();

2. Стирание типов
Java использует механизм стирания типов (type erasure) для реализации Generics. Это означает, что информация о параметризованных типах не сохраняется в байт-коде, и при компиляции все параметры типов заменяются их границами или Object.

Как это работает:

При компиляции Java заменяет все параметры типа (например, T в Box<T>) на Object, если не указано иное.
Если задана граница (например, Box<T extends Number>), то T заменяется на Number.

public class Box<T extends Number> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
При компиляции этот код фактически преобразуется в:

public class Box {
    private Number item;

    public void setItem(Number item) {
        this.item = item;
    }

    public Number getItem() {
        return item;
    }
}

3. Wildcards
Wildcards в Java — это способ указания неопределенного типа в Generics. Существует три типа Wildcards:

Непараметризованный wildcard (?): может представлять любой тип.
Wildcard с верхней границей (? extends T): позволяет использовать типы, которые являются подтипами T.
Wildcard с нижней границей (? super T): позволяет использовать типы, которые являются надтипами T.
Примеры Wildcards:

// Непараметризованный wildcard
public void printItems(List<?> items) {
    for (Object item : items) {
        System.out.println(item);
    }
}

// Wildcard с верхней границей
public void processNumbers(List<? extends Number> numbers) {
    for (Number number : numbers) {
        System.out.println(number);
    }
}

// Wildcard с нижней границей
public void addNumbers(List<? super Integer> list) {
    list.add(10); // Можно добавлять Integer
}

4. PECS (Producer Extends, Consumer Super)
Принцип PECS помогает выбрать правильный wildcard, когда вы работаете с Generics:

Producer Extends: если вы хотите извлекать значения из структуры данных, используйте ? extends T. Это означает, что структура данных производит элементы типа T (или его подклассов).

Consumer Super: если вы хотите добавлять значения в структуру данных, используйте ? super T. Это означает, что структура данных потребляет элементы типа T (или его суперклассов).

Примеры PECS:

// Пример Producer Extends
public static double sum(List<? extends Number> numbers) {
    double sum = 0.0;
    for (Number number : numbers) {
        sum += number.doubleValue();
    }
    return sum;
}

// Пример Consumer Super
public static void addIntegers(List<? super Integer> list) {
    list.add(1); // можно добавлять Integer
    list.add(2);
}
Заключение
Generics в Java обеспечивают безопасность типов и удобство работы с коллекциями и другими обобщенными структурами данных. Стирание типов позволяет избежать проблем с производительностью, сохраняя совместимость с предыдущими версиями Java. Wildcards и принцип PECS помогают точно управлять типами при работе с обобщенными методами и классами, делая код более гибким и понятным.
