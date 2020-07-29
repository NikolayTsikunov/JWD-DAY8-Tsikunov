package by.tsikunov.day8.model.dao;

import by.tsikunov.day8.exception.DaoException;
import by.tsikunov.day8.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    boolean add(Book book) throws DaoException;

    boolean remove(Book book) throws DaoException;

    Optional<Book> findById(long bookId) throws DaoException;

    List<Book> findAll() throws DaoException;

    List<Book> findByTitle(String title) throws DaoException;

    List<Book> findByAuthor(String author) throws DaoException;

    List<Book> findByPublicationYear(int... yearPublication) throws DaoException;

    List<Book> findByPages(int... pages) throws DaoException;

    List<Book> sortByOption(String bookParameter) throws DaoException;
}
