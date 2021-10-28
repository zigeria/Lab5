package commands;


import main.CommandOrganizer;
import main.FileWork;
import main.MovieHashSet;
import main.Request;
import programException.FileCanNotReadException;
import programException.NotFileException;
import programException.NotFoundFileException;
import programException.RecursionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Класс команды исполнения скрипта
 */
public class ExecuteScript extends Command {
    MovieHashSet collection;
    Request request;
    CommandOrganizer commandOrganizer;

    /**
     * Конструктор команды execute_script
     * @param collection - изменяемая коллекция
     * @param request - объект класса запросов
     * @param commandOrganizer - объект класса организатора команд
     */
    public ExecuteScript(MovieHashSet collection, Request request, CommandOrganizer commandOrganizer){
        super("execute_script"," file_name","считать и исполнить скрипт из указанного файла");
        this.collection = collection;
        this.request = request;
        this.commandOrganizer = commandOrganizer;
    }

    
    @Override
    public void execute(String argument) throws NotFoundFileException, FileCanNotReadException, RecursionException, NotFileException {
        File file = new File(argument);

        if (!file.exists()){
            if(FileWork.isFromScript()){
                return;
            }
            else {
                throw new NotFoundFileException();
            }
        }

        if (!file.canRead()){
            if(FileWork.isFromScript()){
                return;
            }
            else {
                throw new FileCanNotReadException();
            }
        }
        if(!file.isFile()){
            if(FileWork.isFromScript()){
                return;
            }
            else{
                throw new NotFileException();
            }
        }
        System.out.println(file.getAbsolutePath());
        System.out.println(CommandOrganizer.fileNameHistory.contains(file.getAbsolutePath()));


        if(FileWork.isFromScript() && CommandOrganizer.fileNameHistory.contains(file.getAbsolutePath())){
            throw new RecursionException();
        }

        CommandOrganizer.fileNameHistory.push(file.getAbsolutePath());
        FileWork.readScript(collection,file, request, commandOrganizer);

    }

    @Override
    public boolean isValidArgument(String argument) {
        try {
            new FileInputStream(argument);
            return true;
        }
        catch (FileNotFoundException exception){
            return false;
        }

    }
}
