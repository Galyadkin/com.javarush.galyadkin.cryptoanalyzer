import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForce {
    public BruteForce() {
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
            System.out.print("Введите имя файла для дешифрования: ");
            Scanner scanner = new Scanner(System.in);
            stringIn = scanner.nextLine().trim();
            pathIn = Path.of(stringIn);
            if (!Files.isRegularFile(pathIn)) {
                System.out.println("Файл для дешифрования не существует.");
            } else if (Files.isRegularFile(pathIn)) {
                long size;
                try {
                    size = Files.size(pathIn);
                } catch (IOException e) {
                    System.out.println("Невозможно определить размер файла для дешифрования.");
                    continue;
                }
                if (size == 0) {
                    System.out.println("Файл для дешифрования пустой, нет данных.");
                } else {
                    inFileExist = Files.isRegularFile(pathIn);
                    System.out.println("Файл для дешифрования: " + stringIn);
                }
            }

        } while (!inFileExist);

        // папка для выходных файлов
        boolean outFolderExist = false;
        do {
            System.out.print("Введите имя папки для выходных файлов: ");
            Scanner scanner2 = new Scanner(System.in);
            stringOut = scanner2.nextLine().trim();

            pathOut = Path.of(stringOut);

            if (!Files.isDirectory(pathOut)) {
                try {
                    Files.createDirectory(pathOut);
                } catch (IOException e) {
                    System.out.println("Папка для выходных файлов не существует и не удалось её создать: " + stringOut);
                    continue;
                }
            }
            outFolderExist = Files.isDirectory(pathOut);
            if (outFolderExist) {
                System.out.println("Папка для выходных файлов: " + stringOut);
            }
        } while (!outFolderExist);

        // формирование выходных файлов
        for (int i = 1; i < Cryption.ALPHABETLENGTH; i++) {
            key = i;
            Path pathOutFile = Path.of(stringOut, "BruteForceKey" + key + ".txt");

            try (BufferedReader bufferedReader = Files.newBufferedReader(pathIn);
                 BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOutFile)
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

        }
        System.out.println("Конец процедуры шифрования/дешифрования.");
        System.out.println();

    }
}
