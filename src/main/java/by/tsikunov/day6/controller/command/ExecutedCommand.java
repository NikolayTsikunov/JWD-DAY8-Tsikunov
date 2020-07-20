package by.tsikunov.day6.controller.command;

import java.util.Map;

public interface ExecutedCommand {
    Map<String, String> execute(Map<String, String> parameters);
}
