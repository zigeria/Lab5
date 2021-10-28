package commands;


import data.Movie;
import main.MovieHashSet;
import main.Request;
import programException.EmptyCollectionException;

import java.util.ArrayList;

/**
 * Класс команды удаления элементов коллекции, меньших, чем заданный
 */
public class RemoveLower extends Command {

    private MovieHashSet collection;
    private Request request;

    /**
     * Конструктор команды remove_lower
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public RemoveLower(MovieHashSet collection, Request request){
        super("remove_lower","","удалить из коллекции все элементы, меньшие, чем заданный");
        this.collection = collection;
        this.request = request;
    }

    @Override
    public void execute(String argument) throws EmptyCollectionException {
        if (collection.isEmpty()) throw new EmptyCollectionException();

        ArrayList<Movie> moviesNeedDel  = new ArrayList<>();
        int counter = 0;

        String name = request.requestMovieName(null);
        Movie movie = collection.findMovieByName(name);
        if(movie!=null){
            for (Movie movieCollection : collection.getCollection()){
                if(movieCollection.compareTo(movie) < 0){
                    moviesNeedDel.add(movieCollection);
                    counter++;
                }
            }
        }

        for (int i = 0; i < moviesNeedDel.size(); i++){
            collection.remove(moviesNeedDel.get(i));
        }
        System.out.format("Из коллекции удалено %d элемент(а/ов)\n", counter);
        if (movie == null){
            System.out.println("В коллекции не было элемента с таким именем");
        }
    }
}
