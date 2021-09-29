package Proga;

/**
 * История вызова последних 9 команд без аргументов
 */
public class History {
    private String[] history_array = new String[9];
    private int head;

    public History() {
        for (int i = 0; i < 9; i++)
            history_array[i] = null;
        head = 0;
    }

    public int next(int i) {
        return (i+1) % 9;
    }

    /**
     * Добавляет команду в историю
     * @param command
     */
    public void add_history(String command) {
        history_array[head] = command;
        head = next(head);
    }

    /**
     * Выводит в консоль историю вызова последних 9 команд (без аргументов)
     */
    public void get_history() {
        int i = head;
        do{
            if (history_array[i] != null){
                System.out.println(history_array[i]);
            }
            i = next(i);
        }
        while (i != head);
    }
}
