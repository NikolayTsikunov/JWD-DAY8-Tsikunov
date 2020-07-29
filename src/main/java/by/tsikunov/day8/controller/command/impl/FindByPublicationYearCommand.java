package by.tsikunov.day8.controller.command.impl;

import by.tsikunov.day8.controller.command.ExecutedCommand;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;
import by.tsikunov.day8.model.service.impl.LibraryServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByPublicationYearCommand implements ExecutedCommand {
    private static final String PUBLICATION_YEAR = "publicationYear";
    private static final String YEAR_DELIMITER = ",";
    @Override
    public Map<String, Object> execute(Map<String, String> parameters) {
        LibraryService service = new LibraryServiceImpl();
        Map<String, Object> result = new HashMap<>();
        if(parameters.containsKey(YEAR_DELIMITER)) {
            try {
                int[] years = getYears(parameters.get(PUBLICATION_YEAR));
                List<Book> books = service.findByPublicationYear(years);
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

    private int[] getYears(String data) {
        String[] entryPages = data.split(YEAR_DELIMITER);
        int[] pages = new int[entryPages.length];
        for (int i = 0; i < entryPages.length; i++) {
            pages[i] = Integer.parseInt(entryPages[i]);
        }
        return pages;
    }
}