Stream — это последовательность данных, которая поддерживает множество операций для обработки этих данных:

Операции над потоками выполняются "лениво", то есть обрабатываются только при необходимости, в процессе выполнения терминальной операции.
Stream не хранит данные, а лишь описывает, как их получить или обработать.

Промежуточные (Intermediate) — возвращают новый поток, например:
filter(Predicate<T>) — фильтрует элементы, оставляя только те, которые соответствуют условию.
map(Function<T, R>) — применяет функцию ко всем элементам потока и возвращает новый поток.
sorted(Comparator<T>) — сортирует элементы.
distinct() — удаляет дубликаты.
limit(n): Ограничение количества элементов до заданного значения.
skip(n): Пропуск первых n элементов.
Промежуточные операции выполняются "лениво" и только при вызове терминальной операции. Они позволяют строить конвейер обработки данных.

Терминальные (Terminal) — завершают поток, выполняя вычисления, например:
collect(Collector<T, A, R>) — преобразует поток в коллекцию или другую структуру данных.
forEach(Consumer<T>) — выполняет действие над каждым элементом потока.
reduce(BinaryOperator<T>) — сворачивает поток в одно значение.
count() — возвращает количество элементов в потоке.
findFirst() — возвращает первый элемент, если таковой есть.
anyMatch(Predicate<T> predicate), allMatch(Predicate<T> predicate): Проверка соответствия элементов условию.
  
Пример использования Stream API:
List<String> names = Arrays.asList("Anna", "Mike", "Tom", "Jane");
List<String> filteredNames = names.stream()
    .filter(name -> name.length() > 3)
    .sorted()
    .collect(Collectors.toList());
System.out.println(filteredNames); // Вывод: [Anna, Jane, Mike]
В этом примере создаётся поток из списка names, который фильтруется по длине строки, сортируется и затем собирается обратно в список.


Stream API тесно связан с принципами функционального программирования:

Немутируемость данных:
Операции над потоками не изменяют исходные данные, а возвращают новый поток, описывающий результат обработки. Например, фильтрация или сортировка в Stream не изменяет исходную коллекцию, а создаёт новую.
Функции высшего порядка:
Stream API активно использует функциональные интерфейсы (например, Predicate, Function, Consumer), что позволяет передавать лямбда-выражения и методы как параметры. Это даёт возможность определять операции, используя функции.

Отсутствие состояния:
Операции Stream API выполняются в определённой последовательности без изменения состояния. Это позволяет легко использовать параллельные потоки, что способствует улучшению производительности при работе с большими объемами данных.

Композиция:
Stream API позволяет объединять несколько операций в один конвейер, создавая чистый и декларативный код. Вместо того чтобы писать цикл для фильтрации, сортировки и преобразования данных, можно просто объединить эти операции с помощью потока.
  
Ленивое вычисление:
Промежуточные операции в потоках выполняются "лениво" (lazy), т.е. до тех пор, пока не будет вызвана терминальная операция, фактическая обработка данных не начнется. Это позволяет оптимизировать производительность за счёт того, что данные обрабатываются только по мере необходимости.

Основные отличия между map и flatMap
  
map:
Применяет функцию к каждому элементу стрима и возвращает новый стрим, содержащий результат этой функции.
Если функция, передаваемая в map, возвращает другой стрим или коллекцию, итоговый результат будет стримом стримов (или коллекцией коллекций).
Полезен, когда результат обработки элемента — это один объект.
  
flatMap:
Применяет функцию к каждому элементу, но разворачивает полученные стримы или коллекции в единый стрим.
Используется, когда результат функции — это стрим или коллекция, и нужно объединить их элементы в один стрим.
Удобен, когда нужно обработать вложенные структуры данных, такие как списки или массивы, превращая их в плоский стрим.
  
Примеры использования map и flatMap
Пример с map
List<String> words = Arrays.asList("Hello", "World", "Java");
List<Integer> lengths = words.stream()
                             .map(String::length)
                             .collect(Collectors.toList());
System.out.println(lengths); // Вывод: [5, 5, 4]
Здесь map применяет метод String::length к каждому элементу words, возвращая List<Integer>, содержащий длину каждого слова.
  
Пример с flatMap
Предположим, у нас есть список предложений, и нам нужно получить список всех уникальных слов.
List<String> sentences = Arrays.asList("Hello world", "Java stream", "Hello Java");

List<String> uniqueWords = sentences.stream()
                                    .map(sentence -> sentence.split(" ")) // Получаем Stream<String[]>
                                    .flatMap(Arrays::stream)              // Разворачиваем Stream<String[]> в Stream<String>
                                    .distinct()
                                    .collect(Collectors.toList());

System.out.println(uniqueWords); // Вывод: [Hello, world, Java, stream]
map(sentence -> sentence.split(" ")) возвращает Stream<String[]>, то есть стрим массивов слов для каждого предложения.
flatMap(Arrays::stream) преобразует каждый String[] в поток String и объединяет их в один плоский Stream<String>.
Затем distinct() удаляет дубликаты.
  
Ключевые отличия между map и flatMap
  
Тип возвращаемого значения:
map возвращает стрим того же типа, что и результат функции. Например, Stream<String[]> при работе с массивом строк.
flatMap объединяет несколько стримов в один стрим. То есть, он "разворачивает" вложенные стримы или коллекции в один плоский стрим.
  
Сценарии применения:
Используйте map, если функция преобразует элемент в один объект или один тип данных.
Используйте flatMap, если функция возвращает вложенные коллекции или стримы, которые нужно объединить в один плоский стрим.
