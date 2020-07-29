package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.*;

public class FindByPagesCommand implements ExecutedCommand {
    private static final String PAGES = "pages";
    private static final String PAGES_DELIMITER = ",";
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        if(parameters.containsKey(PAGES)) {
            try {
                int[] pages = getPages(parameters.get(PAGES));
                List<Book> books = service.findByPages(pages);
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

    private int[] getPages(String data) {
        String[] entryPages = data.split(PAGES_DELIMITER);
        int[] pages = new int[entryPages.length];
        for (int i = 0; i < entryPages.length; i++) {
            pages[i] = Integer.parseInt(entryPages[i]);
        }
        return pages;
    }
}