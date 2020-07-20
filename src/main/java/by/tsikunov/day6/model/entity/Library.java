package by.tsikunov.day6.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Library {
    private static Library instance;
    private static final int MAX_CAPACITY = 100;

    private final List<Book> books;

    private Library() {
        books = new ArrayList<>();
        books.add(new Book("The Lord Of The Rings", new ArrayList<>(Arrays.asList("J.R.R.Tolkien")),
                1954, 1178));
        books.add(new Book("Airport", new ArrayList<>(Arrays.asList("A. Heiley")),
                1968, 476));
        books.add(new Book("Three comrades", new ArrayList<>(Arrays.asList("E. M. Remarque")),
                1936, 496));
        books.add(new Book("Kindred", new ArrayList<>(Arrays.asList("A. Pekhov", "E. Bychkova", "N. Turchaninova")),
                2006, 320));
        books.add(new Book("Divine comedy", new ArrayList<>(Arrays.asList("Dante Alighieri")),
                1265, 300));
        books.add(new Book("The Hobbit", new ArrayList<>(Arrays.asList("J.R.R.Tolkien")),
                1937, 304));
    }

    public static Library getInstance() {
        if(instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean addBook(Book book) {
        boolean result = false;
        book = hasBook(book);
        if (!books.contains(book) && books.size() < MAX_CAPACITY) {
            result = books.add(book);
        }

        return result;
    }

    public boolean removeBook(Book book) {
        book = hasBook(book);
        return books.remove(book);
    }

    private Book hasBook(Book book) {
        for (Book searchingBook : books) {
            if(searchingBook.equalsWithoutId(book)) {
                book = searchingBook;
                break;
            }
        }
        return book;
    }

}
