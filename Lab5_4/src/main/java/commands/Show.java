package commands;


import data.Movie;
import main.MovieHashSet;
import programException.EmptyCollectionException;

/**
 * Класс команды демонстрации всех элементов коллекции
 */
public class Show extends Command {
    private MovieHashSet collection;

    /**
     * Конструктор команды show
     * @param collection - изменяемая коллекция
     */
    public Show(MovieHashSet collection) {
        super("show","","вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collection = collection;
    }

    @Override
    public void execute(String argument) throws EmptyCollectionException {
        if(collection.isEmpty()) throw new EmptyCollectionException();
        System.out.println("-----     -----     -----     -----     -----     ------");
        for(Movie movie : collection.sortCollection()){
            System.out.println(movie);
            System.out.println("-----     -----     -----     -----     -----     ------");
        }
    }
}
