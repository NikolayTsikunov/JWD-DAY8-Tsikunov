package by.tsikunov.day8.model.dao.impl;

import by.tsikunov.day8.exception.DaoException;
import by.tsikunov.day8.model.dao.BookListDao;
import by.tsikunov.day8.model.dao.BookParameter;
import by.tsikunov.day8.model.entity.Book;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookSqlDaoImplTest {

    @Test
    public void testFindAll() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.findAll();
        books.isEmpty();
    }

    @Test
    public void testFindById() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        Optional<Book> book = dao.findById(5);
        book.isPresent();
    }

    @Test
    public void testFindByYearPublication() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.findByPublicationYear(1925, 1950);
        books.isEmpty();
    }

    @Test
    public void testFindByPages() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.findByPages(600, 700);
        books.isEmpty();
    }

    @Test
    public void testFindByTitle() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.findByTitle("or");
        books.isEmpty();
    }

    @Test
    public void testFindByAuthor() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.findByAuthor("Struga");
        books.isEmpty();
    }

    @Test
    public void testAdd() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        boolean isAdded = dao.add(new Book(1, "Mir", Arrays.asList("Tolstoi"), 1999, 600));
        System.out.println(isAdded);
    }

    @Test
    public void testRemove() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        boolean isRemoved = dao.remove(new Book(1, "Mir", Arrays.asList("Tolstoi"), 1999, 600));
        System.out.println(isRemoved);
    }

    @Test
    public void testOrderBy() throws DaoException {
        BookListDao dao = new BookSqlDaoImpl();
        List<Book> books = dao.sortByOption(BookParameter.AUTHORS);
        books.isEmpty();
    }
}