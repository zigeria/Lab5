package programException;

public class FileCanNotWriteException extends FileException{
    @Override
    public String getMessage() {
        return "Файл не доступен для записи";
    }
}
