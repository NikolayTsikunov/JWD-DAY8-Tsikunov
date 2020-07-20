package by.tsikunov.day6.model.dao.impl;

import by.tsikunov.day6.model.dao.BookListDao;
import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.entity.Library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookListDaoImpl implements BookListDao {

    @Override
    public boolean add(Book book) {
        return Library.getInstance().addBook(book);
    }

    @Override
    public boolean remove(Book book) {
        return Library.getInstance().removeBook(book);
    }

    @Override
    public Optional<Book> findById(long bookId) {
        List<Book> books = Library.getInstance().getBooks();
        Optional<Book> resultBook = Optional.empty();
        for (Book book : books) {
            if(book.getBookId() == bookId) {
                resultBook = Optional.of(book);
                break;
            }
        }
        return resultBook;
    }

    @Override
    public List<Book> findAll() {
        return Library.getInstance().getBooks();
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> resultBooks = new ArrayList<>();

        for (Book book : books) {
            if(book.getTitle().contains(title)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> resultBooks = new ArrayList<>();

        for (Book book : books) {
            List<String> authors = book.getAuthors();

            for (String currentAuthor: authors) {
                if(currentAuthor.contains(author)) {
                    resultBooks.add(book);
                    break;
                }
            }
        }
        return resultBooks;
    }

    @Override
    public List<Book> findByPublicationYear(int publicationYear) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.getPublicationYear() == publicationYear) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    @Override
    public List<Book> findByPages(int pages) {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.getPages() == pages) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing(Book::getBookId));
        return result;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing(Book::getTitle));
        return result;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing((Book book) -> book.getAuthors().size()).
                thenComparing(book -> book.getAuthors().get(0)));
        return result;
    }

    @Override
    public List<Book> sortByPublicationYear() {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing(Book::getPublicationYear));
        return result;
    }

    @Override
    public List<Book> sortByPages() {
        List<Book> books = Library.getInstance().getBooks();
        List<Book> result = new ArrayList<>(books);
        result.sort(Comparator.comparing(Book::getPages));
        return result;
    }
}
