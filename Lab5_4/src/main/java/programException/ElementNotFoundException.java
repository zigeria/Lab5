package programException;


public class ElementNotFoundException extends InvalidArgumentException{
    @Override
    public String getMessage() {
        return "В коллекции нет элемента с таким id";
    }
}
