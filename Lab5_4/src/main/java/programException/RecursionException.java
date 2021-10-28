package programException;

public class RecursionException extends FileException{

    @Override
    public String getMessage() {
        return "Файл пытается исполнить сам себя";
    }
}
