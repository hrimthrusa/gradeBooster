Enum поддерживает возможности класса: можно добавлять поля, методы и использовать перечисления в более сложных структурах.
Enum может иметь конструкторы, методы и поля. Пример с конструкторами:
public enum Day {
    MONDAY("Start of the work week"),
    FRIDAY("End of the work week"),
    SUNDAY("Rest day");

    private String description;

    // Конструктор
    Day(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Day.MONDAY.getDescription());  // Выведет: Start of the work week
    }
}
В этом примере у каждого элемента перечисления есть поле description, которое инициализируется при создании константы.
____________________________________________________
Enum может иметь абстрактные методы, которые должны быть реализованы каждой константой:
public enum Operation {
    ADD {
        @Override
        public int apply(int x, int y) {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        public int apply(int x, int y) {
            return x - y;
        }
    };

    public abstract int apply(int x, int y);
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Operation.ADD.apply(2, 3));  // Выведет 5
        System.out.println(Operation.SUBTRACT.apply(5, 3));  // Выведет 2
    }
}
Здесь у нас есть перечисление Operation, где каждая операция имеет свою реализацию метода apply.
_______________________________________________

Почему лучше использовать == для Enum?
Простота и ясность: Использование == явно указывает, что вы сравниваете ссылки (что безопасно для Enum, так как каждый элемент перечисления является синглтоном).
Эффективность: Оператор == быстрее, так как не вызывает дополнительных методов.
Гарантированная уникальность объектов: В случае Enum каждый элемент — это уникальный экземпляр, и поэтому проверка на эквивалентность через == всегда будет корректной и предпочтительной.
________________________
Почему нельзя создать экземпляр Enum вне самого Enum?
Природа Enum: Перечисления в Java — это по сути синглтоны, то есть каждая константа в Enum — это единственный экземпляр, который создаётся автоматически и доступен из перечисления. Например, в перечислении дней недели:
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}
Каждый день (например, MONDAY) создаётся только один раз внутри этого перечисления, и при обращении к Day.MONDAY вы всегда получаете один и тот же объект.
Конструктор Enum приватный: Когда вы создаёте перечисление в Java, его конструкторы по умолчанию становятся приватными, чтобы предотвратить создание новых экземпляров. Вы не можете вызвать конструктор перечисления извне или вручную.
В общем случае нельзя создавать новые экземпляры Enum даже через рефлексию или клонирование:
Клонирование: Попытка клонировать объект Enum вызовет исключение, так как Enum реализует интерфейс Cloneable, но сам метод clone() выбрасывает исключение CloneNotSupportedException.
Рефлексия: Даже при использовании рефлексии создание нового экземпляра Enum вручную невозможно, так как это запрещено на уровне JVM для обеспечения безопасности и целостности перечисления.

  _____________________

Enum может реализовывать интерфейс. Это довольно часто используется для того, чтобы перечисления имели общую функциональность, которую можно задать через интерфейс.
interface Displayable {
    void display();
}

public enum Day implements Displayable {
    MONDAY {
        @Override
        public void display() {
            System.out.println("Monday: Start of the week.");
        }
    },
    TUESDAY {
        @Override
        public void display() {
            System.out.println("Tuesday: Still early.");
        }
    },
    WEDNESDAY {
        @Override
        public void display() {
            System.out.println("Wednesday: Midweek.");
        }
    };

    // Абстрактный метод из интерфейса реализуется для каждого элемента Enum
}

Enum не может наследовать (расширять) другие классы, так как все перечисления в Java неявно наследуют класс java.lang.Enum. Поскольку Java не поддерживает множественное наследование классов, Enum не может наследовать другие классы.
Обходные пути:
Композиция вместо наследования: Вместо наследования класса, можно использовать композицию. Это означает, что если вам нужен функционал какого-то класса в Enum, можно включить его как поле в перечислении.
Пример композиции:

class MyHelperClass {
    public void helperMethod() {
        System.out.println("Helper method called");
    }
}

public enum Day {
    MONDAY, TUESDAY;

    private MyHelperClass helper = new MyHelperClass();

    public void useHelper() {
        helper.helperMethod();
    }
}
Здесь мы создаём поле helper типа MyHelperClass, и каждое значение Enum может использовать его методы.
