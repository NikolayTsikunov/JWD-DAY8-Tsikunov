package by.tsikunov.day6.model.dao;

import by.tsikunov.day6.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    boolean add(Book book);

    boolean remove(Book book);

    Optional<Book> findById(long bookId);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPublicationYear(int yearPublication);

    List<Book> findByPages(int pages);

    List<Book> sortById();

    List<Book> sortByTitle();

    List<Book> sortByAuthor();

    List<Book> sortByPublicationYear();

    List<Book> sortByPages();
}
