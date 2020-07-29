package by.tsikunov.day8.controller.command;

import java.util.Map;

public interface ExecutedCommand {
    Map<String, Object> execute(Map<String, String> parameters);
}
