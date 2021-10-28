package main;


import data.Movie;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс для работы с коллекцией
 */
public class MovieHashSet {
    /**
     * Коллекция HashSet, в которой хранятся элементы Movie
     */
    private HashSet<Movie> collection =  new HashSet<>();
    /**
     * Отсортированное множество идентификаторов элементов коллекции collection
     */
    private SortedSet<Integer> setId = new TreeSet<>();
    /**
     * Файл, в котором хранится коллекция
     */
    private File fileCollection;

    /**
     * Установление значения fileCollection
     * @param fileCollection - файл, в котором хранится коллекция
     */
    public void setFileCollection(File fileCollection) {
        this.fileCollection = fileCollection;
    }

    /**
     * @return Возвращает файл, в котором хранится коллекция
     */
    public File getFileCollection() {
        return fileCollection;
    }

    /**
     * Определяет пуста коллекция или нет
     * @return true - пустая, false - непустая
     */
    public boolean isEmpty(){
        return collection.isEmpty();
    }

    /**
     * Добавляет элемент в коллекцию
     * @param movie - добавляемый элемент
     */
    public void add(Movie movie){
        collection.add(movie);
        setId.add(movie.getId());
    }

    /**
     * Очищает коллекцию
     */
    public void clear(){
        collection.clear();
        setId.clear();
    }

    /**
     * @return Возвращает коллекцию
     */
    public HashSet<Movie> getCollection() {
        return collection;
    }

    /**
     * Определяет тип коллекции
     * @return Возвращает тип коллекции
     */
    public Class getCollectionClass() {
        return collection.getClass();
    }

    /**
     * Определяет размер коллекции
     * @return Возвращает размер коллекции
     */
    public int size(){
        return collection.size();
    }

    /**
     * Сортирует коллекцию
     * @return Возвращает отсортированную коллекцию типа TreeSet
     */
    public TreeSet<Movie> sortCollection(){
        TreeSet<Movie> treeSetCollection = new TreeSet(collection);
        return treeSetCollection;
    }

    /**
     * Удаляет элемент коллекции
     * @param movie - удаляемый элемент
     */
    public void remove(Movie movie){
        setId.remove(movie.getId());
        collection.remove(movie);
    }

    /**
     * Определяет максимальный элемент коллекции
     * @return Возвращает максимальный элемент коллекции
     */
    public Movie getMaxElement(){
        return Collections.max(collection);
    }

    /**
     * Находит элемент по идентификатору
     * @param id - идентификатор элемента
     * @return Возвращает найденный элемент или null, если элемент не найден
     */
    public Movie getMovieById(int id) {
        for(Movie movie : collection){
            if(movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
    }

    /**
     * Генерирует идентификатор
     * @return Возвращает новый идентификатор
     */
    public Integer createId() {
        int id;
        if (isEmpty()) id = 1;
        else id = setId.last() + 1;
        setId.add(id);
        return id;
    }

    /**
     * Находит элемент по названию
     * @param name - название фильма
     * @return Возвращает найденный элемент или null, если элемент не найден
     */
    public Movie findMovieByName(String name){
        for (Movie movieCollection : collection){
            if (movieCollection.getName().equals(name)){
                return movieCollection;
            }
        }
        return null;
    }
}
