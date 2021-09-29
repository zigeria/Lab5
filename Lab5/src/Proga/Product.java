package Proga;

import java.time.ZonedDateTime;

/**
 * Продукт.
 * Элементы данного класса будут храниться в коллекции
 */
public class Product {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private UnitOfMasure unitOfMeasure; //Поле может быть null
    private Organization manufacturer; //Поле не может быть null

    private static int ID = 1;
    public Product(Integer id, String name,Coordinates coordinates,Double price,UnitOfMasure unitOfMeasure,Organization manufacturer){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = ZonedDateTime.now();
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public Product(String name,Coordinates coordinates,Double price,UnitOfMasure unitOfMeasure,Organization manufacturer){
        id = ID++;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = ZonedDateTime.now();
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public UnitOfMasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Идентификатор : " + id +
                "\nНаименование : " + name +
                "\nКоординаты : " + coordinates +
                "\nДата изготовления : " + creationDate +
                "\nЦена : " + price +
                "\nЕдиницы измерения : " + unitOfMeasure +
                "\nИзготовитель : " + manufacturer;
    }
    public String toStringToJSON(){
        return "\n\t\t\"Идентификатор\" : " + id +
                ",\n\t\t\"Наименование\" : \"" + name + "\"" +
                ",\n\t\t\"Координаты\" : {" + coordinates.toStringToJSON() + "\n\t\t\t}" +
                ",\n\t\t\"Дата изготовления\" : \"" + creationDate + "\""+
                ",\n\t\t\"Цена\" : " + price +
                ",\n\t\t\"Единицы измерения\" : \"" + unitOfMeasure + "\""+
                ",\n\t\t\"Изготовитель\" : {" + manufacturer.toStringToJSON() + "\n\t\t\t}";
    }

}
