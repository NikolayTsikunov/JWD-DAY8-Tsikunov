package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;

import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements ExecutedCommand {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        Map<String, String> result = new HashMap<>();
        result.put("Message", "Wrong command");
        return result;
    }
}