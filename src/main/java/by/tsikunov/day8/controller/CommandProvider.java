package by.tsikunov.day8.controller;

import by.tsikunov.day8.controller.command.CommandType;
import by.tsikunov.day8.controller.command.ExecutedCommand;

public class CommandProvider {

    public ExecutedCommand provideCommand(String command) {
        ExecutedCommand executedCommand;
        if(command != null) {
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
