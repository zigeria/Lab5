package programException;

public class GenreNotFoundException extends InvalidArgumentException{
    @Override
    public String getMessage() {
        return "Неверно введен жанр";
    }
}
