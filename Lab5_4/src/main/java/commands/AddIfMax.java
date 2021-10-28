package commands;


import data.Movie;
import main.MovieHashSet;
import main.Request;

/**
 * Класс команды добавления элемента в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 */
public class AddIfMax extends Command {
    private Request request;
    private MovieHashSet collection;

    /**
     * Конструктор команды add_if_max
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public AddIfMax(Request request, MovieHashSet collection){
        super("add_if_max","","добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.request = request;
        this.collection = collection;
    }

    @Override
    public void execute(String argument) {
        Movie newMovie = request.requestMovie(collection.createId());
        if(collection.isEmpty()){
            collection.add(newMovie);
        }
        else if(newMovie.compareTo(collection.getMaxElement()) > 0){
            collection.add(newMovie);
        }
    }
}
