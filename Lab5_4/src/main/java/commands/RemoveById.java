package commands;


import data.Movie;
import main.FileWork;
import main.MovieHashSet;
import programException.ElementNotFoundException;
import programException.EmptyCollectionException;

/**
 * Класс команды удаления элемента коллекции по идентификатору
 */
public class RemoveById extends Command {
    private MovieHashSet collection;

    /**
     * Конструктор команды remove_by_id
     * @param collection
     */
    public RemoveById(MovieHashSet collection){
        super("remove_by_id"," id","удалить элемент из коллекции по его id");
        this.collection = collection;
    }

    @Override
    public void execute(String argument) throws EmptyCollectionException, ElementNotFoundException, NumberFormatException {
        if (collection.isEmpty()) throw new EmptyCollectionException();

        Movie movie;
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

        if((movie = collection.getMovieById(id)) == null){
            if(FileWork.isFromScript()){
                System.out.println("В коллекции нет элемента с таким id" );
                return;
            }
            else {
                throw new ElementNotFoundException();
            }
        }
        collection.remove(movie);
    }

    @Override
    public boolean isValidArgument(String argument) {
        try {
            Integer.parseInt(argument);
            return true;
        }
        catch (NumberFormatException exception){
            return false;
        }
    }
}
