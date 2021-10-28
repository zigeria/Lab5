package main;


import commands.*;
import data.MovieGenre;
import programException.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Класс организующий работу с командами
 */
public class CommandOrganizer {
    /**
     * Список, содержащий все команды
     */
    private ArrayList<Command> commands = new ArrayList<>();
    public static Stack<String> fileNameHistory = new Stack<>();
    private HashMap<String,Command> commandHashMap = new HashMap<>();

    private Command help;
    private Command info;
    private Command show;
    private Command add;
    private Command updateId;
    private Command removeById;
    private Command clear;
    private Command save;
    private Command executeScript;
    private Command exit;
    private Command addIfMax;
    private Command removeGreater;
    private Command removeLower;
    private Command filterContainsName;
    private Command filterLessThanGenre;
    private Command printDescending;
    private MovieHashSet collection;
    private Request request;

    /**
     * Конструктор
     * @param request -- объект класса Request
     * @param collection - изменяемая коллекция
     */
    public CommandOrganizer(MovieHashSet collection, Request request) {
        this.collection = collection;
        this.request = request;
        this.info = new Info(collection);
        this.show = new Show(collection);
        this.add = new Add(request,collection);
        this.updateId =new UpdateId(request,collection);
        this.removeById = new RemoveById(collection);
        this.clear = new Clear(collection);
        this.save = new Save(collection,request);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript(collection,request,this);
        this.addIfMax = new AddIfMax(request,collection);
        this.removeGreater = new RemoveGreater(collection,request);
        this.removeLower = new RemoveLower(collection,request);
        this.filterContainsName = new FilterContainsName(collection);
        this.filterLessThanGenre = new FilterLessThenGenre(collection,request);
        this.printDescending = new PrintDescending(collection);
        this.help = new Help(this);

        commands.add(help);
        commands.add(info);
        commands.add(show);
        commands.add(add);
        commands.add(updateId);
        commands.add(removeById);
        commands.add(clear);
        commands.add(save);
        commands.add(executeScript);
        commands.add(exit);
        commands.add(addIfMax);
        commands.add(removeGreater);
        commands.add(removeLower);
        commands.add(filterContainsName);
        commands.add(filterLessThanGenre);
        commands.add(printDescending);

        commandHashMap.put(help.getCommandName(), help);
        commandHashMap.put(info.getCommandName(), info);
        commandHashMap.put(show.getCommandName(), show);
        commandHashMap.put(add.getCommandName(), add);
        commandHashMap.put(updateId.getCommandName(), updateId);
        commandHashMap.put(removeById.getCommandName(), removeById);
        commandHashMap.put(clear.getCommandName(), clear);
        commandHashMap.put(save.getCommandName(), save);
        commandHashMap.put(executeScript.getCommandName(), executeScript);
        commandHashMap.put(exit.getCommandName(), exit);
        commandHashMap.put(addIfMax.getCommandName(), addIfMax);
        commandHashMap.put(removeGreater.getCommandName(), removeGreater);
        commandHashMap.put(removeLower.getCommandName(), removeLower);
        commandHashMap.put(filterContainsName.getCommandName(), filterContainsName);
        commandHashMap.put(filterLessThanGenre.getCommandName(), filterLessThanGenre);
        commandHashMap.put(printDescending.getCommandName(), printDescending);

    }

    /**
     * Получает список команд
     * @return Возвращает список команд
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Очищает стек, который хранит имена файлов
     */
    public void clear(){
        fileNameHistory.clear();
    }

    /**
     * Осуществляет исполнение команды
     * @param command - массив строк, содержащий команду и аргумент
     */
    public void executeCommand(String[] command){
        try {
            if (commandHashMap.containsKey(command[0])){
                commandHashMap.get(command[0]).execute(command[1]);
            }
            else {
                System.out.println("Команда " + command[0] + " не найдена. Введите help, чтобы посмотреть существующие команды");
            }
        }
        catch (EmptyCollectionException exception){
            System.out.println(exception.getMessage());//коллекция пуста
        }
        catch (ShouldNotBeEmptyException exception){
            System.out.println("Аргумент не может быть пустым");
        }
        catch (ElementNotFoundException exception){
            System.out.println(exception.getMessage());//"В коллекции нет элемента с таким id"
            System.out.println("Введите id в виде целого числа (int)");
            executeCommand(new String[]{command[0],request.requestAnotherArg()});
        }
        catch (NumberFormatException exception){
            System.out.println("Неверно введен id");
            System.out.println("Введите id в виде целого числа (int)");
            executeCommand(new String[]{command[0],request.requestAnotherArg()});
        }
        catch (GenreNotFoundException exception){
            System.out.println(exception.getMessage());//Неверно введен жанр

            System.out.println("Выберите жанр из списка\n" + MovieGenre.genreList());
            executeCommand(new String[]{command[0],request.requestAnotherArg()});
        }
        catch (RecursionException exception){
            System.out.println(exception.getMessage());
        }
        catch (FileCanNotReadException| NotFileException | NotFoundFileException exception){
            System.out.println(exception.getMessage());
            System.out.println("Введите другое имя файла");
            executeCommand(new String[]{command[0],request.requestAnotherArg()});
        }
        catch (MyCollectionException exception){
            System.out.println("Непредвиденная ошибка");
        }

    }

}