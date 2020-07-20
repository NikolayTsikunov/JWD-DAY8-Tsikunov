package by.tsikunov.day6.creator;

import by.tsikunov.day6.model.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookCreatorTest {

    @Test
    public void testCreateBookPositive() {
        BookCreator creator = new BookCreator();
        Optional<Book> actual = creator.createBook("The Lord Of The Rings; " +
                "J.R.R.Tolkien; 1956; 900");
        Book expected = new Book("The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900);
        expected.setBookId(actual.get().getBookId());
        assertEquals(actual.get(), expected);
    }

    @Test
    public void testCreateBookNegative() {
        BookCreator creator = new BookCreator();
        Optional<Book> actual = creator.createBook("J.R.R.Tolkien; 1956; 900");
        assertTrue(actual.isEmpty());
    }
}