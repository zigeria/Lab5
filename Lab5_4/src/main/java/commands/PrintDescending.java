package commands;


import data.Movie;
import main.MovieHashSet;
import programException.EmptyCollectionException;

/**
 * Класс команды, выводящей элементы коллекции в порядке убывания
 */
public class PrintDescending extends Command {
    private MovieHashSet collection;

    /**
     * Конструктор команды print_descending
     * @param collection - изменяемая коллекция
     */
    public PrintDescending(MovieHashSet collection){
        super("print_descending","","вывести элементы коллекции в порядке убывания");
        this.collection = collection;
    }
    @Override
    public void execute(String argument) throws EmptyCollectionException {
        if (collection.isEmpty()) throw new EmptyCollectionException();

        for(Movie movie : collection.sortCollection().descendingSet()){
            System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- ");
            System.out.println(movie);
        }
        System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- ");
    }
}
