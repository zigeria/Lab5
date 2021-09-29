package Proga;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Работает со строками, поиск соответствий
 */
public class FindCommand {
    private static String arg;

    /**
     * Данный метод разделяет введенную строку на слова.
     * Позволяет игнорировать ошибки пользователя при вводе или небольшие дефекты в файле.
     * Исключает пробелы перед командой и между командой и аргументом.
     * Работает и с пользовательским вводом и при исполнении скрипта из файла
     * @param line Строка введенная пользователем или прочитанная из файла.
     * @return Возвращает строчный массив из 2х элементов, первый - команда, второй - аргумент.
     */
    public static String[] parseLine(String line){
        Pattern pattern = Pattern.compile("\\w+\\s*\\w*");          // компиляция регулярного выражения
        Matcher matcher = pattern.matcher(line);                   //создание поисковика в сканированном тексте
        String[] command = {"",""};

        if (matcher.find()) {                                        //найти строку
            line = line.substring(matcher.start(), matcher.end());  //исключение пробелов перед командой
            command = line.split("\\s+");           //исключение пробелов между командой и аргументом
            try {
                arg = command[1];
            }
            catch (ArrayIndexOutOfBoundsException e){
                arg = "";
            }
        }
        return command;
    }

    public static boolean arg(String arg){
        Pattern pattern = Pattern.compile("\\W");          // символ не являющийся символом слова
        Matcher matcher = pattern.matcher(arg);
        return matcher.find();
    }

    /**
     * Находит команду, соответствующую вызываемой, и исполняет ее.
     * @param collection Изменяемая коллекция.
     * @param command Строчный массив из 2х элементов, первый - команда, второй - аргумент.
     * @param where Показывает откуда вызвали метод, из консоли или файла.
     * @param history История вызова последних 9 команд.
     * @return Возвращает булево значение. При удачном нахождении команды возвращается true, кроме команды execute_script.
     */
    public static boolean find(ProductTreeMap collection, String[] command, String where, Scanner scanner, History history){
        switch (command[0]) {
            //Команды без аргументов
            case "help":
                collection.help();
                return true;
            case "info":
                collection.info();
                return true;
            case "show":
                if (!collection.isEmpty()){
                    collection.show();
                }
                return true;
            case "print_descending":
                if (!collection.isEmpty()){
                    collection.descending();
                }
                return true;
            case "clear":
                if (!collection.isEmpty()) {
                    collection.clear();
                }
                return true;
            case "save":
                collection.save(scanner);
                return true;

            case "print_unique_manufacturer":
                if (!collection.isEmpty()) {
                    collection.unique_manufacturer();
                }
                return true;


            //Команды с аргументами
            case "remove_key":
                if (!collection.isEmpty()) {
                    collection.remove(arg, scanner, collection, where);
                }
                return true;

            case "remove_lower":
                if (!collection.isEmpty()) {
                    collection.remove_lower(arg, scanner, where);
                }
                return true;

            case "remove_greater":
                if (!collection.isEmpty()) {
                    collection.remove_greater(arg, scanner, where);
                }return true;


            case "filter_unitOfMeasure":
                if (!collection.isEmpty()) {
                    collection.filter_unitOfMeasure(arg, scanner, where);
                }
                return true;

            case "insert":
                collection.insert(arg, scanner,collection, where);
                return true;

            case "update_id":
                if (!collection.isEmpty()) {
                    collection.update(arg, scanner);
                }
                return true;

            case "execute_script":
                history.add_history("execute_script");
                FileWork.execute_script(collection, scanner, history);
                //если передано из файла и файл тот же, то может возникнуть рекурсия
                return false;

            case "exit":
                Main.setExecution(false);
                return false;

            case "history":
                history.get_history();
                return true;


            default:
                if (where.equals("console")){
                    System.out.println("Странно... Не могу найти команду. Если не знаете, какие команды могут быть выполнены, то введите help");
                }
                else if (where.equals("fileScript")){
                    System.out.format("Ошибка в файле. Невозможно исполнить команду %s %s, исполнение команды пропущено \n", command[0], command[1]);

                }
                return false;
        }
    }
}