package data;

/**
 * Координаты
 */
public class Coordinates {
    private Integer x; //Максимальное значение поля: 871, Поле не может быть null
    private float y;

    /**
     * Конструктор координат
     * @param x - координата х
     * @param y - координата у
     */
    public Coordinates(Integer x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return Возвращает координату х
     */
    public Integer getX() {
        return x;
    }

    /**
     * @return Возвращает координату у
     */
    public float getY() {
        return y;
    }

    /**
     * Строковое представление координат
     * @return Возвращает строковое представление координат
     */
    @Override
    public String toString() {
        return "\n\tX : " + x + "\n\tY : " + y;
    }

}
