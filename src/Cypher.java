import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Cypher {
    public Cypher(String mode) {
        String stringIn;
        String stringOut;
        Path pathIn;
        Path pathOut;
        boolean inFileExist = false;
        int characterCode;
        int key;
        char character;
        char charEncrypted;

        // файл-источник
        do {
            System.out.print("Введите имя файла-источника: ");
            Scanner scanner = new Scanner(System.in);
            stringIn = scanner.nextLine().trim();
            pathIn = Path.of(stringIn);
            if (!Files.isRegularFile(pathIn)) {
                System.out.println("Файл-источник не существует.");
            } else {
                long size;
                try {
                    size = Files.size(pathIn);
                } catch (IOException e) {
                    System.out.println("Невозможно определить размер файла-источника.");
                    continue;
                }
                if (size == 0) {
                    System.out.println("Файл-источник пустой, нет данных для шифрования.");
                } else {
                    inFileExist = Files.isRegularFile(pathIn);
                    System.out.println("Файл-источник: " + stringIn);
                }
            }

        } while (!inFileExist);

        // ключ
        do {
            key = 0;
            System.out.print("Введите ключ (целое число): ");
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNextInt()) {
                key = scanner1.nextInt();
            }
            if (key % Cryption.ALPHABETLENGTH == 0) {
                key = 0;
            }
        } while (key == 0);
        System.out.println("Ключ: " + key);

        if (mode.equals("decrypt")) {
            key = -key;
        }

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

            pathOut = Path.of(stringOut);
            if (!Files.isRegularFile(pathOut)) {
                try {
                    Files.createFile(pathOut);
                } catch (IOException e) {
                    System.out.println("Выходной файл не существует и не удалось создать выходной файл: " + stringOut);
                    continue;
                }
            }
            outFileExist = Files.isRegularFile(pathOut);
            if (outFileExist) {
                System.out.println("Выходной файл: " + stringOut);
            }
        } while (!outFileExist);


        // пишем выходной файл
        pathOut = Path.of(stringOut);

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathIn);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOut)
             // файл для неизвестных символов
             //BufferedWriter bufferedWriterUnknown = Files.newBufferedWriter(Path.of("D:\\Cypher\\OUT\\unknown.txt"))
        ) {
            do {
                characterCode = bufferedReader.read();
                bufferedReader.mark(1);
                character = (char) characterCode;
                charEncrypted = new Cryption().crypto(key, characterCode);
                if (!(character == charEncrypted)) {
                    bufferedWriter.write(charEncrypted);
                } else if (characterCode == 10 || characterCode == 13) {
                    bufferedWriter.write(character);
                } else {
                    System.out.println("Встретился неизвестный символ: " + character);
                    System.out.println("Работа приложения остановлена.");
                    return;
                    //bufferedWriterUnknown.write(character); // файл для неизвестных символов
                }
            } while ((bufferedReader.ready()));
            bufferedWriter.flush();
            //bufferedWriterUnknown.flush(); // файл для неизвестных символов
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Конец процедуры шифрования/дешифрования.");
        System.out.println();
    }
}
