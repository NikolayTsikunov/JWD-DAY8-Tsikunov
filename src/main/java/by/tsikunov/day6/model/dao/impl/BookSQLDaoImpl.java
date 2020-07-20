package by.tsikunov.day6.model.dao.impl;

import by.tsikunov.day6.model.dao.BookListDao;
import by.tsikunov.day6.model.entity.Book;

import java.util.List;
import java.util.Optional;

public class BookSQLDaoImpl implements BookListDao {
    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean remove(Book book) {
        return false;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findByPublicationYear(int yearPublication) {
        return null;
    }

    @Override
    public List<Book> findByPages(int pages) {
        return null;
    }

    @Override
    public List<Book> sortById() {
        return null;
    }

    @Override
    public List<Book> sortByTitle() {
        return null;
    }

    @Override
    public List<Book> sortByAuthor() {
        return null;
    }

    @Override
    public List<Book> sortByPublicationYear() {
        return null;
    }

    @Override
    public List<Book> sortByPages() {
        return null;
    }
}
