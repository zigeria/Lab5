package copy.Proga;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

//import static Proga.FromСonsole.getNewProduct;
//import static Proga.FromСonsole.getUpdateProduct;

public class ProductTreeMap{
    private Map<Integer, Product> treeMap = new TreeMap();

    public void longLine(){
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public void dottedLine(){
        System.out.println("---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---    ---");
    }
    public boolean checkEmpty(){
        return treeMap.isEmpty();
    }
    public boolean checkKey(Integer key){
        return treeMap.containsKey(key);
    }

    //вывести справку по доступным командам
    public void help(){
        longLine();
        System.out.format("%67s\n%67s\n", "Вам доступны следующие команды", "------------------------------");
        System.out.format("%36s %s \n", "help :", "Вывод справки по доступным командам");
        System.out.format("%36s %s \n", "info :", "Вывод информацию о коллекции");
        System.out.format("%36s %s \n", "show :", "Вывод всех элементов коллекции");
        System.out.format("%36s %s \n", "save :", "Сохранение коллекции в файл");
        System.out.format("%36s %s \n", "exit :", "Завершение программы (без сохранения в файл)");
        System.out.format("%36s %s \n", "clear :", "Очистка коллекции");
        System.out.format("%36s %s \n", "history :", "Вывод последних 9 команд (без их аргументов)");
        System.out.format("%36s %s \n", "insert key :", "Добавление нового элемента с заданным ключом");
        System.out.format("%36s %s \n", "update_id id :", "Обновление значения элемента коллекции, id которого равен заданному");
        System.out.format("%36s %s \n", "remove_key key :", "Удаление элемента из коллекции по его ключу");
        System.out.format("%36s %s \n", "print_descending :", "Вывод элементов коллекции в порядке убывания");
        System.out.format("%36s %s \n", "remove_lower element :", "Удаление из коллекции всех элементов, меньше, чем заданный");
        System.out.format("%36s %s \n", "remove_greater element :", "Удаление из коллекции всех элементов, превышающих заданный");
        System.out.format("%36s %s \n", "execute_script file_name :", "Исполнение скрипта из указанного файла");
        System.out.format("%36s %s \n", "print_unique_manufacturer :", "Вывод уникальных значений поля manufacturer всех элементов в коллекции");
        System.out.format("%36s %s \n", "filter_unitOfMeasure unitOfMeasure :", "Вывод элементов, значение поля unitOfMeasure которых меньше заданного");
        longLine();
    }

    //вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
    public void info(){
        longLine();
        System.out.format("%-22s %s \n","Тип коллекции:", treeMap.getClass());
        System.out.format("%-22s %s \n","Количество элементов:", treeMap.size());
    }

    //вывести в стандартный поток вывода все элементы коллекции в строковом представлении
    public void show(){
        longLine();
        System.out.println("Элементы коллекции");
        System.out.println("------------------");
        for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
            dottedLine();
            System.out.println("\n" + entry.getValue().toString());
        }
        longLine();
    }

    //добавить новый элемент с заданным ключом
    public void insert(String key,Scanner scanner, ProductTreeMap collection){
        int int_key;
        try{
            int_key = Integer.parseInt(key);
            treeMap.put(int_key, FromСonsole.getNewProduct(scanner,0));
        }
        catch (NumberFormatException e){
            System.out.println("Значение ключа не int");
            treeMap.put(FromСonsole.getNewKey(scanner, collection), FromСonsole.getNewProduct(scanner,0));
        }
    }

