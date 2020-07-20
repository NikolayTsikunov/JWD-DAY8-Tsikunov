package by.tsikunov.day6.controller;

import by.tsikunov.day6.controller.command.ExecutedCommand;
import by.tsikunov.day6.controller.command.impl.AddBookCommand;
import by.tsikunov.day6.controller.command.impl.EmptyCommand;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {
    CommandProvider commandProvider;

    @BeforeTest
    public void setUp() {
        commandProvider = new CommandProvider();
    }

    @Test
    public void testProvideCommandPositive() {
        ExecutedCommand command = commandProvider.provideCommand("ADD_BOOK");
        assertTrue(command instanceof AddBookCommand);
    }

    @Test
    public void testProvideCommandUnknown() {
        ExecutedCommand command = commandProvider.provideCommand("SORT_BY_TYPE");
        assertTrue(command instanceof EmptyCommand);
    }

    @Test
    public void testProvideCommandEmpty() {
        ExecutedCommand command = commandProvider.provideCommand(null);
        assertTrue(command instanceof EmptyCommand);
    }
}