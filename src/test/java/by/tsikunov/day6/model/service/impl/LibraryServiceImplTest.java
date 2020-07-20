package by.tsikunov.day6.model.service.impl;

import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import by.tsikunov.day6.model.service.LibraryService;
import by.tsikunov.day6.model.service.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class LibraryServiceImplTest {
    LibraryService libraryService;

    @BeforeTest
    public void setUp() {
        libraryService = new LibraryServiceImpl();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testAddBookPositive() {
        Book book = new Book("Three comrades",
                new ArrayList<>(Arrays.asList("E. M. Remarque")), 1936, 496);
        Response actual = libraryService.addBook(book);
        Response expected = new Response();
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        expectedList.add(book);
        expected.setMessage("Book successfully added");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testAddBookNegative() {
        Book book = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840);
        Response actual = libraryService.addBook(book);
        Response expected = new Response();
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        expected.setMessage("Can't add book");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveBookPositive() {
        Book book = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840);
        Response actual = libraryService.removeBook(book);
        Response expected = new Response();
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        for (Book searchingBook : expectedList) {
            if(searchingBook.equalsWithoutId(book)) {
                book = searchingBook;
            }
        }
        expectedList.remove(book);
        expected.setMessage("Book successfully removed");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveBookNegative() {
        Book book = new Book("Three comrades",
                new ArrayList<>(Arrays.asList("E. M. Remarque")), 1936, 496);
        Response actual = libraryService.removeBook(book);
        Response expected = new Response();
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        for (Book searchingBook : expectedList) {
            if(searchingBook.equalsWithoutId(book)) {
                book = searchingBook;
            }
        }
        expectedList.remove(book);
        expected.setMessage("Can't remove book");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindAll() {
        Response actual = libraryService.findAll();
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        Response expected = new Response();
        expected.setMessage("All books");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdPositive() {
        long id = 4;
        Response actual = libraryService.findById(id);
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        Response expected = new Response();
        expected.setMessage("The book has found");
        for (Book book : expectedList) {
            if(book.getBookId() == id) {
                expected.setResultBooks(Arrays.asList(book));
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdNegative() {
        long id = 109;
        Response actual = libraryService.findById(id);
        Response expected = new Response();
        expected.setMessage(String.format("Can't find book by id {%d}", id));
        expected.setResultBooks(new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTitlePositive() {
        String title = "or";
        Response actual = libraryService.findByTitle(title);
        List<Book> books = TestBooksStorage.getBaseLibrary();
        List<Book> expectedList = new ArrayList<>();
        Response expected = new Response();
        expected.setMessage("The books have found");
        for(Book book : books) {
            if(book.getTitle().contains(title)) {
                expectedList.add(book);
            }
        }
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTitleNegative() {
        String title = "Mama";
        Response actual = libraryService.findByTitle(title);
        Response expected = new Response();
        expected.setMessage(String.format("Can't find books with this title {%s}", title));
        expected.setResultBooks(new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthorPositive() {
        String author = "Strugatsky";
        Response actual = libraryService.findByAuthor(author);
        List<Book> books = TestBooksStorage.getBaseLibrary();
        List<Book> expectedList = new ArrayList<>();
        Response expected = new Response();
        expected.setMessage("The books have found");
        for (Book book : books) {
            List<String> authors = book.getAuthors();
            for (String searchedAuthor : authors) {
                if(searchedAuthor.contains(author)) {
                    expectedList.add(book);
                    break;
                }
            }
        }
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthorNegative() {
        String author = "Mama";
        Response actual = libraryService.findByAuthor(author);
        Response expected = new Response();
        expected.setMessage(String.format("Can't find books with this author {%s}", author));
        expected.setResultBooks(new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublicationYearPositive() {
        int year = 1956;
        Response actual = libraryService.findByPublicationYear(year);
        Response expected = new Response();
        List<Book> books = TestBooksStorage.getBaseLibrary();
        List<Book> expectedList = new ArrayList<>();
        for (Book book : books) {
            if(book.getPublicationYear() == year) {
                expectedList.add(book);
            }
        }
        expected.setMessage("The books have found");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublicationYearNegative() {
        int year = 1500;
        Response actual = libraryService.findByPublicationYear(year);
        Response expected = new Response();
        expected.setMessage(String.format("Can't find books with this publication year {%d}", year));
        expected.setResultBooks(new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesPositive() {
        int pages = 900;
        Response actual = libraryService.findByPages(pages);
        Response expected = new Response();
        List<Book> books = TestBooksStorage.getBaseLibrary();
        List<Book> expectedList = new ArrayList<>();
        for (Book book : books) {
            if(book.getPages() == pages) {
                expectedList.add(book);
            }
        }
        expected.setMessage("The books have found");
        expected.setResultBooks(expectedList);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesNegative() {
        int pages = 100;
        Response actual = libraryService.findByPages(pages);
        Response expected = new Response();
        expected.setMessage(String.format("Can't find books with this count of pages {%d}", pages));
        expected.setResultBooks(new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testSortById() {
        Response actual = libraryService.sortById();
        Response expected = new Response();
        expected.setMessage("Sorted list by id");
        expected.setResultBooks(TestBooksStorage.getSortedByIdLibrary());
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByTitle() {
        Response actual = libraryService.sortByTitle();
        Response expected = new Response();
        expected.setMessage("Sorted list by title");
        expected.setResultBooks(TestBooksStorage.getSortedByTitleLibrary());
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByAuthor() {
        Response actual = libraryService.sortByAuthor();
        Response expected = new Response();
        expected.setMessage("Sorted list by author");
        expected.setResultBooks(TestBooksStorage.getSortedByAuthorLibrary());
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPublicationYear() {
        Response actual = libraryService.sortByPublicationYear();
        Response expected = new Response();
        expected.setMessage("Sorted list by publication year");
        expected.setResultBooks(TestBooksStorage.getSortedByPublicationYearLibrary());
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPages() {
        Response actual = libraryService.sortByPages();
        Response expected = new Response();
        expected.setMessage("Sorted list by count of pages");
        expected.setResultBooks(TestBooksStorage.getSortedByPagesLibrary());
        assertEquals(actual, expected);
    }
}