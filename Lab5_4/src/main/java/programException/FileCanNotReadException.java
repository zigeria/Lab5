package programException;


public class FileCanNotReadException extends FileException{
    @Override
    public String getMessage() {
        return "Файл нельзя прочитать";
    }
}
