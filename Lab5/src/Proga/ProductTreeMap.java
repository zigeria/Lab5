package Proga;

import java.io.*;
import java.util.*;

/**
 * Класс, управляющий коллекцией продуктов
 */
public class ProductTreeMap{
    private Map<Integer,Product> treeMap = new TreeMap();

//Вспомогательные методы

    public void longLine(){
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }
    public void dottedLine(){
        System.out.println("---    ---    ---    ---    ---    ---    ---    ---    ---    ---");
    }

    /**
     * Проверяет пуста ли коллекция
     * @return Возвращает true, если в коллекции нет ни одного сопоставления ключ-значение
     */
    public boolean isEmpty(){
        if(treeMap.isEmpty()){
            System.out.println("Коллекция пуста");
        }
        return treeMap.isEmpty();
    }

    /**
     * @param key Ключ
     * @return Возвращает значение true, если эта карта содержит сопоставление для указанного ключа.
     */
    public boolean checkKey(Integer key){
        return treeMap.containsKey(key);
    }

    /**
     * Находит в коллекции продукт с данным именем и возвращает ключ
     */
    public Integer findProduct(String productName, Scanner scanner, String where){
        String name = productName;
        while (true){
            for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
                if (entry.getValue().getName().equals(name)){
                    return entry.getKey();
                }
            }
            if (where.equals("fileScript")){
                return null;
            }
            System.out.println("Продукт не найден. Введите название продукта");
            name = scanner.nextLine();
        }
    }


    /**
     * Проверяет, является ли введенное значение одной из единиц измерения используемых в программе,
     *      если не является, просит пользователя ввести новое значение
     * @param unitOfMasure_str единица измерения введенная пользователем
     * @param where откуда вызван метод. Из консоли или файла
     * @return единицу измерения продукта
     */
    public UnitOfMasure checkArgUnitOfMasure(String unitOfMasure_str, Scanner scanner, String where){
        UnitOfMasure unitOfMasure;
        try {
            unitOfMasure = UnitOfMasure.getUnitOfMasure(unitOfMasure_str);
            return unitOfMasure;
        } catch (IllegalArgumentException e) {
            if(where.equals("fileScript")){
                System.out.println("В файле единица измерения указана некорректно." );
                return null;
            }
            return FromСonsole.getUnitOfMasure(scanner);
        }
    }


