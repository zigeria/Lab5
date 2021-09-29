package Proga;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, используемый для работы с файлами
 */
public class FileWork {
    /**
     * Метод, который позволяет считать и исполнить скрипт из указанного файла.
     * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
     * @param collection Коллекция, изменяемая выполнением скрипта.
     * @param history История вызова последних 9 команд.
     */
    public static void execute_script(ProductTreeMap collection, Scanner scanner, History history){
        BufferedInputStream in;
        try {
                 in = new BufferedInputStream(new FileInputStream(System.getenv("FILE_SCRIPT")));
        }
        catch (FileNotFoundException e){
            in = FromСonsole.getBufferedInputStream(scanner);
        }
        int i = -1;
        String line = "";
        String[] command;
        try {
            while((i = in.read()) != -1){

                switch(i) {
                    case '\n':
                        command = FindCommand.parseLine(line);
                        if(command[0].equals("exit")){
                            Main.setExecution(false);
                            return;
                        }
                        if(FindCommand.find(collection, command, "fileScript", scanner, history)){
                            history.add_history(command[0]);
                        }
                        line = "";
                        break;
                    default:
                        line += (char)i;
                        break;
                }
            }

            if (!line.equals("")){
                command = FindCommand.parseLine(line);

                if(FindCommand.find(collection, command, "fileScript", scanner, history)){
                    history.add_history(command[0]);
                }
            }
        }
        catch (IOException e){
            System.out.println("Произошла ошибка ввода");
        }
    }
}
