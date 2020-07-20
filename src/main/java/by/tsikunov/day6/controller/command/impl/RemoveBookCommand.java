package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.creator.BookCreator;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.service.LibraryService;
import by.tsikunov.day6.model.service.impl.LibraryServiceImpl;
import by.tsikunov.day6.model.service.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RemoveBookCommand implements ExecutedCommand {
    private static final String BOOK_PARAMS = "bookParameters";
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        BookCreator creator = new BookCreator();
        Map<String, String> result = new HashMap<>();
        if(parameters.containsKey(BOOK_PARAMS)) {
            Optional<Book> removingBook = creator.createBook(parameters.get(BOOK_PARAMS));
            if(removingBook.isPresent()) {
                Response response = service.removeBook(removingBook.get());
                result.put("Message", response.getMessage());
                result.put("Storage", response.getResultBooks().toString());
            } else {
                result.put("Message", "Wrong book data");
            }
        } else {
            result.put("Message", "Empty book data");
        }
        return result;
    }
}