package data;

import java.time.LocalDateTime;

/**
 * Фильм
 */
public class Movie implements Comparable<Movie>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person operator; //Поле может быть null

    /**
     * Конструктор фильма
     * @param id - идентификатор
     * @param name - название
     * @param coordinates - координаты
     * @param creationDate - дата инициализации
     * @param oscarsCount - количество оскаров
     * @param genre - жанр
     * @param mpaaRating - рейтинг
     * @param operator - оператор
     */
    public Movie(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, int oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person operator){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    /**
     * @return Возвращает идентификатор
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Возвращает название фильма
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Возвращает дату инициализации
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Возвращает количество оскаров
     */
    public int getOscarsCount() {
        return oscarsCount;
    }

    /**
     * @return Возвращает жанр
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * @return Возвращает рейтинг
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * @return Возвращает рейтинг
     */
    public Person getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Идентификатор фильма: " + id +
                "\nНазвание : " + name +
                "\nМестоположение : " + coordinates +
                "\nДата создания : " + creationDate +
                "\nКоличество оскаров : " + oscarsCount +
                "\nЖанр : " + genre +
                "\nРейтинг американской ассоциации кинематографистов : " + mpaaRating +
                "\nОперетор : " + operator ;
    }


    @Override
    public int compareTo(Movie movie) {
        return id.compareTo(movie.id);
    }
}
