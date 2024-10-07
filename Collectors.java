Класс Collectors — это часть Java Stream API, которая предоставляет фабричные методы для создания различных реализаций интерфейса Collector. Его основное предназначение — помощь в сборе данных из стрима в другие структуры данных или в обработке данных по окончании операций в стриме. С помощью Collectors можно, например, собирать элементы в коллекции, объединять строки, производить статистические вычисления и группировать данные по критериям.

Основные методы класса Collectors
Рассмотрим наиболее популярные методы класса Collectors:

toList() и toSet()

Служат для сбора элементов стрима в List и Set соответственно.
List<String> list = stream.collect(Collectors.toList());
Set<String> set = stream.collect(Collectors.toSet());
toMap()

Сохраняет элементы стрима в Map. Принимает два аргумента: функцию для извлечения ключа и функцию для извлечения значения.
Map<Integer, String> map = stream.collect(Collectors.toMap(String::length, s -> s));
Примечание: toMap() не поддерживает дублирующиеся ключи, поэтому если два элемента создадут одинаковый ключ, возникнет ошибка. Для обработки дубликатов существует перегруженная версия метода, где можно задать стратегию разрешения конфликтов.
joining()

Применяется к стриму строк для их объединения в одну строку. Существует три варианта:
joining(): просто объединяет строки.
joining(CharSequence delimiter): объединяет строки, добавляя указанный разделитель.
joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix): объединяет строки, добавляя разделитель, префикс и суффикс.

String joined = stream.collect(Collectors.joining(", "));
counting()

Возвращает количество элементов в стриме.
long count = stream.collect(Collectors.counting());
summarizingInt(), summarizingDouble(), summarizingLong()

Возвращает статистику (сумма, среднее, минимальное и максимальное значения) по элементам стрима, преобразованным в соответствующие числовые типы.
IntSummaryStatistics stats = stream.collect(Collectors.summarizingInt(String::length));
groupingBy()

Группирует элементы в Map по заданному критерию. Может использоваться с одной или двумя функциями:
groupingBy(Function classifier): группирует элементы, создавая Map с ключами, полученными от функции classifier.
groupingBy(Function classifier, Collector downstream): позволяет применить еще один коллектор к сгруппированным элементам.
Map<Integer, List<String>> byLength = stream.collect(Collectors.groupingBy(String::length));
Map<Integer, Set<String>> byLengthToSet = stream.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
partitioningBy()

Делит элементы на две группы, в зависимости от результата логического выражения (Predicate). Возвращает Map<Boolean, List<T>>.
Map<Boolean, List<String>> partitioned = stream.collect(Collectors.partitioningBy(s -> s.length() > 3));
reducing()

Позволяет объединять элементы стрима в одно значение с помощью функции.
Optional<Integer> sum = stream.collect(Collectors.reducing((i1, i2) -> i1 + i2));
int sumWithInitial = stream.collect(Collectors.reducing(0, (i1, i2) -> i1 + i2));
mapping()

Применяет преобразующую функцию к каждому элементу, а затем собирает результат в коллекцию. Часто используется в сочетании с другими коллекторами, такими как groupingBy.
Map<Integer, Set<Character>> groupedByLengthWithFirstLetter = 
    stream.collect(Collectors.groupingBy(
        String::length, 
        Collectors.mapping(s -> s.charAt(0), Collectors.toSet())
    ));
maxBy() и minBy()

Используются для получения максимального или минимального элемента из стрима на основе функции сравнения.
Optional<String> longest = stream.collect(Collectors.maxBy(Comparator.comparingInt(String::length)));
