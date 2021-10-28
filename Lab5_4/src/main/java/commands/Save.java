package commands;


import main.FileWork;
import main.MovieHashSet;
import main.Request;

/**
 * Класс команды сохранения в файл
 */
public class Save extends Command {
    MovieHashSet collection;
    Request request;

    /**
     * Конструктор команды save
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public Save(MovieHashSet collection, Request request) {
        super("save","","сохранить коллекцию в файл");
        this.collection = collection;
        this.request = request;
    }

    @Override
    public void execute(String argument) {
        FileWork.writeCollectionToCSV(collection,request);
    }
}
