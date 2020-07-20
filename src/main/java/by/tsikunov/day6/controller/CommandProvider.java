package by.tsikunov.day6.controller;

import by.tsikunov.day6.controller.command.CommandType;
import by.tsikunov.day6.controller.command.ExecutedCommand;

public class CommandProvider {

    public ExecutedCommand provideCommand(String command) {
        ExecutedCommand executedCommand;
        if(command != null && !command.isBlank()) {
            try{
                executedCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                executedCommand = CommandType.EMPTY_COMMAND.getCommand();
            }
        } else {
            executedCommand = CommandType.EMPTY_COMMAND.getCommand();
        }
        return executedCommand;
    }

}
