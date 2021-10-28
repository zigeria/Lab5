package programException;

public class ShouldNotBeEmptyException extends MyCollectionException {
    @Override
    public String getMessage() {
        return "Ввод не может быть пустым";
    }
}
