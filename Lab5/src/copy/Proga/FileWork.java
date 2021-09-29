package copy.Proga;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWork {
    //считать и исполнить скрипт из указанного файла.
    // В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
    //"C://Users//ziger//Desktop//Программирование//Script.txt"
    public static void execute_script(ProductTreeMap collection){

        Pattern pattern = Pattern.compile("\\w+\\s*\\w*");          // компиляция регулярного выражения

        try( BufferedInputStream in = new BufferedInputStream(new FileInputStream("C://Users//ziger//Desktop//Программирование//Script.txt")))
        {
            int i /*= -1*/;
            String line = "";
            String[] command;
            while((i = in.read()) != -1){

                switch(i) {
                    case '\n':
                        Matcher matcher = pattern.matcher(line);

                        if (matcher.find()){
                            line = line.substring(matcher.start(), matcher.end());  //исключение пробелов перед командой
                            command = line.split("\\s+");           //исключение пробелов между командой и аргументом
                            //FindCommand.find(collection,command);
                        }

                        line = "";
                        break;
                    default:
                        line += (char)i;
                        break;
                }
            }

            if (!line.equals("")){

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()){
                    line = line.substring(matcher.start(), matcher.end());  //исключение пробелов перед командой
                    command = line.split("\\s+");           //исключение пробелов между командой и аргументом
                    //FindCommand.find(collection,command);
                }
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    //Создание переменной окружения
    public static void newEnvironment(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map env = processBuilder.environment();
        env.put("FILE_SAVE", "C:\\Users\\ziger\\Desktop\\Программирование\\ProductTreeMap.json");
        env.put("FILE_SCRIPT", "C:\\Users\\ziger\\Desktop\\Программирование\\Script.txt");

    }

}
