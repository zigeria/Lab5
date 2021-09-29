package Proga;

import java.io.*;
import java.util.Scanner;

/**
 * Класс для работы с консолью.
 */
public class FromСonsole {

    public static String getProductName(Scanner scanner){
        String name;
        while (true) {
            System.out.println("Введите название продукта: ");
            name = scanner.nextLine();
            if (FindCommand.arg(name) || name.equals("")) {
                System.out.println("У продукта обязательно должно быть название!");
                System.out.println("Необходимо ввести 1 слово, содержащее символы слова");
                continue;
            }
            return name;
        }
    }

    public static long getCoordinateX(Scanner scanner){
        String x_str;
        long x;
        while(true){
            System.out.println("Координата Х должна быть типа long и больше -379. Введите координату Х : ");
            try {
                x_str =scanner.nextLine();
                x = Long.parseLong(x_str);
                if (x <= -379){
                    System.out.println("Координата Х не может быть меньше или равна -379. Пожалуйста вводите значения согласно условиям...");
                    continue;
                }
                return x;
            }
            catch (NumberFormatException e){
                System.out.println("К сожалению, введенная координата не принадлежит типу long. Попробуйте снова. У Вас получится)");
            }
        }
    }
    public static double getCoordinateY(Scanner scanner){
        String y_str;
        double y;
        while (true){
            System.out.println("Координата У должна принадлежать типу double. Введите координату У: ");
            try {
                y_str = scanner.nextLine();
                y = Double.parseDouble(y_str);
                return y;
            }
            catch (NumberFormatException e){
                System.out.println("Введенное значение не удовлетворяет условию. Попробуйте ввести другое число.");
            }
        }
    }

    public static Double getPrice(Scanner scanner){
        String price_str;
        Double price;
        while (true) {
            System.out.println("Введите стоимость продукта в формате double. Значение должно быть больше 0: ");
            try {
                price_str = scanner.nextLine();
                price = Double.parseDouble(price_str);
                if (price <= 0) {
                    System.out.println("Будьте внимательны значение должно быть больше 0!");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Упс! Цена должна быть указана в формате double. Попробуйте ввести другое значение.");
            }
        }
    }

    public static UnitOfMasure getUnitOfMasure(Scanner scanner){
        UnitOfMasure unitOfMasure;
        while (true) {
            System.out.format("Выберите единицу измерения из списка:\n\t%s\n\t%s\n\t%s\n", UnitOfMasure.values());
            String unitOfMasure_str = scanner.nextLine();
            try {
                unitOfMasure = UnitOfMasure.getUnitOfMasure(unitOfMasure_str);
                return unitOfMasure;
            } catch (IllegalArgumentException e) {
                System.out.println("В списке нет указанной единицы измерения(");
            }
        }
    }

    public static String getOrganizationName(Scanner scanner){
        String organizationName;
        while (true) {
            System.out.println("Введите название предприятия: ");
            organizationName = scanner.nextLine();
            if (FindCommand.arg(organizationName) || organizationName.equals("")) {
                System.out.println("У предприятия должно быть название!");
                System.out.println("Необходимо ввести 1 слово, содержащее символы слова");
                continue;
            }
            return organizationName;
        }
    }

    public static long getAnnualTernover(Scanner scanner){
        String annualTernover_str;
        long annualTernover;
        while(true){
            System.out.println("Введите размер годового оборота предприятия. Значение должно соответствовать типу long и быть положительным: ");
            try {
                annualTernover_str =scanner.nextLine();
                annualTernover = Long.parseLong(annualTernover_str);
                if (annualTernover <= 0){
                    System.out.println("Значение годового оборота должно быть больше 0. Попробуйте еще раз");
                    continue;
                }
                return annualTernover;
            }
            catch (NumberFormatException e){
                System.out.println("Очень жаль( Значение годового оборота не принадлежит типу long. Пожалуйста введите другое число. ");
            }
        }
    }

    public static Address getNewAddress(Scanner scanner){
        String street;
        while (true) {
            System.out.println("Введите адрес предприятия (Не более 72 символов): ");
            street = scanner.nextLine();
            if (FindCommand.arg(street)) {
                System.out.println("Некорректный ввод!");
                System.out.println("В адресе должны содержаться символы слова");
                continue;
            }
            else if (street.length() > 72){
                System.out.println("К сожалению, адрес слишком длинный");
                continue;
            }
            return new Address(street);
        }
    }

    public static Product getNewProduct(Scanner scanner, int id) {
        String name = getProductName(scanner);
        Coordinates coordinates = new Coordinates(getCoordinateX(scanner), getCoordinateY(scanner));
        Double price = getPrice(scanner);
        UnitOfMasure unitOfMasure = getUnitOfMasure(scanner);
        Organization manufacturer = new Organization(getOrganizationName(scanner), getAnnualTernover(scanner), getNewAddress(scanner));

        if (id == 0) {
            return new Product(name, coordinates, price, unitOfMasure, manufacturer);
        }
        else {
            return new Product(id, name, coordinates, price, unitOfMasure, manufacturer);
        }
    }

    public static Integer getKey(Scanner scanner, ProductTreeMap collection, String command){
        String key_str;
        Integer key;
        while (true) {
            System.out.println("Пожалуйста введите ключ элемента");
            try{
                key_str = scanner.nextLine();
                key = Integer.parseInt(key_str);
                if (command.equals("insert") && collection.checkKey(key)){
                    System.out.println("В коллекции уже есть элемент с таким значением ключа");
                    continue;
                }
                else if (command.equals("remove")&& !collection.checkKey(key)){
                    System.out.println("В коллекции нет элемента с таким значением ключа");
                    continue;
                }
                return key;
            }
            catch (NumberFormatException  e){
                System.out.println("Ключ должен быть в формате числа int");
            }
        }
    }

    public static OutputStreamWriter getOutputStreamWriter(Scanner scanner){
        String fileName;
        while(true){
            System.out.println("Файл не найден. Введите корректный путь к файлу");
            fileName = scanner.nextLine();
            try {
                return new OutputStreamWriter(new FileOutputStream(fileName));
            }
            catch (FileNotFoundException e){
                continue;
            }
        }
    }
    public static BufferedInputStream getBufferedInputStream(Scanner scanner){
        String fileName;
        while(true){
            System.out.println("Файл не найден. Введите корректный путь к файлу");
            fileName = scanner.nextLine();
            try {
                return new BufferedInputStream(new FileInputStream(fileName));
            }
            catch (FileNotFoundException e){
                continue;
            }
        }
    }
}
