package Proga;

import java.util.Scanner;
/**
 * @author Цыгер Ирина R3136
 * Консольное приложение, реализующее управление коллекцией в интерактивном режиме
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Boolean execution = true;

    public static void setExecution(Boolean execution) {
        Main.execution = execution;
    }

    public static Boolean getExecution() {
        return execution;
    }

    /***
     * Точка входа в приложение
     */
    public static void main(String[] args) {

        ProductTreeMap collection = new ProductTreeMap();
        History history = new History();
        collection.help();

        while (getExecution()) {
            System.out.println("Введите команду ");
            String line = scanner.nextLine();

            String[] command = FindCommand.parseLine(line);

            if (FindCommand.find(collection,command,"console",scanner, history)){
                    history.add_history(command[0]);
            }
        }
    }
}
