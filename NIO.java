Java NIO (New Input/Output) — это пакет, введенный в Java 7, который предлагает более эффективные способы работы с файлами и каталогами. NIO.2 расширяет возможности NIO, добавляя поддержку работы с файлами, метаданными, а также улучшая управление потоками. Давайте рассмотрим ключевые аспекты работы с NIO.2.

1. Проверка существования файла или каталога
Для проверки существования файла или каталога используется метод Files.exists(Path path).

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCheck {
    public static void main(String[] args) {
        Path path = Paths.get("example.txt");
        if (Files.exists(path)) {
            System.out.println("Файл или каталог существует.");
        } else {
            System.out.println("Файл или каталог не существует.");
        }
    }
}

2. Удаление, копирование и перемещение
Удаление файла или каталога:

Files.delete(path); // Удаление файла

Копирование:
import java.nio.file.StandardCopyOption;

Path sourcePath = Paths.get("source.txt");
Path targetPath = Paths.get("target.txt");
Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING); // Копирование файла

Перемещение:
Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING); // Перемещение файла

3. Управление метаданными
Для работы с метаданными файлов можно использовать методы класса Files, такие как Files.getAttribute() и Files.setAttribute().

Получение атрибута:
Object size = Files.getAttribute(path, "size");
System.out.println("Размер файла: " + size);

Установка атрибута:
Files.setAttribute(path, "user:myAttribute", "someValue"); // Установка пользовательского атрибута

4. Чтение, запись и создание файлов
  
Чтение файла:
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

Path path = Paths.get("example.txt");
List<String> lines = Files.readAllLines(path); // Чтение всех строк
for (String line : lines) {
    System.out.println(line);
}

Запись в файл:
List<String> lines = Arrays.asList("Первая строка", "Вторая строка");
Files.write(path, lines); // Запись строк в файл

Создание файла:
Files.createFile(path); // Создание нового файла

5. Создание и чтение каталогов
Создание каталога:
Path dirPath = Paths.get("newDir");
Files.createDirectory(dirPath); // Создание нового каталога

Чтение содержимого каталога:
try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
    for (Path entry : stream) {
        System.out.println(entry.getFileName());
    }
}

6. Поиск файлов. PathMatcher
Для поиска файлов, соответствующих определенному шаблону, можно использовать PathMatcher.

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;

import java.io.IOException;
import java.util.EnumSet;

public class PathMatcherExample {
    public static void main(String[] args) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.txt");

        try {
            Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (matcher.matches(file.getFileName())) {
                        System.out.println("Найден файл: " + file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

7. Обход дерева файлов. FileVisitor
Для обхода дерева каталогов можно использовать интерфейс FileVisitor, который позволяет обрабатывать файлы и каталоги при их обходе.

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;

import java.io.IOException;

public class FileVisitorExample {
    public static void main(String[] args) {
        Path startPath = Paths.get(".");

        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Посетили файл: " + file);
                    return FileVisitResult.CONTINUE; // Продолжить обход
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Вход в каталог: " + dir);
                    return FileVisitResult.CONTINUE; // Продолжить обход
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
