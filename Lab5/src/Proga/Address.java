package Proga;
/**
 * Адрес организации производителя
 */
public class Address {
    private String street; //Длина строки не должна быть больше 72, Поле не может быть null

    public  Address(String street){
        this.street = street;
    }

    public String toStringToJSON(){
        return "\n\t\t\t\t\"Улица\" : \"" + street + "\"";
    }

    @Override
    public String toString() {
        return street;
    }
}
