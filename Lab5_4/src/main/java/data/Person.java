package data;

/**
 * Человек
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Location location; //Поле не может быть null

    /**
     * Конструктор человека
     * @param name - имя
     * @param height - рост
     * @param eyeColor - цвет глаз
     * @param location - место рождения
     */
    public Person(String name, long height, Color eyeColor, Location location){
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.location = location;
    }

    /**
     * @return Возвращает имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает цвет глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * @return Возвращает место рождения
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return Возвращает рост
     */
    public long getHeight() {
        return height;
    }

    /**
     * Строковое представление характеристик человека
     * @return Возвращает строковое представление характеристик человека
     */
    @Override
    public String toString() {
        return "\n\tИмя : " + name + '\'' +
                "\n\tРост : " + height +
                "\n\tЦвет глаз : " + eyeColor +
                "\n\tМесто рождения : " + location;
    }
}