    //обновить значение элемента коллекции, id которого равен заданному
    public void update(String id, Scanner scanner){
        int int_id;
        boolean hasId = false;
        while (true){
            try {
                int_id = Integer.parseInt(id);
                for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
                    if (entry.getValue().getId() == int_id){
                        treeMap.put(entry.getKey(), FromСonsole.getNewProduct(scanner, int_id));
                        hasId = true;
                        break;
                    }
                }
                if (!hasId){
                    System.out.println("Продукт с заданным идентификатором не найден. Введите id");
                    id = scanner.nextLine();
                    continue;
                }
                break;
            }
            catch (NumberFormatException e){
                System.out.println("id должен быть типа int. Введите id.");
                id = scanner.nextLine();
            }
        }

    }

    //удалить элемент из коллекции по его ключу
    public void remove(String key, Scanner scanner, ProductTreeMap collection){
        int int_key;
        try{
            int_key = Integer.parseInt(key);
            if(checkKey(int_key)){
                treeMap.remove(key);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Значение ключа не int");
            treeMap.remove(FromСonsole.getCollectionKey(scanner, collection));
        }
        System.out.println("Элемент удален");
    }

    //удалить из коллекции все элементы, превышающие заданный
    public void remove_greater(String productName, Scanner scanner){
        ArrayList<Integer> arrayKey  = new ArrayList<>(0);
        int counter = 0;
        for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
            if (entry.getKey() > findProduct(productName,scanner) ){
                arrayKey.add(entry.getKey());
                counter++;
            }
        }
        for (int i = 0; i < arrayKey.size(); i++){
            treeMap.remove(arrayKey.get(i));
        }
        System.out.format("Из коллекции удалено %d элемент(а/ов)", counter);
    }

    //удалить из коллекции все элементы, меньшие, чем заданный
    public void remove_lower(String productName, Scanner scanner){
        ArrayList<Integer> arrayKey  = new ArrayList<>(0);
        int counter = 0;
        for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
            if (entry.getKey() < findProduct(productName,scanner) ){
                arrayKey.add(entry.getKey());
                counter++;
            }
        }
        for (int i = 0; i < arrayKey.size(); i++){
            treeMap.remove(arrayKey.get(i));
        }
        System.out.format("Из коллекции удалено %d элемент(а/ов)", counter);
    }

    //находит в коллекции продукт с данным именем и возвращает ключ
    public Integer findProduct(String productName, Scanner scanner){
        String name = productName;
        while (true){
            for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
                if (entry.getValue().getName().equals(name)){
                    return entry.getKey();
                }
            }
            System.out.println("Продукт не найден. Введите название продукта");
            name = scanner.nextLine();
        }
    }

    //вывести элементы коллекции в порядке убывания
    public void descending(){
        TreeMap<Integer, Product> reversTreeMap = new TreeMap(Comparator.reverseOrder());
        reversTreeMap.putAll(treeMap);

        longLine();
        System.out.println("Элементы коллекции в обратном порядке");
        System.out.println("-------------------------------------");
        for(Map.Entry<Integer, Product> map: reversTreeMap.entrySet()){
            dottedLine();
            System.out.println("\n" + map.getValue().toString());
        }
        longLine();
    }

    //очистить коллекцию
    public void clear(){
        if (!treeMap.isEmpty()) {
            treeMap.clear();
        }
        System.out.println("Коллекция пуста");
    }

    //сохранить коллекцию в файл
    public void save() throws FileNotFoundException {
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(System.getenv("FILE_SAVE")));

        StringBuilder allProduct = new StringBuilder("{\n\t\"Product\" : [");
        for(Map.Entry<Integer, Product> treeMap: treeMap.entrySet()){
            allProduct.append("{" + treeMap.getValue().toStringToJSON() + "\n\t\t},");
        }
        allProduct.deleteCharAt(allProduct.length()-1); //Удалить запятую в конце
        allProduct.append("\n\t]\n}");
        try {
            out.write(allProduct.toString());
            out.close();
            System.out.println("Коллекция сохранена в файл ProductTreeMap.json");

        }catch (IOException e){
            System.out.println("Ошибка... Возможно операция была прервана");
        }

    }

    //вывести элементы, значение поля unitOfMeasure которых меньше заданного
    public void filter_unitOfMeasure(String argUnitOfMasure, Scanner scanner){
        UnitOfMasure unitOfMasure = checkArgUnitOfMasure(argUnitOfMasure, scanner);
        for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
            if (entry.getValue().getUnitOfMeasure().ordinal() < unitOfMasure.ordinal()){
                System.out.println("\n" + entry.getValue().toString());
            }
        }
    }

    //Проверяет, является ли введенное значение одной из единиц измерения используемых в программе,
    // если не является, просит пользователя ввести новое значение
    public UnitOfMasure checkArgUnitOfMasure(String argUnitOfMasure, Scanner scanner){
        UnitOfMasure unitOfMasure = null;
        String StrUnitOfMasure = argUnitOfMasure;
        while (true){
            try {
                unitOfMasure = UnitOfMasure.valueOf(StrUnitOfMasure);
                return unitOfMasure;
            } catch (IllegalArgumentException e) {
                System.out.println("Единица измерения введена некорректно ..Выберите из списка:\n" + UnitOfMasure.values());
                StrUnitOfMasure = scanner.nextLine();
            }
        }
    }


    //вывести уникальные значения поля manufacturer всех элементов в коллекции
    public void unique_manufacturer(){
        ArrayList<String> arrayManufacturer  = new ArrayList<>(0);
        for(Map.Entry<Integer, Product> entry: treeMap.entrySet()){
            for (int i = 0; i < arrayManufacturer.size(); i++) {
                if(arrayManufacturer.get(i).equals(entry.getValue().getManufacturer().getName())){
                    arrayManufacturer.remove(i);
                    break;
                }
            }
            arrayManufacturer.add(entry.getValue().getManufacturer().getName());
        }
        System.out.println("Уникальные значения поля manufacturer всех элементов в коллекции");
        for (int i = 0; i < arrayManufacturer.size(); i++){
            System.out.println(arrayManufacturer.get(i));
        }
    }

}
