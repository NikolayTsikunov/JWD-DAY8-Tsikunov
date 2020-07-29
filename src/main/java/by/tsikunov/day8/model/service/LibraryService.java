package by.tsikunov.day8.model.service;

import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    boolean addBook(Book book) throws ServiceException;

    boolean removeBook(Book book) throws ServiceException;

    Optional<Book> findById(long bookId) throws ServiceException;

    List<Book> findByTitle(String title) throws ServiceException;

    List<Book> findByAuthor(String author) throws ServiceException;

    List<Book> findByPublicationYear(int... yearPublication) throws ServiceException;

    List<Book> findByPages(int... pages) throws ServiceException;

    List<Book> findAll() throws ServiceException;

    List<Book> sortByOption(String bookOption) throws ServiceException;
}
