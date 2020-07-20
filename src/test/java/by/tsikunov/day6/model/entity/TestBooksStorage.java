package by.tsikunov.day6.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBooksStorage {
    private static Library library;
    private static List<Book> baseLibrary;
    private static List<Book> sortedByIdLibrary;
    private static List<Book> sortedByTitleLibrary;
    private static List<Book> sortedByAuthorLibrary;
    private static List<Book> sortedByPublicationYearLibrary;
    private static List<Book> sortedByPagesLibrary;

    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;
    private static Book book5;
    private static Book book6;
    private static Book book7;
    private static Book book8;
    private static Book book9;
    private static Book book10;

    static {
        book1 = new Book("The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900);
        book2 = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840);
        book3 = new Book("1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450);
        book4 = new Book("12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1925, 760);
        book5 = new Book("Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470);
        book6 = new Book("Faust",
                new ArrayList<>(Arrays.asList("I.V.Goethe")), 1808, 600);
        book7 = new Book("Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350);
        book8 = new Book("Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430);
        book9 = new Book("Divine Comedy",
                new ArrayList<>(Arrays.asList("A.Dante")), 1265, 610);
        book10 = new Book("Process",
                new ArrayList<>(Arrays.asList("F.Kafka")), 1925, 700);
    }

    private TestBooksStorage() {
    }

    public static Library resetLibrary() {
        library = Library.getInstance();

        List<Book> books = new ArrayList<>(library.getBooks());
        cleanLibrary(books);
        fillLibrary();

        return library;
    }

    public static void cleanLibrary(List<Book> books) {
        if (!books.isEmpty()) {
            for (Book book : books) {
                library.removeBook(book);
            }
        }
    }

    public static void fillLibrary() {
        for (Book book : baseLibrary) {
            library.addBook(book);
        }
    }

    public static void initializeLibraries() {
        baseLibrary = new ArrayList<>();
        sortedByIdLibrary = new ArrayList<>();
        sortedByTitleLibrary = new ArrayList<>();
        sortedByAuthorLibrary = new ArrayList<>();
        sortedByPublicationYearLibrary = new ArrayList<>();
        sortedByPagesLibrary = new ArrayList<>();

        baseLibrary.add(book3);
        baseLibrary.add(book5);
        baseLibrary.add(book1);
        baseLibrary.add(book9);
        baseLibrary.add(book8);
        baseLibrary.add(book10);
        baseLibrary.add(book2);
        baseLibrary.add(book4);
        baseLibrary.add(book7);
        baseLibrary.add(book6);

        sortedByIdLibrary.add(book1);
        sortedByIdLibrary.add(book2);
        sortedByIdLibrary.add(book3);
        sortedByIdLibrary.add(book4);
        sortedByIdLibrary.add(book5);
        sortedByIdLibrary.add(book6);
        sortedByIdLibrary.add(book7);
        sortedByIdLibrary.add(book8);
        sortedByIdLibrary.add(book9);
        sortedByIdLibrary.add(book10);

        sortedByTitleLibrary.add(book4);
        sortedByTitleLibrary.add(book3);
        sortedByTitleLibrary.add(book5);
        sortedByTitleLibrary.add(book9);
        sortedByTitleLibrary.add(book6);
        sortedByTitleLibrary.add(book2);
        sortedByTitleLibrary.add(book8);
        sortedByTitleLibrary.add(book10);
        sortedByTitleLibrary.add(book7);
        sortedByTitleLibrary.add(book1);

        sortedByAuthorLibrary.add(book9);
        sortedByAuthorLibrary.add(book5);
        sortedByAuthorLibrary.add(book10);
        sortedByAuthorLibrary.add(book6);
        sortedByAuthorLibrary.add(book3);
        sortedByAuthorLibrary.add(book1);
        sortedByAuthorLibrary.add(book8);
        sortedByAuthorLibrary.add(book7);
        sortedByAuthorLibrary.add(book4);
        sortedByAuthorLibrary.add(book2);

        sortedByPublicationYearLibrary.add(book9);
        sortedByPublicationYearLibrary.add(book6);
        sortedByPublicationYearLibrary.add(book10);
        sortedByPublicationYearLibrary.add(book4);
        sortedByPublicationYearLibrary.add(book3);
        sortedByPublicationYearLibrary.add(book8);
        sortedByPublicationYearLibrary.add(book1);
        sortedByPublicationYearLibrary.add(book7);
        sortedByPublicationYearLibrary.add(book5);
        sortedByPublicationYearLibrary.add(book2);

        sortedByPagesLibrary.add(book7);
        sortedByPagesLibrary.add(book8);
        sortedByPagesLibrary.add(book3);
        sortedByPagesLibrary.add(book5);
        sortedByPagesLibrary.add(book6);
        sortedByPagesLibrary.add(book9);
        sortedByPagesLibrary.add(book10);
        sortedByPagesLibrary.add(book4);
        sortedByPagesLibrary.add(book2);
        sortedByPagesLibrary.add(book1);
    }

    public static List<Book> getBaseLibrary() {
        return baseLibrary;
    }

    public static List<Book> getSortedByIdLibrary() {
        return sortedByIdLibrary;
    }

    public static List<Book> getSortedByTitleLibrary() {
        return sortedByTitleLibrary;
    }

    public static List<Book> getSortedByAuthorLibrary() {
        return sortedByAuthorLibrary;
    }

    public static List<Book> getSortedByPublicationYearLibrary() {
        return sortedByPublicationYearLibrary;
    }

    public static List<Book> getSortedByPagesLibrary() {
        return sortedByPagesLibrary;
    }
}
