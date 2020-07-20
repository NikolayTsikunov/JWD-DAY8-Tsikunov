package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class FindByIdCommandTest {
    ExecutedCommand command;

    @BeforeTest
    public void setUp() {
        command = new FindByIdCommand();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testExecutePositive() {
        String id = "5";
        int bookId = 5;
        Map<String, String> entryData = new HashMap<>();
        entryData.put("bookId", id);
        Map<String, String> actual = command.execute(entryData);
        List<Book> books = TestBooksStorage.getBaseLibrary();
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "The book has found");
        for (Book book : books) {
            if(book.getBookId() == bookId) {
                expected.put("Storage", Arrays.asList(book).toString());
                break;
            }
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteNegative() {
        Map<String, String> entryData = new HashMap<>();
        Map<String, String> actual = command.execute(entryData);
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "Empty entry data");
        assertEquals(actual, expected);
    }
}