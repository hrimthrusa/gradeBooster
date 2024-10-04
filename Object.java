@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    MyClass myClass = (MyClass) obj;
    return field == myClass.field;  // Сравниваем содержимое
}

protected Object clone()
Этот метод создаёт и возвращает копию объекта.
По умолчанию он использует механизм побитового копирования (shallow copy). Для использования этого метода класс должен явно реализовывать интерфейс Cloneable, иначе метод выбрасывает исключение CloneNotSupportedException.

Пример полного переопределения equals() и hashCode()
import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);  // Хэш-код генерируется на основе полей
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}
Теперь объекты Person, которые равны по значению полей, будут иметь одинаковые хэш-коды и корректно работать в коллекциях.
