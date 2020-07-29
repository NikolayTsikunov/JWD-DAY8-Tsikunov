package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.creator.BookCreator;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RemoveBookCommand implements ExecutedCommand {
    private static final String BOOK_PARAMS = "bookParameters";
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        result.put("Status", false);
        if(parameters.containsKey(BOOK_PARAMS)) {
            BookCreator creator = new BookCreator();
            Optional<Book> removingBook = creator.create(parameters.get(BOOK_PARAMS));
            if(removingBook.isPresent()) {
                try {
                    if(service.removeBook(removingBook.get())) {
                        result.put("Status", true);
                        result.put("Message", "Book was removed");
                    } else {
                        result.put("Message", "Book wasn't removed");
                    }
                } catch (ServiceException e) {
                    result.put("Message", e.getMessage());
                }
            } else {
                result.put("Message", "Wrong book data");
            }
        } else {
            result.put("Message", "Empty book data");
        }
        return result;
    }
}