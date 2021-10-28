package main;

import data.*;
import programException.FileCanNotWriteException;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Класс для работы с файлами
 */
public class FileWork {
    /**
     * Переменная констатирующая факт нахождение в скрипте
     */
    private static boolean fromScript = false;

    /**
     * @return Возвращает fromScript
     */
    public static boolean isFromScript() {
        return fromScript;
    }

    /**
     * Запись коллекции в файл в csv формате
     * @param collection - изменяемая коллекция
     * @param request - объект класса Request
     */
    public static void writeCollectionToCSV(MovieHashSet collection, Request request) {
        try(PrintWriter printWriter = new PrintWriter(collection.getFileCollection())){
            if (!collection.getFileCollection().canWrite()){
                throw new FileCanNotWriteException();
            }
            for(Movie movie : collection.getCollection()) {
                String movieCSV = movie.getId() + ";"
                        + movie.getName() + ";"
                        + movie.getCoordinates().getX() + ";"
                        + movie.getCoordinates().getY() + ";"
                        + movie.getCreationDate() + ";"
                        + movie.getOscarsCount() + ";"
                        + movie.getGenre() + ";"
                        + movie.getMpaaRating() + ";";
                if(movie.getOperator() != null){
                    movieCSV = movieCSV
                            + movie.getOperator().getName() + ";"
                            + movie.getOperator().getHeight() + ";"
                            + movie.getOperator().getEyeColor() + ";"
                            + movie.getOperator().getLocation().getX() + ";"
                            + movie.getOperator().getLocation().getY() + ";"
                            + movie.getOperator().getLocation().getName() + "\n";
                }
                else {
                    movieCSV = movieCSV + null + "\n";
                }
                printWriter.write(movieCSV);
            }
        }
        catch (FileNotFoundException exception){
            System.out.println("Файл, в котором хранится коллекция не найден.");
            collection.setFileCollection(request.requestFile());
            writeCollectionToCSV(collection, request);
        }
        catch (FileCanNotWriteException exception){
            System.out.println(exception.getMessage());
            collection.setFileCollection(request.requestFile());
            writeCollectionToCSV(collection, request);
        }
    }

    /**
     * Чтение команд из фала
     * @param collection - изменяемая коллекция
     * @param fileScript - констатация нахождения в скрипте
     * @param request - объект класса Request
     * @param commandOrganizer - организатор команд
     */
    public static void readScript(MovieHashSet collection, File fileScript, Request request, CommandOrganizer commandOrganizer){


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileScript)))) {

            String line;
            String[] command;

            fromScript = true;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                command = request.requestCommand(line.trim());
                commandOrganizer.executeCommand(command);
            }
            fromScript = false;

        }
        catch (IOException exception){
            System.out.println("Не удалось исполнить файл. Ошибка ввода-вывода");
        }
    }

    /**
     * Восстановить коллекцию из файла
     * @param collection - изменяемая коллекция
     * @param request - объект класса Request
     */
    public static void restoreCollection(MovieHashSet collection, Request request){

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(collection.getFileCollection())))){

            String line;
            String[] movieElements;

            Integer id;
            String name;
            LocalDateTime creationDate;
            Integer oscarsCount;
            MovieGenre genre;
            MpaaRating mpaaRating;

            Person operator;
            String operatorName;
            Long height;
            Color eyeColor;

            Location location;
            Integer locationX;
            Integer locationY;
            String locationName;

            Coordinates coordinates;
            Integer x;
            Float y;

            request.setFromFile(true);

            while ((line = bufferedReader.readLine()) != null) {
                movieElements = line.split(";");
                if ((movieElements.length == 9) || (movieElements.length == 14)) {
                    if ((id = request.requestId(movieElements[0])) == null) continue;
                    if ((name = request.requestMovieName(movieElements[1])) == null) continue;
                    if ((x = request.requestX(movieElements[2])) == null) continue;
                    if ((y = request.requestY(movieElements[3])) == null) continue;
                    coordinates = new Coordinates(x,y);
                    if ((creationDate = request.requestCreationDate(movieElements[4])) == null) continue;
                    if ((oscarsCount = request.requestOscarsCount(movieElements[5])) == null) continue;
                    if ((genre = request.requestGenre(movieElements[6])) == null) continue;
                    mpaaRating = request.requestMpaaRating(movieElements[7]);
                    if (movieElements.length == 9){
                        operator = null;
                        collection.add(new Movie(id,name,coordinates,creationDate,oscarsCount,genre,mpaaRating,operator));
                    }
                    else{
                        if ((operatorName = request.requestPersonName(movieElements[8])) == null) continue;
                        if ((height = request.requestHeight(movieElements[9])) == null)continue;
                        eyeColor = request.requestEyeColor(movieElements[10]);
                        if ((locationX = request.requestLocationX(movieElements[11])) ==null)continue;
                        if ((locationY = request.requestLocationY(movieElements[12])) ==null)continue;
                        if ((locationName = request.requestLocationName(movieElements[12])) ==null)continue;
                        location = new Location(locationX,locationY,locationName);
                        operator = new Person(operatorName,height,eyeColor,location);
                        collection.add(new Movie(id,name,coordinates,creationDate,oscarsCount,genre,mpaaRating,operator));
                    }
                }
            }

            request.setFromFile(false);
        }
        catch (FileNotFoundException exception){
            System.out.println("Файл, в котором хранится коллекция не найден.");
            collection.setFileCollection(request.requestFile());
            restoreCollection(collection, request);
        }
        catch (IOException exception){
            System.out.println("Не удалось восстановить коллекцию. Возникла ошибка ввода-вывода");
        }

    }

}
