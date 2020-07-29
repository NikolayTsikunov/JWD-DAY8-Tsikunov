package by.tsikunov.day8.model;

import by.tsikunov.day8.exception.ConnectionException;
import by.tsikunov.day8.model.connection.WrapperConnection;
import by.tsikunov.day8.model.entity.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBookStorage {
    private static final String SQL_DELETE_BOOKS = "DELETE FROM book";
    private static final String SQL_RESET_PRIMARY_KEY = "ALTER TABLE book AUTO_INCREMENT = 1";
    private static final String SQL_ADD_BOOKS = "INSERT INTO book(title, author, publicationYear, pages)" +
            "VALUES (?, ?, ?, ?)";
    private static List<Book> bookList;

    private TestBookStorage() {
    }

    public static void resetBookStorage() throws SQLException, ConnectionException {
        fillList();
        WrapperConnection wrapperConnection = new WrapperConnection();
        Connection connection = wrapperConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOKS);
        statement.executeUpdate();
        statement.execute(SQL_RESET_PRIMARY_KEY);
        statement = connection.prepareStatement(SQL_ADD_BOOKS);
        for (Book book : bookList) {
            statement.setString(1, book.getTitle());
            String author = String.join(",", book.getAuthors());
            statement.setString(2, author);
            statement.setInt(3, book.getPublicationYear());
            statement.setInt(4, book.getPages());
            statement.executeUpdate();
        }
        statement.close();
        connection.close();
    }

    private static void fillList() {
        bookList = new ArrayList<>();

        bookList.add(new Book(-1, "The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, 900));
        bookList.add(new Book(-1,"Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, 840));
        bookList.add(new Book(-1,"1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, 450));
        bookList.add(new Book(-1, "12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1925, 760));
        bookList.add(new Book(-1, "Airport",
                new ArrayList<>(Arrays.asList("A.Heiley")), 1968, 470));
        bookList.add(new Book(-1, "Faust",
                new ArrayList<>(Arrays.asList("I.V.Goethe")), 1808, 600));
        bookList.add(new Book(-1, "Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, 350));
        bookList.add(new Book(-1, "Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, 430));
        bookList.add(new Book(-1, "Divine Comedy",
                new ArrayList<>(Arrays.asList("A.Dante")), 1265, 610));
        bookList.add(new Book(-1, "Process",
                new ArrayList<>(Arrays.asList("F.Kafka")), 1925, 700));
    }
}
