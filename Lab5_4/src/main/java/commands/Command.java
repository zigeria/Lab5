package commands;


import programException.MyCollectionException;

/**
 * Абстрактный класс всех команд
 */
public abstract class Command {

    private String commandName;
    private String commandNameFull;
    private String description;

    /**
     * Конструктор команды
     * @param commandName Название команды
     * @param argName Название аргумента, который необходимо использовать для этой команды
     * @param description Описание команды
     */
    public Command(String commandName,String argName, String description) {
        this.commandName = commandName;
        this.commandNameFull = commandName +argName;
        this.description = description;
    }

    /**
     * @return Возвращает описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Возвращает название команды
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @return Возвращает название команды с используемым аргументом
     */
    public String getCommandNameFull() {
        return commandNameFull;
    }

    /**
     * Абстрактный метод исполнения команды
     * @param argument - аргумент исполняемой команды в строковом представлении
     * @throws programException.MyCollectionException
     */
    public abstract void execute(String argument) throws MyCollectionException;

    /**
     * Метод осуществляющий проверку аргумента исполняемой команды
     * @param argument - аргумент исполняемой команды в строковом представлении
     * @return Возвращает true - если аргумент допустим, false - если нет
     */
    public boolean isValidArgument(String argument) {
        return true;
    }

    /**
     * @return Возвращает полное название команды с ее описанием
     */
    @Override
    public String toString() {
        return commandNameFull + " : " + description;
    }
}