package by.tsikunov.day6.model.dao.impl;

import by.tsikunov.day6.model.dao.BookListDao;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.Library;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    BookListDao bookListDao;

    @BeforeTest
    public void setUp() {
        bookListDao = new BookListDaoImpl();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testAddPositive() {
        Book book = new Book("Three comrades", new ArrayList<>(Arrays.asList("E. M. Remarque")),
                1936, 496);
        boolean actual = bookListDao.add(book);
        assertTrue(actual);
    }

    @Test
    public void testAddNegative() {
        Book book = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840);
        boolean actual = bookListDao.add(book);
        assertFalse(actual);
    }

    @Test
    public void testRemovePositive() {
        Book book = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840);
        boolean actual = bookListDao.remove(book);
        assertTrue(actual);
    }

    @Test
    public void testRemoveNegative() {
        Book book = new Book("Three comrades", new ArrayList<>(Arrays.asList("E. M. Remarque")),
                1936, 496);
        boolean actual = bookListDao.remove(book);
        assertFalse(actual);
    }

    @Test
    public void testFindByIdPositive() {
        long id = 5;
        Optional<Book> actual = bookListDao.findById(id);
        List<Book> books = TestBooksStorage.getBaseLibrary();
        Optional<Book> expected = Optional.empty();
        for (Book book : books) {
            if(book.getBookId() == id) {
                expected = Optional.of(book);
                break;
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdNegative() {
        Optional<Book> actual = bookListDao.findById(101);
        Optional<Book> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindAll() {
        List<Book> actual = bookListDao.findAll();
        List<Book> expected = TestBooksStorage.getBaseLibrary();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTitle() {
        String title = "or";
        List<Book> actual = bookListDao.findByTitle(title);
        List<Book> expected = new ArrayList<>();
        List<Book> baseLibrary = TestBooksStorage.getBaseLibrary();
        for (Book book : baseLibrary) {
            if(book.getTitle().contains(title)) {
                expected.add(book);
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthor() {
        String searchingAuthor = "Strugatsky";
        List<Book> actual = bookListDao.findByAuthor(searchingAuthor);
        List<Book> expected = new ArrayList<>();
        List<Book> baseLibrary = TestBooksStorage.getBaseLibrary();
        for (Book book : baseLibrary) {
            List<String> authors = book.getAuthors();
            for (String author : authors) {
                if(author.contains(searchingAuthor)) {
                    expected.add(book);
                    break;
                }
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublicationYear() {
        int year = 1968;
        List<Book> actual = bookListDao.findByPublicationYear(year);
        List<Book> expected = new ArrayList<>();
        List<Book> baseLibrary = TestBooksStorage.getBaseLibrary();
        for (Book book : baseLibrary) {
            if(book.getPublicationYear() == year) {
                expected.add(book);
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPages() {
        int pages = 610;
        List<Book> actual = bookListDao.findByPages(pages);
        List<Book> expected = new ArrayList<>();
        List<Book> baseLibrary = TestBooksStorage.getBaseLibrary();
        for (Book book : baseLibrary) {
            if(book.getPages() == pages) {
                expected.add(book);
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testSortById() {
        List<Book> actual = bookListDao.sortById();
        List<Book> expected = TestBooksStorage.getSortedByIdLibrary();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByTitle() {
        List<Book> actual = bookListDao.sortByTitle();
        List<Book> expected = TestBooksStorage.getSortedByTitleLibrary();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByAuthor() {
        List<Book> actual = bookListDao.sortByAuthor();
        List<Book> expected = TestBooksStorage.getSortedByAuthorLibrary();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPublicationYear() {
        List<Book> actual = bookListDao.sortByPublicationYear();
        List<Book> expected = TestBooksStorage.getSortedByPublicationYearLibrary();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPages() {
        List<Book> actual = bookListDao.sortByPages();
        List<Book> expected = TestBooksStorage.getSortedByPagesLibrary();
        assertEquals(actual, expected);
    }
}