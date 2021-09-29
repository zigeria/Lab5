package copy.Proga;

public class Organization {
    private static int ID = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long annualTurnover; //Значение поля должно быть больше 0
    private Address officialAddress; //Поле может быть null

    public Organization(String name, long annualTurnover, Address officialAddres){
        id = ID++;
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.officialAddress = officialAddres;
    }
    public String toStringToJSON(){
        return "\n\t\t\t\"Идентификатор организации\" : " + id +
                ",\n\t\t\t\"Название организации\" : " + "\"" + name + "\"" +
                ",\n\t\t\t\"Годовой оборот\" : " + annualTurnover +
                ",\n\t\t\t\"Адрес\" : {" + officialAddress.toStringToJSON() +  "\n\t\t\t\t}";
    }

    @Override
    public String toString() {
        return "\n\tИдентификатор организации: " + id +
                "\n\tНазвание организации: " + name +
                "\n\tГодовой оборот: " + annualTurnover +
                "\n\tАдрес: " + officialAddress;
    }

    public String getName() {
        return name;
    }
}
