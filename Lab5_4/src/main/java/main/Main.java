package main;


import java.io.File;
import java.util.Scanner;

/**
 * Главный класс
 */
public class Main {
    /**
     * Начало программы
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Request request = new Request(scanner);
        MovieHashSet collection = new MovieHashSet();

        File file = request.requestFile();
        if (!file.exists() || !file.canRead() ||  !file.isFile() || !file.canWrite()) {
            System.out.println("Дурацкий файл");
            System.exit(-1);
        }

        collection.setFileCollection(file);
        CommandOrganizer commandOrganizer = new CommandOrganizer(collection,request);
        FileWork.restoreCollection(collection,request);

        while (true){
            System.out.println("Введите команду ");
            commandOrganizer.executeCommand(request.requestCommand(null));
            commandOrganizer.clear();
        }
    }
}
