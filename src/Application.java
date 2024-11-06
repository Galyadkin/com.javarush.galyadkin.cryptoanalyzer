import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        int menuNumber;
        do {
            System.out.println("ШИФРОВАНИЕ МЕТОДОМ ЦЕЗАРЯ. ВЫБЕРИТЕ, ПОЖАЛУЙСТА, ПУНКТ МЕНЮ:");
            System.out.println("1. ШИФРОВАНИЕ С КЛЮЧОМ");
            System.out.println("2. ДЕШИФРОВАНИЕ С КЛЮЧОМ");
            System.out.println("3. BRUTE FORCE");
            // System.out.println("4 - СТАТИСТИЧЕСКИЙ АНАЛИЗ");
            System.out.println("0. ВЫХОД");

            do {
                menuNumber = -1;
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    menuNumber = scanner.nextInt();
                } else {
                    System.out.print("ВЫБЕРИТЕ, ПОЖАЛУЙСТА, СУЩЕСТВУЮЩИЙ ПУНКТ МЕНЮ: ");
                    continue;
                }
                if (menuNumber > 3) {
                    System.out.print("ВЫБЕРИТЕ, ПОЖАЛУЙСТА, СУЩЕСТВУЮЩИЙ ПУНКТ МЕНЮ: ");
                }
            } while (menuNumber < 0 || menuNumber > 3);

            switch (menuNumber) {
                case 1:
                    System.out.println("Вызов метода шифрования");
                    new Cypher("encrypt");
                    break;
                case 2:
                    System.out.println("Вызов метода дешифрования");
                    new Cypher("decrypt");
                    break;
                case 3:
                    System.out.println("Вызов метода Brute force");
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        } while (menuNumber > 0);
    }
}
