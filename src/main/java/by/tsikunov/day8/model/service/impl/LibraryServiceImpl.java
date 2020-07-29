package by.tsikunov.day8.model.service.impl;

import by.tsikunov.day8.exception.DaoException;
import by.tsikunov.day8.exception.ServiceException;
import by.tsikunov.day8.model.dao.BookListDao;
import by.tsikunov.day8.model.dao.impl.BookSqlDaoImpl;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.service.LibraryService;

import java.util.*;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public boolean addBook(Book book) throws ServiceException {
        boolean isAdded;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            isAdded = bookListDao.add(book);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding book", e);
        }
        return isAdded;
    }

    @Override
    public boolean removeBook(Book book) throws ServiceException {
        boolean isAdded;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            isAdded = bookListDao.remove(book);
        } catch (DaoException e) {
            throw new ServiceException("Error while removing books book", e);
        }
        return isAdded;
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
             books = bookListDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while searching all books", e);
        }
        return books;
    }

    @Override
    public Optional<Book> findById(long bookId) throws ServiceException {
        Optional<Book> book;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            book = bookListDao.findById(bookId);
        } catch (DaoException e) {
            throw new ServiceException("Error while searching book by id", e);
        }
        return book;
    }

    @Override
    public List<Book> findByTitle(String title) throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            books = bookListDao.findByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("Error while searching books by title", e);
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            books = bookListDao.findByAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException("Error while searching books by author", e);
        }
        return books;
    }

    @Override
    public List<Book> findByPublicationYear(int... publicationYear) throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            books = bookListDao.findByPublicationYear(publicationYear);
        } catch (DaoException e) {
            throw new ServiceException("Error while searching books by publication year", e);
        }
        return books;
    }

    @Override
    public List<Book> findByPages(int... pages) throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            books = bookListDao.findByPages(pages);
        } catch (DaoException e) {
            throw new ServiceException("Error while searching books by pages", e);
        }
        return books;
    }

    @Override
    public List<Book> sortByOption(String bookParameter) throws ServiceException {
        List<Book> books;
        try{
            BookListDao bookListDao = new BookSqlDaoImpl();
            books = bookListDao.sortByOption(bookParameter);
        } catch (DaoException e) {
            throw new ServiceException("Error sorting books by option = " + bookParameter, e);
        }
        return books;
    }
}
