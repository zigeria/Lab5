package main;


import data.*;
import programException.NumericalLimitationException;
import programException.ShouldNotBeEmptyException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Класс запросов
 * Используется для получения из консоли или файла информации, необходимой для создания элемента коллекции или исполнения команд
 */
public class Request {
    /**
     * Минимальное значение количества оскаров
     */
    private final int MIN_OSCARS_COUNT = 1;
    /**
     * Максимальное значение координаты Х
     */
    private final Integer MAX_X = 871;
    /**
     * Минимальное значение роста
     */
    private final long MIN_HEIGHT = 1;
    /**
     * Максимально допустимое значение длины строки с названием населенного пункта
     */
    private final int MAX_LENGTH_LOCATION_NAME = 253;

    private final Scanner scanner;
    /**
     * Показывает, откуда происходит вызов методов
     * true - из файла, false - из командной строки
     */
    private boolean fromFile = false;

    /**
     * Задает boolean значение переменной fromFile
     * @param fromFile
     */
    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }

    /**
     * Возвращает fromFile
     * @return true - если вызов из файла, иначе - false
     */
    public boolean isFromFile() {
        return fromFile;
    }

    /**
     * Конструктор
     * @param scanner
     */
    public Request(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Осуществляет запрос команды
     * @param line - строка из читаемого файла, в котором хранится коллекция. line == null - если пользовательский ввод
     * @return Массив строк, вычисленный путем разбиения этой строки на совпадения с заданным регулярным выражением
     * Если аргумент команды отсутствует, то второй элемент массива является пустой строкой
     */
     public String[] requestCommand(String line){
        if(!FileWork.isFromScript()){
            line = scanner.nextLine().trim();
        }
        String[] splitLine = line.split("\\s+");   //исключение пробелов между командой и аргументом
        String[] command = new String[2];
        try {
            command[0] = splitLine[0];
            command[1] = splitLine[1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            command[1] = "";
        }
        return command;
    }

    /**
     * Запрос файла
     * @return файл
     */
    public File requestFile(){

        String fileName;
        File file;
        while (true) {
            try {
                System.out.println("Введите имя файла в которой хранится/будет храниться коллекция:");
                fileName = scanner.nextLine().trim();
                if (fileName.equals("")) throw new ShouldNotBeEmptyException();
                file = new File(fileName);
                break;
            }
            catch (ShouldNotBeEmptyException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return file;
    }

    /**
     * Запрос идентификатора
     * @param id_str - считанный из файла аргумент команды, требующей идентификатор
     * @return Возвращает значение id или null - в случае некорректности данных
     */
    public Integer requestId(String id_str){
        int id;
        try {
            id = Integer.parseInt(id_str.trim());
            return id;
        }
        catch (NumberFormatException exception) {
            System.out.println("Идентификатор должен быть представлен целым (int) числом! Элемент будет отброшен");
            return null;
        }
    }

    /**
     * Запрос названия фильма
     * @param name - считанное из файла название фильма
     * @return null или значение name
     */
    public String requestMovieName(String name){
        if(fromFile){
            if (name.equals("")) return null;
        }
        else {
            while (true) {
                try {
                    System.out.println("Введите название фильма:");
                    name = scanner.nextLine().trim();
                    if (name.equals("")) throw new ShouldNotBeEmptyException();
                    break;
                }
                catch (ShouldNotBeEmptyException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
        return name;
    }

    /**
     * Запрос координаты Х
     * @param x_str - считанная из файла координата Х
     * @return значение координаты x или null
     */
    public Integer requestX(String x_str){
        int x;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите координату X. X < " + (MAX_X + 1) + ":");
                    x_str = scanner.nextLine();
                }
                x = Integer.parseInt(x_str.trim());
                if (x > MAX_X) throw new NumericalLimitationException();
                return x;
            }
            catch (NumberFormatException exception) {
                System.out.println("Координата X должна быть представлена целым (int) числом!");
                if (fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
            catch (NumericalLimitationException exception){
                System.out.println(exception.getMessage());
                if (fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос координаты У
     * @param y_str - считанная из файла координата У
     * @return значение координаты У или null
     */
    public Float requestY (String y_str){
        float y;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите координату Y:");
                    y_str = scanner.nextLine();
                }
                y = Float.parseFloat(y_str.trim());
                return y;
            }
            catch (NumberFormatException exception) {
                System.out.println("Координата Y должна быть представлена дробным (float) числом!");
                if (fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос даты инициализации объекта
     * @param creationDate_str - считанная из файла дата инициализации объекта
     * @return Возвращает дату инициализации объекта или null
     */
    public LocalDateTime requestCreationDate (String creationDate_str){
        LocalDateTime creationDate;
        try {
            creationDate = LocalDateTime.parse(creationDate_str);
            return creationDate;
        }
        catch (DateTimeParseException exception) {
            System.out.println("Дата не может быть проанализирована! Элемент будет отброшен");
            return null;
        }
    }

    /**
     * Запрос координат
     * @return объект класса Coordinates
     */
    public Coordinates requestCoordinates(){
        return new Coordinates(requestX(null),requestY(null));
    }

    /**
     * Запрос о количестве оскаров
     * @param oscarsCount_str -считанное из файла количество оскаров
     * @return количество оскаров или null
     */
    public Integer requestOscarsCount (String oscarsCount_str){
        int oscarsCount;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите количество оскаров не меньше " + MIN_OSCARS_COUNT + ":");
                    oscarsCount_str = scanner.nextLine();
                }
                oscarsCount = Integer.parseInt(oscarsCount_str.trim());
                if (oscarsCount < MIN_OSCARS_COUNT) throw new NumericalLimitationException();
                return oscarsCount;
            }
            catch (NumberFormatException exception) {
                System.out.println("Количество оскаров должно быть представлено целым (int) числом!");
                if (fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
            catch (NumericalLimitationException exception){
                System.out.println(exception.getMessage());
                if (fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос жанра
     * @param genre_str считанный из файла жанр
     * @return жанр или null
     */
    public MovieGenre requestGenre (String genre_str) {
        MovieGenre genre;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Список жанров:\n" + MovieGenre.genreList());
                    System.out.println("Введите жанр из списка:");
                    genre_str = scanner.nextLine();
                }
                genre = MovieGenre.valueOf(genre_str.trim().toUpperCase());
                return genre;
            } catch (IllegalArgumentException exception) {
                System.out.println("Введенного жанра нет в списке!");
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос рейтинга MPAA
     * @param mpaaRating_str - считанный из файла рейтинг
     * @return название рейтинга или null
     */
    public MpaaRating requestMpaaRating (String mpaaRating_str){
        MpaaRating mpaaRating;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Список рейтингов MPAA:\n" + MpaaRating.mpaaRatingList());
                    System.out.println("Введите рейтинг из списка:");
                    mpaaRating_str = scanner.nextLine();
                }
                if (mpaaRating_str.equals("") || mpaaRating_str.equals("null")) return null;
                mpaaRating = MpaaRating.valueOf(mpaaRating_str.trim().toUpperCase());
                return mpaaRating;
            } catch (IllegalArgumentException exception) {
                if(fromFile){
                    return null;
                }
                System.out.println("Введенного рейтинга нет в списке!");
            }
        }
    }

    /**
     * Запрос имени человека
     * @param name - имя, считанное из файла
     * @return имя или null
     */
    public String requestPersonName (String name){
        while (true) {
            try {
                if (!fromFile) {
                    System.out.println("Введите имя:");
                    name = scanner.nextLine();
                }
                if (name.equals("")) throw new ShouldNotBeEmptyException();
                return name.trim();
            } catch (ShouldNotBeEmptyException exception) {
                if (fromFile) {
                    return null;
                }
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Запрос роста
     * @param height_str - рост, считанный из файла
     * @return значение роста или null
     */
    public Long requestHeight (String height_str){
        long height;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите рост не меньше " + MIN_HEIGHT + ":");
                    height_str = scanner.nextLine();
                }
                height = Long.parseLong(height_str.trim());
                if (height < MIN_HEIGHT) throw new NumericalLimitationException();
                return height;
            }
            catch (NumberFormatException exception) {
                System.out.println("Рост должен быть представлен целым (long) числом!");
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
            catch (NumericalLimitationException exception){
                System.out.println(exception.getMessage());
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос цвета глаз
     * @param eyeColor_str - цвет глаз, считанный из файла
     * @return цвет или null
     */
    public Color requestEyeColor (String eyeColor_str){
        Color eyeColor;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Список доступных цветов:\n" + Color.colorList());
                    System.out.println("Введите цвет из списка:");
                    eyeColor_str = scanner.nextLine();
                }
                if (eyeColor_str.equals("") || eyeColor_str.equals("null")) return null;
                eyeColor = Color.valueOf(eyeColor_str.trim().toUpperCase());
                return eyeColor;
            } catch (IllegalArgumentException exception) {
                if(fromFile){
                    return null;
                }
                System.out.println("Введенного цвета нет в списке!");
            }
        }

    }

    /**
     * Запрос координаты Х места рождения
     * @param x_str - считанная из файла координата Х
     * @return значение координаты x или null
     */
    public Integer requestLocationX (String x_str){
        int x;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите координату X места рождения:");
                    x_str = scanner.nextLine();
                }
                x = Integer.parseInt(x_str.trim());
                return x;
            }
            catch (NumberFormatException exception) {
                System.out.println("Координата X должна быть представлена целым (int) числом!");
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }
    /**
     * Запрос координаты У места рождения
     * @param y_str - считанная из файла координата У
     * @return значение координаты У или null
     */
    public Integer requestLocationY (String y_str){
        int y;
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите координату Y места рождения:");
                    y_str = scanner.nextLine();
                }
                y = Integer.parseInt(y_str.trim());
                return y;
            }
            catch (NumberFormatException exception) {
                System.out.println("Координата Y должна быть представлена целым (int) числом!");
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос названия населенного пункта
     * @param name название населенного пункта, считанное с файла
     * @return название населенного пункта или null
     */
    public String requestLocationName (String name){
        while (true) {
            try {
                if(!fromFile){
                    System.out.println("Введите название населенного пункта. Длина строки не должна быть больше " + MAX_LENGTH_LOCATION_NAME + ":");
                    name = scanner.nextLine();
                }
                name = name.trim();
                if (name.equals("")) throw new ShouldNotBeEmptyException();
                if (name.length() > MAX_LENGTH_LOCATION_NAME) throw new NumericalLimitationException();
                return name;
            }
            catch (NumericalLimitationException exception) {
                System.out.println(exception.getMessage());
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
            catch (ShouldNotBeEmptyException exception){
                System.out.println("Ввод не может быть пустым");
                if(fromFile){
                    System.out.println("Элемент будет отброшен");
                    return null;
                }
            }
        }
    }

    /**
     * Запрос моста рождения
     * @return объект класса Location
     */
    public Location requestLocation (){
        System.out.println("Введите информацию о месте рождения");
        return new Location(requestLocationX(null), requestLocationY(null), requestLocationName(null));
    }

    /**
     * Запрос о наличии информации об операторе
     * @return true - информация есть, false - информации нет
     */
    public boolean requestOperatorInformation(){
        String yesOrNo;
        while (true) {
            try {
                System.out.println("Есть ли информация про оператора? Ответьте ДА или НЕТ");
                yesOrNo = scanner.nextLine().trim();
                if (yesOrNo.equalsIgnoreCase("да") || yesOrNo.equalsIgnoreCase("yes")) return true;
                if (yesOrNo.equalsIgnoreCase("нет") || yesOrNo.equalsIgnoreCase("no")) return false;
                else throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException exception) {
                System.out.println("Неуместный ввод");
            }
        }
    }

    /**
     * Запросить человека
     * @return Возвращает объект класса Person
     */
    public Person requestOperator (){
        if (requestOperatorInformation()){
            return new Person(requestPersonName(null),requestHeight(null),requestEyeColor(null),requestLocation());
        }
        else return null;
    }

    /**
     * Запросить Фильм
     * @param id - идентификатор фильма
     * @return Возвращает объект класса Movie
     */
    public Movie requestMovie(Integer id){
        return new Movie(id,
                requestMovieName(null),
                requestCoordinates(),
                LocalDateTime.now(),
                requestOscarsCount(null),
                requestGenre(null),
                requestMpaaRating(null),
                requestOperator());
    }

    /**
     * Запросить другой аргумент
     * @return Возвращает строку с введенным из консоли аргументом
     */
    public String requestAnotherArg(){
        String arg = scanner.nextLine().trim();
        return arg;
    }
}
