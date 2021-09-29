package copy.Proga;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String arg;
    public static void main(String[] args) throws FileNotFoundException {

        ProductTreeMap collection = new ProductTreeMap();
        History history = new History();
        FileWork.newEnvironment();
        collection.help();
        Pattern pattern = Pattern.compile("\\w+\\s*\\w*");          // компиляция регулярного выражения

        boolean execution = true;

        while (execution) {
            System.out.println("Введите команду ");
            String line = scanner.nextLine();

            Matcher matcher = pattern.matcher(line);                   //создание поисковика в сканированном тексте

            if (matcher.find()) {                                        //найти строку
                line = line.substring(matcher.start(), matcher.end());  //исключение пробелов перед командой
                String[] command = line.split("\\s+");           //исключение пробелов между командой и аргументом
                try {
                    arg = command[1];
                }
                catch (ArrayIndexOutOfBoundsException e){
                    arg = "";
                }

                boolean valid = true;   // эта переменная к концу switch = true, если команда определена, иначе false

                switch (command[0]){
                    case "help" :
                        collection.help();
                        break;
                    case "info" :
                        collection.info();
                        break;
                    case "show" :
                        collection.show();
                        break;
                    case "save" :
                        collection.save();
                        System.out.println("Коллекция сохранена в файл ProductTreeMap.json");
                        break;
                    case "exit" :
                        execution = false;
                        break;
                    case "clear" :
                        collection.clear();
                        break;
                    case "history" :
                        history.get_history();
                        break;
                    case "insert" :
                        collection.insert(arg, scanner, collection);
                        break;

                    case "update_id" :
                        collection.update(arg,scanner);
                        break;

                    case "remove_key" :
                        if (collection.checkEmpty()){
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        }
                        else {
                            collection.remove(arg,scanner, collection);
                        }
                        break;

                    case "remove_lower" :
                        if (collection.checkEmpty()){
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        }
                        else {
                            collection.remove_lower(arg, scanner);
                        }
                        break;

                    case "remove_greater" :
                        if (collection.checkEmpty()){
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        }
                        else {
                            collection.remove_greater(arg, scanner);
                        }
                        break;

                    case "print_descending" :
                        collection.descending();
                        break;

                    case "execute_script" :
                        FileWork.execute_script(collection);
                        break;

                    case "print_unique_manufacturer" :
                        collection.unique_manufacturer();
                        break;

                    case "filter_unitOfMeasure" :
                        collection.filter_unitOfMeasure(arg, scanner);
                        break;
                    default:
                        System.out.println("Странно... Не могу найти команду. Если не знаете, какие команды могут быть выполнены, то введите help");
                        valid = false;
                        break;
                }
                if(valid) {
                    history.add_history(command[0]);
                }
            }
        }
    }
}
