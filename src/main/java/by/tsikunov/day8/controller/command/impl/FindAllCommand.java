package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllCommand implements ExecutedCommand {
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        try {
            List<Book> books = service.findAll();
            result.put("Status", true);
            result.put("Library", books);
        } catch (ServiceException e) {
            result.put("Status", false);
            result.put("Message", e.getMessage());
        }
        return result;
    }
}
