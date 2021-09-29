package copy.Proga;
//вывести последние 9 команд (без их аргументов)
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

    public void add_history(String command) {
        history_array[head] = command;
        head = next(head);
    }

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
