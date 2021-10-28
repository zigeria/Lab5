package commands;

/**
 * Класс команды выхода из программы
 */
public class Exit extends Command {
    /**
     * Конструктор команды exit
     */
    public Exit() {
        super("exit","", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String argument) {
        System.exit(0);
    }
}
