package by.tsikunov.day6.controller;

import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class ControllerTest {
    Controller controller;

    @BeforeTest
    public void setUp() {
        controller = Controller.getInstance();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testExecuteBookCommandSort() {
        List<Book> expectedList = TestBooksStorage.getSortedByPublicationYearLibrary();
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "Sorted list by publication year");
        expected.put("Storage", expectedList.toString());
        Map<String, String> actual = controller.executeBookCommand("SORT_YEAR_PUBLICATION", null);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteBookCommandFind() {
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "All books");
        expected.put("Storage", expectedList.toString());
        Map<String, String> actual = controller.executeBookCommand("FIND_ALL", null);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteBookCommandAdd() {
        String bookData = "Three comrades; E. M. Remarque; 1936; 496";
        Map<String, String> entryData = new HashMap<>();
        entryData.put("bookParameters", bookData);
        Map<String, String> actual = controller.executeBookCommand("ADD_BOOK", entryData);
        List<Book> expectedList = TestBooksStorage.getBaseLibrary();
        Book book = new Book("Three comrades",
                new ArrayList<>(Arrays.asList("E. M. Remarque")), 1936, 496);
        book.setBookId(17);
        expectedList.add(book);
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "Book successfully added");
        expected.put("Storage", expectedList.toString());
        assertEquals(actual, expected);
    }

}