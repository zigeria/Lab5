package data;

/**
 * Жанр
 */
public enum MovieGenre {
    WESTERN,
    COMEDY,
    TRAGEDY,
    HORROR;

    /**
     * Список жанров
     * @return возвращает список жанров
     */
    public static String genreList(){
        String genreList = "";
        for(MovieGenre movieGenre : MovieGenre.values()){
            genreList += movieGenre.name() + "\n";
        }
        return genreList;
    }
}
