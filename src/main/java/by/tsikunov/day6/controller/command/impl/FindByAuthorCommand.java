package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.model.service.LibraryService;
import by.tsikunov.day6.model.service.impl.LibraryServiceImpl;
import by.tsikunov.day6.model.service.response.Response;

import java.util.HashMap;
import java.util.Map;

public class FindByAuthorCommand implements ExecutedCommand {
    private static final String AUTHOR = "author";
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, String> result = new HashMap<>();
        if(parameters.containsKey(AUTHOR)) {
            Response response = service.findByAuthor(parameters.get(AUTHOR));
            result.put("Message", response.getMessage());
            result.put("Storage", response.getResultBooks().toString());
        } else {
            result.put("Message", "Empty entry data");
        }
        return result;
    }
}