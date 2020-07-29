package by.tsikunov.day8.model.dao.impl;

import by.tsikunov.day8.exception.ConnectionException;
import by.tsikunov.day8.exception.DaoException;
import by.tsikunov.day8.model.connection.WrapperConnection;
import by.tsikunov.day8.model.dao.BookListDao;
import by.tsikunov.day8.model.dao.BookParameter;
import by.tsikunov.day8.model.entity.Book;
import by.tsikunov.day8.model.util.DataParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookSqlDaoImpl implements BookListDao {
    private static final String SQL_SELECT = "SELECT bookId, title, author, publicationYear, pages FROM book ";
    private static final String SQL_WHERE = "WHERE (%s = ?)";
    private static final String SQL_WHERE_SUB_STRING = "WHERE (SELECT INSTR(%s, ?) > 0)";
    private static final String SQL_WHERE_RANGE = "WHERE %s BETWEEN ? AND ?";
    private static final String SQL_ORDER_BY = "ORDER BY %s";
    private static final String SQL_INSERT = "INSERT INTO book (title, author, publicationYear, pages)" +
            " VALUES(?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM book " +
            "WHERE title = ? AND author = ? AND publicationYear = ? AND pages = ?";

    private final WrapperConnection wrapperConnection;

    public BookSqlDaoImpl() throws DaoException {
        try {
            wrapperConnection = new WrapperConnection();
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
            throw new DaoException("Error while creating connection to database", e);
        }
    }

    @Override
    public boolean add(Book book) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnection.getConnection().prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, book.getTitle());
            String author = String.join(",", book.getAuthors());
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, book.getPublicationYear());
            preparedStatement.setInt(4, book.getPages());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("SQL exception (request or table failed)", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public boolean remove(Book book) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = wrapperConnection.getConnection().prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, book.getTitle());
            String author = String.join(",", book.getAuthors());
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, book.getPublicationYear());
            preparedStatement.setInt(4, book.getPages());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        Statement statement = null;
        try {
            statement = wrapperConnection.getConnection().createStatement();
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(statement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public Optional<Book> findById(long bookId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            String sqlRequest = String.format(SQL_SELECT + SQL_WHERE, BookParameter.ID);
            preparedStatement = wrapperConnection.getConnection().prepareStatement(sqlRequest);
            preparedStatement.setLong(1, bookId);
            Optional<Book> resultBook = Optional.empty();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = createBook(resultSet);
                resultBook = Optional.of(book);
            }
            return resultBook;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            String sqlRequest = String.format(SQL_SELECT + SQL_WHERE_SUB_STRING, BookParameter.TITLE);
            preparedStatement = wrapperConnection.getConnection().prepareStatement(sqlRequest);
            preparedStatement.setString(1, title);
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> findByAuthor(String author) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            String sqlRequest = String.format(SQL_SELECT + SQL_WHERE_SUB_STRING, BookParameter.AUTHORS);
            preparedStatement = wrapperConnection.getConnection().prepareStatement(sqlRequest);
            preparedStatement.setString(1, author);
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> findByPublicationYear(int... yearPublication) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            String sqlRequest = String.format(SQL_SELECT + SQL_WHERE_RANGE, BookParameter.PUBLICATION_YEAR);
            preparedStatement = wrapperConnection.getConnection().prepareStatement(sqlRequest);
            if(yearPublication.length == 2){
                preparedStatement.setInt(1, yearPublication[0]);
                preparedStatement.setInt(2, yearPublication[1]);
            } else {
                preparedStatement.setInt(1, yearPublication[0]);
                preparedStatement.setInt(2, yearPublication[0]);
            }
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> findByPages(int... pages) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            String sqlRequest = String.format(SQL_SELECT + SQL_WHERE_RANGE, BookParameter.PAGES);
            preparedStatement = wrapperConnection.getConnection().prepareStatement(sqlRequest);
            if(pages.length == 2){
                preparedStatement.setInt(1, pages[0]);
                preparedStatement.setInt(2, pages[1]);
            } else {
                preparedStatement.setInt(1, pages[0]);
                preparedStatement.setInt(2, pages[0]);
            }
            List<Book> books = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(preparedStatement);
            wrapperConnection.closeConnection();
        }
    }

    @Override
    public List<Book> sortByOption(String bookParameter) throws DaoException {
        Statement statement = null;
        try {
            statement = wrapperConnection.getConnection().createStatement();
            List<Book> books = new ArrayList<>();
            String sqlRequest = String.format(SQL_SELECT + SQL_ORDER_BY, bookParameter);
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException("SQL exception", e);
        } finally {
            wrapperConnection.closeStatement(statement);
            wrapperConnection.closeConnection();
        }
    }

    private Book createBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getLong(BookParameter.ID));
        book.setTitle(resultSet.getString(BookParameter.TITLE));
        book.setPublicationYear(resultSet.getInt(BookParameter.PUBLICATION_YEAR));
        book.setPages(resultSet.getInt(BookParameter.PAGES));
        DataParser parser = new DataParser();
        List<String> authors = parser.getAuthor(resultSet.getString(BookParameter.AUTHORS));
        book.setAuthors(authors);
        return book;
    }
}
