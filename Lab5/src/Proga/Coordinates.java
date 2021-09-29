package Proga;
/**
 * Координаты продукта
 */
public class Coordinates {
    private long x; //Значение поля должно быть больше -379
    private double y;

    public Coordinates(long x, double y){
        this.x = x;
        this.y = y;
    }
    public String toStringToJSON() {
        return "\n\t\t\t\"x\" : " + x +
                ",\n\t\t\t\"y\" : " + y;
    }
    @Override
    public String toString() {
        return "\n\tx = " + x +
                "\n\ty = " + y;
    }
}
