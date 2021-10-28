package commands;


import main.MovieHashSet;

/**
 * Класс команды, выводящей информацию о коллекции
 */
public class Info extends Command {
    private MovieHashSet collection;

    /**
     * Конструктор команды info
     * @param collection - изменяемая коллекция
     */
    public Info(MovieHashSet collection) {
        super("info","", "вывести информацию о коллекции");
        this.collection = collection;
    }

    @Override
    public void execute(String argument) {
        System.out.println("Сведения о коллекции:");
        System.out.format("%-22s %s \n","Тип коллекции:", collection.getCollectionClass());
        System.out.format("%-22s %s \n","Количество элементов:", collection.size());
    }
}
