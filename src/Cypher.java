import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Cypher {
    public Cypher(String method) {
        // файл-источник
        boolean inFileExist = false;
        String stringIn;
        do {
            System.out.print("Введите имя файла-источника: ");
            Scanner scanner = new Scanner(System.in);
            stringIn = scanner.nextLine().trim();
            Path pathIn = Path.of(stringIn);
            if (Files.notExists(pathIn)) {
                System.out.println("Файл-источник не существует");
                continue;
            } else if (Files.exists(pathIn)) {
                long size;
                try {
                    size = Files.size(pathIn);
                } catch (IOException e) {
                    System.out.println("Невозможно определить размер файла-источника.");
                    continue;
                }
                if (size == 0) {
                    System.out.println("Файл-источник пустой, нет данных для шифрования");
                } else {
                    inFileExist = Files.exists(pathIn);
                    System.out.println("Файл-источник: " + stringIn);
                }
            }

        } while (!inFileExist);

        // ключ
        int key;
        do {
            key = 0;
            System.out.print("Введите ключ (целое число): ");
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                key = scanner1.nextInt();
            }
        } while (key == 0);
        System.out.println("Ключ: " + key);
        String stringOut;

        // выходной файл
        boolean outFileExist = false;
        do {
            System.out.print("Введите имя выходного файла: ");
            Scanner scanner2 = new Scanner(System.in);
            stringOut = scanner2.nextLine().trim();

            if (stringOut.equals(stringIn)) {
                System.out.println("Файл-источник не может быть выходным файлом.");
                continue;
            }

            Path pathOut = Path.of(stringOut);
            if (Files.notExists(pathOut)) {
                try {
                    Files.createFile(pathOut);
                } catch (IOException e) {
                    System.out.println("Выходной файл не существует и не удалось создать выходной файл: " + pathOut);
                }
            }
            outFileExist = Files.exists(pathOut);
            System.out.println("Выходной файл: " + stringOut);

        } while (!outFileExist);

        System.out.println("Конец процедуры шифрования/дешифрования");
        System.out.println();
    }
}
