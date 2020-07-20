package by.tsikunov.day6.controller.command.impl;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.model.entity.TestBooksStorage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class FindAllCommandTest {
    ExecutedCommand command;

    @BeforeTest
    public void setUp() {
        command = new FindAllCommand();
    }

    @BeforeMethod
    public void prepareLibrary() {
        TestBooksStorage.initializeLibraries();
        TestBooksStorage.resetLibrary();
    }

    @Test
    public void testExecute() {
        Map<String, String> actual = command.execute(null);
        Map<String, String> expected = new HashMap<>();
        expected.put("Message", "All books");
        expected.put("Storage", TestBooksStorage.getBaseLibrary().toString());
        assertEquals(actual, expected);
    }
}