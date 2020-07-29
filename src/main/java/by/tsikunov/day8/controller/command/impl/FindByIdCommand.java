package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FindByIdCommand implements ExecutedCommand {
    private static final String BOOK_ID = "bookId";
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        if(parameters.containsKey(BOOK_ID)) {
            try {
                long id = Long.parseLong(parameters.get(BOOK_ID));
                Optional<Book> book = service.findById(id);
                if(book.isPresent()) {
                    result.put("Status", true);
                    result.put("Book", book.get());
                } else {
                    result.put("Status", true);
                    result.put("Message", "Book wasn't found");
                }
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