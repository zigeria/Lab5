package commands;


import main.FileWork;
import main.MovieHashSet;
import main.Request;
import programException.ElementNotFoundException;
import programException.EmptyCollectionException;

/**
 * Класс команды обновления элемента по id
 */
public class UpdateId extends Command {
    private Request request;
    private MovieHashSet collection;

    /**
     * Конструктор команды update
     * @param request - объект класса Request
     * @param collection - изменяемая коллекция
     */
    public UpdateId(Request request, MovieHashSet collection) {
        super("update"," id","обновить значение элемента коллекции, id которого равен заданному");
        this.request = request;
        this.collection = collection;
    }

    @Override
    public void execute(String argument) throws EmptyCollectionException, ElementNotFoundException, NumberFormatException{

        if (collection.isEmpty()) throw new EmptyCollectionException();
        if (!isValidArgument(argument)){
            if(FileWork.isFromScript()){
                System.out.println("id должен быть числом int" );
                return;
            }
            else {
                throw new NumberFormatException();
            }
        }
        int id = Integer.parseInt(argument);

        if(collection.getMovieById(id) == null){
            if(FileWork.isFromScript()){
                System.out.println("В коллекции нет элемента с таким id" );
                return;
            }
            else {
                throw new ElementNotFoundException();
            }
        }
        RemoveById removeById = new RemoveById(collection);
        removeById.execute(argument);
        collection.add(request.requestMovie(id));
    }

    @Override
    public boolean isValidArgument(String argument){
        try {
            Integer.parseInt(argument);
            return true;
        }
        catch (NumberFormatException exception){
            return false;
        }
    }
}
