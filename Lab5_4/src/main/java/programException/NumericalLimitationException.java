package programException;

public class NumericalLimitationException extends MyCollectionException{
    @Override
    public String getMessage() {
        return "Нарушено числовое ограничение";
    }
}
