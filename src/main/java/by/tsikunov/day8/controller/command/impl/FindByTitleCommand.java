package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByTitleCommand implements ExecutedCommand {
    private static final String TITLE = "title";
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        if(parameters.containsKey(TITLE)) {
            try {
                List<Book> books = service.findByTitle(parameters.get(TITLE));
                result.put("Status", true);
                result.put("Library", books);
            } catch (ServiceException e) {
                result.put("Status", false);
                result.put("Message", e.getMessage());
            }
        } else {
            result.put("Status", false);
            result.put("Message", "Missing entry data");
        }
        return result;
    }
}