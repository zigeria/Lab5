package commands;


import data.Movie;
import data.MovieGenre;
import main.MovieHashSet;
import main.Request;
import programException.EmptyCollectionException;
import programException.GenreNotFoundException;

/**
 * Класс команды, выводящей элементы коллекции, значение поля genre которых меньше заданного
 */
public class FilterLessThenGenre extends Command {
    private MovieHashSet collection;
    private Request request;

    /**
     * Конструктор команды filter_less_than_genre
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public FilterLessThenGenre(MovieHashSet collection, Request request){
        super("filter_less_than_genre"," genre","вывести элементы, значение поля genre которых меньше заданного");
        this.collection = collection;
        this.request = request;
    }
    @Override
    public void execute(String argument) throws EmptyCollectionException, GenreNotFoundException {
        if (collection.isEmpty()) throw new EmptyCollectionException();

        if(!isValidArgument(argument)){
            if(request.isFromFile()){
                System.out.println("Жанр не найден в списке");
                return;
            }
            else {
                throw new GenreNotFoundException();
            }
        }

        int count = 0;
        for (Movie movie : collection.getCollection()){
            if(movie.getGenre().compareTo(MovieGenre.valueOf(argument.toUpperCase())) < 0){
                count++;
                System.out.println(movie);
            }
        }
        if (count == 0){
            System.out.println("В коллекции нет фильмов, жанры которого меньше " + argument.toUpperCase());
        }
    }

    @Override
    public boolean isValidArgument(String argument) {
        try {
            MovieGenre.valueOf(argument.toUpperCase());
            return true;
        }
        catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
