package commands;



import data.Movie;
import main.MovieHashSet;
import programException.EmptyCollectionException;
import programException.ShouldNotBeEmptyException;

/**
 * Класс команды, осуществляющей фильтрацию элементов коллекции по содержанию подстроки в значении поля name
 */
public class FilterContainsName extends Command {
    private MovieHashSet collection;

    /**
     * Конструктор команды filter_contains_name
     * @param collection - изменяемая коллекция
     */
    public FilterContainsName(MovieHashSet collection){
        super("filter_contains_name"," name","вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collection = collection;
    }
    @Override
    public void execute(String argument) throws EmptyCollectionException, ShouldNotBeEmptyException {
        if (collection.isEmpty()) throw new EmptyCollectionException();
        if (argument.equals("")) throw new ShouldNotBeEmptyException();
        int count = 0;
        for (Movie movie : collection.getCollection()){
            if(movie.getName().contains(argument)){
                count++;
                System.out.println(movie);
            }
        }
        if (count == 0){
            System.out.println("В коллекции нет фильмов, в названии которых содержится" + argument);
        }
    }
}
