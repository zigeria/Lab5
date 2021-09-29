package copy.Proga;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FromСonsole {
    public static Product getNewProduct(Scanner scanner, int id) {
        String name = null;
        while (true) {
            System.out.println("Введите название продукта: ");
            scanner.nextLine();
            name = scanner.nextLine();
            if (name.equals("")) {
                System.out.println("У продукта обязательно должно быть название!");
                continue;
            }
            break;
        }
        Coordinates coordinates = getNewCoordinates(scanner);

        Double price = null;
        while (true) {
            System.out.println("Введите стоимость продукта в формате double. Значение должно быть больше 0: ");
            try {
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Будьте внимательны значение должно быть больше 0!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Упс! Цена должна быть указана в формате double. Попробуйте ввести другое значение.");
                scanner.nextLine();
            }
        }

        UnitOfMasure unitOfMasure = null;
        while (true) {
            System.out.format("Выберите единицу измерения из списка:\n\t%s\n\t%s\n\t%s\n", UnitOfMasure.values());
            String s = scanner.nextLine();
            try {
                unitOfMasure = UnitOfMasure.valueOf(s);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("В списке нет указанной единицы измерения(");
            }
        }

        Organization manufacturer = getNewOrganization(scanner);
        if (id == 0) {
            return new Product(name, coordinates, price, unitOfMasure, manufacturer);
        }
        else {
            return new Product(id, name, coordinates, price, unitOfMasure, manufacturer);
        }
    }

   public static Coordinates getNewCoordinates(Scanner scanner){
       System.out.println("Введите координаты: ");

       long x;
       while(true){
           System.out.println("Координата Х должна быть типа long и больше -379. Введите координату Х : ");
           try {
               x = scanner.nextLong();
               if (x <= -379){
                   System.out.println("Координата Х не может быть меньше или равна -379. Пожалуйста вводите значения согласно условиям...");
                   scanner.nextLine();
                   continue;
               }
               break;
           }
           catch (InputMismatchException e){
               System.out.println("К сожалению, введенная координата не принадлежит типу long. Попробуйте снова. У Вас получится)");
               scanner.nextLine();
           }
       }

       double y;
       while (true){
           System.out.println("Координата У должна принадлежать типу double. Введите координату У: ");
           try {
               y = scanner.nextDouble();
               break;
           }
           catch (InputMismatchException e){
               System.out.println("Введенное значение не удовлетворяет условию. Попробуйте ввести другое число.");
               scanner.nextLine();
           }
       }

       Coordinates coordinates = new Coordinates(x,y);
        return new Coordinates(x,y);
   }

   public static Address getNewAddress(Scanner scanner){
       String street;
       while (true) {
           System.out.println("Введите адрес предприятия (Не более 72 символов): ");
           street = scanner.nextLine();
           if(street.equals("")) {
               System.out.println("Вы ничего не указали");
               scanner.nextLine();
               continue;
           }
           else if (street.length() > 72){
               System.out.println("К сожалению, адрес слишком длинный");
               scanner.nextLine();
               continue;
           }
           break;
       }
       return new Address(street);
   }

   public static Organization getNewOrganization(Scanner scanner){

       String nameOrganization = null;
       while (true){
           System.out.println("Введите название предприятия: ");
           nameOrganization = scanner.nextLine();
           if(nameOrganization.equals("")) {
               System.out.println("У предприятия должно быть название, иначе его не зарегистрируют!");
               scanner.nextLine();
               continue;
           }
           break;

       }

       long annualTernover;
       while (true){
           System.out.println("Введите размер годового оборота предприятия. Значение должно соответствовать типу long и быть положительным: ");
           try {
               annualTernover = scanner.nextLong();
               if (annualTernover <= 0){
                   System.out.println("Значение годового оборота должно быть больше 0. Попробуйте еще раз");
                   scanner.nextLine();
                   continue;
               }
               break;
           }
           catch (InputMismatchException e){
               System.out.println("Очень жаль( Значение годового оборота не принадлежит типу long. Пожалуйста введите другое число. ");
               scanner.nextLine();
           }
       }

       Address officialAddress = getNewAddress(scanner);

       return new Organization(nameOrganization, annualTernover, officialAddress);
   }

   public static Integer getNewKey(Scanner scanner, ProductTreeMap collection){
       Integer key = null;
       while (true) {
           System.out.println("Пожалуйста введите ключ добавляемого элемента");
           try{
               key = scanner.nextInt() ;
               if (collection.checkKey(key)) {
                   System.out.println("В коллекции уже есть элемент с таким значением ключа");
                   continue;
               }
               break;
           }
           catch (InputMismatchException e){
               System.out.println("Ключ должен быть в формате числа int");
               scanner.nextLine();
           }
       }
       return key;
   }

    public static Integer getCollectionKey(Scanner scanner, ProductTreeMap collection){
        Integer key = null;
        while (true) {
            System.out.println("Пожалуйста введите ключ элемента, который вы собираетесь удалить");
            try{
                key = scanner.nextInt() ;
                if (!collection.checkKey(key)) {
                    System.out.println("В коллекции нет элемента с таким значением ключа");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Ключ должен быть в формате числа int");
                scanner.nextLine();
            }
        }
        return key;
    }

    //public static Integer getId(){}

}
