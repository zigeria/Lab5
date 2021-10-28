package programException;


public class EmptyCollectionException extends MyCollectionException{
    @Override
    public String getMessage() {
        return "Коллекция пуста";
    }
}
