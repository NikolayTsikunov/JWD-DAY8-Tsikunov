package by.tsikunov.day6.model.service.impl;

import by.tsikunov.day6.model.dao.BookListDao;
import by.tsikunov.day6.model.dao.impl.BookListDaoImpl;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.service.LibraryService;
import by.tsikunov.day6.model.service.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public Response addBook(Book book) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        boolean result = bookListDao.add(book);
        if(result) {
            response.setMessage("Book successfully added");
        } else {
            response.setMessage("Can't add book");
        }
        response.setResultBooks(bookListDao.findAll());
        return response;
    }

    @Override
    public Response removeBook(Book book) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        boolean result = bookListDao.remove(book);
        if(result) {
            response.setMessage("Book successfully removed");
        } else {
            response.setMessage("Can't remove book");
        }
        response.setResultBooks(bookListDao.findAll());
        return response;
    }

    @Override
    public Response findAll() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.findAll();
        response.setMessage("All books");
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response findById(long bookId) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        Optional<Book> book = bookListDao.findById(bookId);
        if(book.isEmpty()) {
            response.setMessage(String.format("Can't find book by id {%d}", bookId));
            response.setResultBooks(new ArrayList<>());
        } else {
            response.setMessage("The book has found");
            response.setResultBooks(Arrays.asList(book.get()));
        }
        return response;
    }

    @Override
    public Response findByTitle(String title) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.findByTitle(title);
        if(books.isEmpty()) {
            response.setMessage(String.format("Can't find books with this title {%s}", title));
        } else {
            response.setMessage("The books have found");
        }
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response findByAuthor(String author) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.findByAuthor(author);
        if(books.isEmpty()) {
            response.setMessage(String.format("Can't find books with this author {%s}", author));
        } else {
            response.setMessage("The books have found");
        }
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response findByPublicationYear(int publicationYear) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.findByPublicationYear(publicationYear);
        if(books.isEmpty()) {
            response.setMessage(String.format("Can't find books with this publication year {%d}", publicationYear));
        } else {
            response.setMessage("The books have found");
        }
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response findByPages(int pages) {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.findByPages(pages);
        if(books.isEmpty()) {
            response.setMessage(String.format("Can't find books with this count of pages {%d}", pages));
        } else {
            response.setMessage("The books have found");
        }
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response sortById() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.sortById();
        response.setMessage("Sorted list by id");
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response sortByTitle() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.sortByTitle();
        response.setMessage("Sorted list by title");
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response sortByAuthor() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.sortByAuthor();
        response.setMessage("Sorted list by author");
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response sortByPublicationYear() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.sortByPublicationYear();
        response.setMessage("Sorted list by publication year");
        response.setResultBooks(books);
        return response;
    }

    @Override
    public Response sortByPages() {
        BookListDao bookListDao = new BookListDaoImpl();
        Response response = new Response();
        List<Book> books = bookListDao.sortByPages();
        response.setMessage("Sorted list by count of pages");
        response.setResultBooks(books);
        return response;
    }
}
