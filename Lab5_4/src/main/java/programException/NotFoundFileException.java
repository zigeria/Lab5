package programException;

public class NotFoundFileException extends FileException {
    @Override
    public String getMessage() {
        return "Файл не найден";
    }
}
