package commands;

import main.MovieHashSet;
import programException.EmptyCollectionException;

/**
 * Класс команды очистки коллекции
 */
public class Clear extends Command {

    private MovieHashSet collection;

    /**
     * Конструктор команды clear
     * @param collection - изменяемая коллекция
     */
    public Clear(MovieHashSet collection){
        super("clear","","очистить коллекцию");
        this.collection = collection;
    }
    @Override
    public void execute(String argument) throws EmptyCollectionException {
        if (collection.isEmpty()) throw new EmptyCollectionException();
        collection.clear();
    }

}
