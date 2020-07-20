package by.tsikunov.day6.creator;

import by.tsikunov.day6.model.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookCreator {
    private static final String PARAMETER_DELIMITER = ";";
    private static final String AUTHOR_DELIMITER = ",";
    private static final int TITLE_POSITION = 0;
    private static final int AUTHORS_POSITION = 1;
    private static final int PUBLICATION_YEAR_POSITION = 2;
    private static final int PAGES_POSITION = 3;


    public Optional<Book> createBook(String bookData) {
        Optional<Book> resultBook = Optional.empty();
        String[] bookElement = bookData.split(PARAMETER_DELIMITER);
        if(bookElement.length == 4) {
            String title = bookElement[TITLE_POSITION].trim();
            List<String> authors = createListAuthors(bookElement[AUTHORS_POSITION].trim());
            int publicationYear = Integer.parseInt(bookElement[PUBLICATION_YEAR_POSITION].trim());
            int pages = Integer.parseInt(bookElement[PAGES_POSITION].trim());
            resultBook = Optional.of(new Book(title, authors, publicationYear, pages));
        }

        return resultBook;
    }

    private List<String> createListAuthors(String authors) {
        String[] elementsList = authors.split(AUTHOR_DELIMITER);
        List<String> resultList = new ArrayList<>();
        for(String author : elementsList){
             resultList.add(author.trim());
        }
        return resultList;
    }
}
