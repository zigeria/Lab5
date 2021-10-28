package programException;

public class NotFileException extends FileException{
    @Override
    public String getMessage() {
        return "Не является файлом";
    }
}
