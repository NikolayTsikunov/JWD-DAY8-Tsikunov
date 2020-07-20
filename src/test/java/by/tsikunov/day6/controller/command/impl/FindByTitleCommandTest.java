package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindByTitleCommandTest {
    ExecutedCommand command;

    @BeforeTest
    public void setUp() {
        command = new FindByTitleCommand();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testExecutePositive() {
        String title = "or";
        Map<String, String> entryData = new HashMap<>();
        entryData.put("title", title);
        Map<String, String> actual = command.execute(entryData);
        Map<String, String> expected = new HashMap<>();
        List<Book> books = TestBooksStorage.getBaseLibrary();
        List<Book> expectedBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.getTitle().contains(title)) {
                expectedBooks.add(book);
            }
        }
        expected.put("Message", "The books have found");
        expected.put("Storage", expectedBooks.toString());
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