//Комады без аргументов

    /**
     * Выводит справку по доступным командам
     */
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

    /**
     * Выводит информацию о коллекции (тип, количество элементов)
     */
    public void info(){
        longLine();
        System.out.format("%-22s %s \n","Тип коллекции:", treeMap.getClass());
        System.out.format("%-22s %s \n","Количество элементов:", treeMap.size());
        longLine();
    }

    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public void show(){
        longLine();
        System.out.println("Элементы коллекции");
        System.out.println("------------------");
        for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
            dottedLine();
            System.out.println("\n" + entry.getValue().toString() + "\n");
        }
        longLine();
    }

    /**
     * Выводит все элементы коллекции в обратном порядке
     */
    public void descending(){
        TreeMap<Integer, Product> reversTreeMap = new TreeMap(Comparator.reverseOrder());
        reversTreeMap.putAll(treeMap);

        longLine();
        System.out.println("Элементы коллекции в обратном порядке");
        System.out.println("-------------------------------------");
        for(Map.Entry<Integer,Product> map: reversTreeMap.entrySet()){
            dottedLine();
            System.out.println("\n" + map.getValue().toString());
        }
        longLine();
    }

    /**
     * Очищает коллекцию
     */
    public void clear(){
        treeMap.clear();
        System.out.println("Коллекция очищена");
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void save(Scanner scanner) {
        OutputStreamWriter out;
        try{
            out = new OutputStreamWriter(new FileOutputStream(System.getenv("FILE_SAVE")));
        }
        catch (FileNotFoundException e){
            out = FromСonsole.getOutputStreamWriter(scanner);
        }

        StringBuilder allProduct = new StringBuilder("{\n\t\"Product\" : [");
        for(Map.Entry<Integer,Product> treeMap: treeMap.entrySet()){
            allProduct.append("{" + treeMap.getValue().toStringToJSON() + "\n\t\t},");
        }
        allProduct.deleteCharAt(allProduct.length()-1); //Удалить запятую в конце
        allProduct.append("\n\t]\n}");
        try {
            out.write(allProduct.toString());
            out.close();
            System.out.println("Коллекция сохранена в файл");

        }catch (IOException e){
            System.out.println("Ошибка... Возможно операция была прервана");
        }

    }

    /**
     * Выводит уникальные значения поля manufacturer всех элементов в коллекции
     */
    public void unique_manufacturer(){
        ArrayList<String> arrayManufacturer  = new ArrayList<>(0);
        for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
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


//Комады с аргуменами
    /**
     * Добавляет новый элемент с заданным ключом
     */
    public void insert(String key_str,Scanner scanner, ProductTreeMap collection, String where){
    int key;
    try{
        key = Integer.parseInt(key_str);
        if (!checkKey(key)){
            treeMap.put(key, FromСonsole.getNewProduct(scanner,0));
        }
        else {
            System.out.println("В коллекции уже есть элемент с таким значением ключа");
            treeMap.put(FromСonsole.getKey(scanner, collection, "insert"), FromСonsole.getNewProduct(scanner,0));
        }
    }
    catch (NumberFormatException e){
        System.out.println("Значение ключа не int");
        if (where.equals("fileScript")){
            System.out.println("Ошибка в файле. Выполнение команды \"insert\" будет пропущено.");
        }
        else{
            treeMap.put(FromСonsole.getKey(scanner, collection, "insert"), FromСonsole.getNewProduct(scanner,0));
        }
    }
}



    /**
     * Обновляет значение элемента коллекции
     * @param id элемента коллекции, который необходимо обновить
     */
    public void update(String id, Scanner scanner){
        int int_id;
        boolean hasId = false;
        while (true){
            try {
                int_id = Integer.parseInt(id);
                for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
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



    /**
     * Удаление элемента из коллекции по его ключу
     * @param key ключ элемента коллекции, который необходимо удалить
     * @param collection коллекция,из которой удаляется элемент
     * @param where откуда вызван метод. Из консоли или файла
     */
    public void remove(String key, Scanner scanner, ProductTreeMap collection, String where){
        int int_key;
        try{
            int_key = Integer.parseInt(key);
            if(checkKey(int_key)){
                treeMap.remove(key);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Значение ключа не int");
            if (where.equals("console")){
                treeMap.remove(FromСonsole.getKey(scanner, collection, "remove"));
            }
            else if(where.equals("fileScript")){
                System.out.println("Ошибка в файле. Команда \"удаление элемента по ключу\" пропущена");
                return;
            }
        }
        System.out.println("Элемент удален");
    }

    /**
     * Удаление из коллекции всех элементов, превышающих заданный
     * @param productName Название продукта.
     * @param where откуда вызван метод. Из консоли или файла
     */
    public void remove_greater(String productName, Scanner scanner, String where){
        Integer key = findProduct(productName,scanner, where);
        if(key == null){
            System.out.println("Ошибка в файле. Элемента с таким именем нет в коллекции. Выполнение команды \"remove_greater\" пропущено");
        }
        else {
            ArrayList<Integer> arrayKey  = new ArrayList<>(0);
            int counter = 0;
            for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
                if (entry.getKey() > key){
                    arrayKey.add(entry.getKey());
                    counter++;
                }
            }
            for (int i = 0; i < arrayKey.size(); i++){
                treeMap.remove(arrayKey.get(i));
            }
            System.out.format("Из коллекции удалено %d элемент(а/ов)", counter);
        }
    }

    /**
     * Удаление из коллекции всех элементов, меньших чем заданный
     * @param productName Название продукта.
     * @param where откуда вызван метод. Из консоли или файла
     */
    public void remove_lower(String productName, Scanner scanner, String where){
        Integer key = findProduct(productName,scanner, where);
        if(key == null){
            System.out.println("Ошибка в файле. Элемента с таким именем нет в коллекции. Выполнение команды \"remove_greater\" пропущено");
        }
        else {
            ArrayList<Integer> arrayKey  = new ArrayList<>(0);
            int counter = 0;
            for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
                if (entry.getKey() < key){
                    arrayKey.add(entry.getKey());
                    counter++;
                }
            }
            for (int i = 0; i < arrayKey.size(); i++){
                treeMap.remove(arrayKey.get(i));
            }
            System.out.format("Из коллекции удалено %d элемент(а/ов)", counter);
        }
    }

    //вывести элементы, значение поля unitOfMeasure которых меньше заданного

    /**
     * Выводит элементы, значение поля unitOfMeasure которых меньше заданного
     * @param argUnitOfMasure
     * @param where откуда вызван метод. Из консоли или из файла
     */
    public void filter_unitOfMeasure(String argUnitOfMasure, Scanner scanner, String where){
        UnitOfMasure unitOfMasure = checkArgUnitOfMasure(argUnitOfMasure, scanner, where);
        if (unitOfMasure == null){
            System.out.println("Выполнение команды \"filter_unitOfMeasure\" пропущено");
        }
        else {
            for(Map.Entry<Integer,Product> entry: treeMap.entrySet()){
                if (entry.getValue().getUnitOfMeasure().ordinal() < unitOfMasure.ordinal()){
                    System.out.println("\n" + entry.getValue().toString());
                }
            }
        }
    }
}
