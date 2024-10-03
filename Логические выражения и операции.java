Приоритет логических операций:

! (логическое "НЕ")
&& (логическое "И")
|| (логическое "ИЛИ")

Чтобы изменить порядок выполнения операций, можно использовать скобки.

Оператор исключающее ИЛИ (XOR) в Java — это логическая операция, которая возвращает true, если два операнда различны, и возвращает false, если оба операнда одинаковы. Он обозначается символом ^ и относится к разряду побитовых и логических операций.

Принцип работы исключающего ИЛИ (XOR):
Если один из операндов равен true, а другой — false, результат будет true.
Если оба операнда равны (оба true или оба false), результат будет false.

Пример использования XOR с логическими значениями:

public class XORExample {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        
        // Исключающее ИЛИ (XOR)
        boolean result = a ^ b; // true ^ false -> true
        System.out.println("Result of true ^ false: " + result); // Вывод: true

        boolean result2 = a ^ true; // true ^ true -> false
        System.out.println("Result of true ^ true: " + result2); // Вывод: false
    }
}

Поиск уникального элемента: XOR используется для поиска уникального элемента в массиве, где каждый элемент повторяется дважды, кроме одного.

public class FindUnique {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 5, 3, 4};
        int unique = 0;
        
        for (int num : arr) {
            unique ^= num; // Суммируем элементы с помощью XOR
        }

        System.out.println("The unique element is: " + unique); // Вывод: 2
    }
}

