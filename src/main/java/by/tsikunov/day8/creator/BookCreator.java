package by.tsikunov.day8.creator;

import by.tsikunov.day8.model.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookCreator {
    private static final String PARAMETERS_DELIMITER = ";";
    private static final String AUTHORS_DELIMITER = ",";

    private static final int PARAMETERS_COUNT = 4;
    private static final long TEMP_BOOK_ID = -1;
    private static final int TITLE_POSITION = 0;
    private static final int AUTHOR_POSITION = 1;
    private static final int YEAR_POSITION = 2;
    private static final int PAGES_POSITION = 3;

    public Optional<Book> create(String bookData) {
        Optional<Book> resultedBook = Optional.empty();
        String[] bookElements = bookData.split(PARAMETERS_DELIMITER);
        if(bookElements.length == PARAMETERS_COUNT) {
            String title = bookElements[TITLE_POSITION];
            List<String> authors = getAuthors(bookElements[AUTHOR_POSITION]);
            int year = Integer.parseInt(bookElements[YEAR_POSITION]);
            int pages = Integer.parseInt(bookElements[PAGES_POSITION]);
            Book book = new Book(TEMP_BOOK_ID, title, authors, year, pages);
            resultedBook = Optional.of(book);
        }
        return resultedBook;
    }

    private List<String> getAuthors(String data) {
        String[] authors = data.split(AUTHORS_DELIMITER);
        List<String> resultedAuthors = new ArrayList<>();
        for (String author : authors) {
            resultedAuthors.add(author.trim());
        }
        return resultedAuthors;
    }
}
