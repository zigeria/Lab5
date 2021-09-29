package copy.Proga;

import java.util.Scanner;

public class FindCommand {/*
    private static final Scanner scanner = new Scanner(System.in);
    private static String arg;
    public static void find(ProductTreeMap collection, String[] command) {
                try {
                    arg = command[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    arg = "";
                }

                boolean valid = true;   // эта переменная к концу switch = true, если команда определена, иначе false

                switch (command[0]) {
                    //Команды без аргументов
                    case "help":
                        collection.help();
                        break;
                    case "info":
                        collection.info();
                        break;
                    case "show":
                        collection.show();
                        break;
                    case "print_descending":
                        collection.descending();
                        break;
                    case "clear":
                        collection.clear();
                        break;
                    case "save":
                        collection.save();
                        break;

                    //Что-нибудь сделать
                    case "exit":
                        execution = false;
                        break;

                    case "history":
                        history.get_history();
                        break;

                    //Команды с аргументами

                    case "insert":
                        collection.insert(arg, scanner, collection);
                        break;

                    case "update_id":
                        collection.update(arg, scanner);
                        break;

                    case "remove_key":
                        if (collection.checkEmpty()) {
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        } else {
                            collection.remove(arg, scanner, collection);
                        }
                        break;

                    case "remove_lower":
                        if (collection.checkEmpty()) {
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        } else {
                            collection.remove_lower(arg, scanner);
                        }
                        break;

                    case "remove_greater":
                        if (collection.checkEmpty()) {
                            System.out.println("Нельзя удалить элемент.Коллекция пуста.");
                            scanner.nextLine();
                        } else {
                            collection.remove_greater(arg, scanner);
                        }
                        break;



                    case "execute_script":
                        FileWork.execute_script();
                        //если файл тот же, то может возникнуть рекурсия
                        break;

                    case "print_unique_manufacturer":
                        collection.unique_manufacturer();
                        break;

                    case "filter_unitOfMeasure":
                        collection.filter_unitOfMeasure(arg, scanner);
                        break;
                    default:
                        System.out.println("Странно... Не могу найти команду. Если не знаете, какие команды могут быть выполнены, то введите help");
                        valid = false;
                        break;
                }
                if (valid) {
                    history.add_history(command[0]);
                }


        }
    }*/
}