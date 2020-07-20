package by.tsikunov.day6.controller;

import by.tsikunov.day6.controller.command.ExecutedCommand;

import java.util.Map;

public class Controller {
    private static Controller instance;

    private Controller() {}

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Map<String, String> executeBookCommand(String command, Map<String, String> entryData) {
        CommandProvider commandProvider = new CommandProvider();
        ExecutedCommand executedCommand = commandProvider.provideCommand(command);
        return executedCommand.execute(entryData);
    }
}
