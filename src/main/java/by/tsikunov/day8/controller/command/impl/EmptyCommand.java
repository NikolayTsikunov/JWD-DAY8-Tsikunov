package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;

import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements ExecutedCommand {
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        Map<String, Object> result = new HashMap<>();
        result.put("Status", true);
        result.put("Message", "Wrong command");
        return result;
    }
}