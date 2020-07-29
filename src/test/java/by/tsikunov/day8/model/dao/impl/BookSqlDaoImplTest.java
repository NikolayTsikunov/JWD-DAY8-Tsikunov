package by.tsikunov.day8.model.dao.impl;

import by.tsikunov.day8.exception.ConnectionException;
import by.tsikunov.day8.exception.DaoException;
import by.tsikunov.day8.model.TestBookStorage;
import by.tsikunov.day8.model.dao.BookListDao;
import by.tsikunov.day8.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookSqlDaoImplTest {
    BookListDao dao;

    @BeforeMethod
    public void prepareData() {
        try{
            TestBookStorage.resetBookStorage();
            dao = new BookSqlDaoImpl();
        } catch (SQLException | ConnectionException | DaoException e) {
            fail("Error with connection to test database");
        }
    }

    @Test
    public void testAdd() throws DaoException {
        Book book = new Book(-1, "The Prince",
                new ArrayList<>(Arrays.asList("N.Machiavelli")), 1532, 643);
        assertTrue(dao.add(book));
    }

    @Test
    public void testRemove() throws DaoException {
        Book book = new Book(-1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900);
        assertTrue(dao.remove(book));
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900));
        expected.add(new Book(2,"Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840));
        expected.add(new Book(3,"1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450));
        expected.add(new Book(4, "12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1925, 760));
        expected.add(new Book(5, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470));
        expected.add(new Book(6, "Faust",
                new ArrayList<>(Arrays.asList("I.V.Goethe")), 1808, 600));
        expected.add(new Book(7, "Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350));
        expected.add(new Book(8, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        expected.add(new Book(9, "Divine Comedy",
                new ArrayList<>(Arrays.asList("A.Dante")), 1265, 610));
        expected.add(new Book(10, "Process",
                new ArrayList<>(Arrays.asList("F.Kafka")), 1925, 700));
        List<Book> actual = dao.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindById() throws DaoException {
        Optional<Book> actual = dao.findById(5);
        Book expected = new Book(5, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470);
        assertEquals(actual.get(), expected);
    }

    @Test
    public void testFindByTitle() throws DaoException {
        List<Book> actual = dao.findByTitle("or");
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900));
        expected.add(new Book(5, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthor() throws DaoException {
        List<Book> actual = dao.findByAuthor("Struga");
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(7, "Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350));
        expected.add(new Book(8, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublicationYear() throws DaoException {
        List<Book> actual = dao.findByPublicationYear(1945, 1960);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900));
        expected.add(new Book(3,"1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450));
        expected.add(new Book(7, "Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350));
        expected.add(new Book(8, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPages() throws DaoException {
        List<Book> actual = dao.findByPages(400, 500);
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(3,"1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450));
        expected.add(new Book(5, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470));
        expected.add(new Book(8, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByOption() throws DaoException {
        List<Book> actual = dao.sortByOption("publicationYear");
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(9, "Divine Comedy",
                new ArrayList<>(Arrays.asList("A.Dante")), 1265, 610));
        expected.add(new Book(6, "Faust",
                new ArrayList<>(Arrays.asList("I.V.Goethe")), 1808, 600));
        expected.add(new Book(4, "12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1925, 760));
        expected.add(new Book(10, "Process",
                new ArrayList<>(Arrays.asList("F.Kafka")), 1925, 700));
        expected.add(new Book(3,"1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450));
        expected.add(new Book(8, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        expected.add(new Book(1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900));
        expected.add(new Book(7, "Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350));
        expected.add(new Book(5, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470));
        expected.add(new Book(2,"Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840));
        assertEquals(actual, expected);
    }
}