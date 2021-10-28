package commands;


import main.CommandOrganizer;

/**
 * Класс команды, выводящей справку по доступным командам
 */
public class Help extends Command {
    private CommandOrganizer commandOrganizer;

    /**
     * Конструктор команды help
     * @param commandOrganizer - объект класса организатора команд
     */
    public Help(CommandOrganizer commandOrganizer) {
        super("help","", "вывести справку по доступным командам");
        this.commandOrganizer = commandOrganizer;
    }

    @Override
    public void execute(String argument) {
        for (Command command : commandOrganizer.getCommands()) {
            System.out.format("%30s : %s \n",command.getCommandNameFull(), command.getDescription());
        }
    }
}