package commands;

import main.MovieHashSet;
import main.Request;

/**
 * Класс команды добавления элемента в коллекцию
 */
public class Add extends Command {
    private Request request;
    private MovieHashSet collection;

    /**
     * Конструктор команды add
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public Add(Request request, MovieHashSet collection){
        super("add","","добавить новый элемент в коллекцию");
        this.request = request;
        this.collection = collection;
    }

    @Override
    public void execute(String argument) {
        collection.add(request.requestMovie(collection.createId()));
    }

}
