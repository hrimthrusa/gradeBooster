Optional в Java — это класс, предназначенный для представления значения, которое может отсутствовать. Его основное предназначение — предотвращение NullPointerException и предоставление удобного API для работы с потенциально пустыми значениями. Класс Optional был добавлен в Java 8 и активно используется для повышения безопасности кода и более удобного управления значениями, которые могут быть null.

Зачем нужен Optional?
До появления Optional разработчики зачастую использовали null для обозначения отсутствующего значения. Это привело к частым ошибкам NullPointerException, поскольку разработчик мог случайно вызвать метод на null. С Optional эти ошибки можно сократить, так как он позволяет явно указать, что переменная может быть пустой и предоставляет методы для безопасной обработки этого случая.

Создание Optional
Существует несколько методов для создания экземпляров Optional:

Optional.of(T value)
Создает Optional, содержащий непустое значение value. Если value — null, то будет выброшено NullPointerException.
Optional<String> name = Optional.of("Alice");

Optional.ofNullable(T value)
Создает Optional, который может содержать либо значение value, либо null. Если value — null, то создается пустой Optional.
Optional<String> name = Optional.ofNullable(null);

Optional.empty()
Создает пустой Optional, который не содержит значения.
Optional<String> emptyOptional = Optional.empty();

Основные методы класса Optional
isPresent()
Возвращает true, если Optional содержит значение, и false, если он пуст.
if (name.isPresent()) {
    System.out.println("Name is: " + name.get());
}

ifPresent(Consumer<? super T> consumer)
Выполняет действие, если значение присутствует. Метод принимает Consumer, который обрабатывает значение, если оно существует.
name.ifPresent(n -> System.out.println("Name is: " + n));

get()
Возвращает значение, содержащееся в Optional, если оно присутствует. Если значение отсутствует, выбрасывается NoSuchElementException.
String value = name.get();

orElse(T other)
Возвращает значение, если оно присутствует, иначе возвращает other.
String result = name.orElse("Default Name");

orElseGet(Supplier<? extends T> other)
Похож на orElse, но принимает Supplier, который возвращает значение, если Optional пуст.
String result = name.orElseGet(() -> "Default Name");

orElseThrow(Supplier<? extends X> exceptionSupplier)
Возвращает значение, если оно присутствует. Если значение отсутствует, выбрасывается исключение, заданное Supplier.
String result = name.orElseThrow(() -> new IllegalArgumentException("Name is missing"));

map(Function<? super T, ? extends U> mapper)
Применяет функцию к значению, если оно присутствует, и возвращает новый Optional с результатом. Если значение отсутствует, возвращает пустой Optional.
Optional<Integer> nameLength = name.map(String::length);

flatMap(Function<? super T, Optional<U>> mapper)
Похож на map, но используется, если функция возвращает Optional. Применяется для работы с вложенными Optional.
Optional<String> result = name.flatMap(n -> Optional.of(n.toUpperCase()));

filter(Predicate<? super T> predicate)
Возвращает тот же Optional, если значение удовлетворяет условию. Если нет — возвращает пустой Optional.
Optional<String> filteredName = name.filter(n -> n.startsWith("A"));
