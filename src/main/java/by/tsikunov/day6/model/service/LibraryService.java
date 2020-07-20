package by.tsikunov.day6.model.service;

import by.tsikunov.day6.model.entity.Book;
import by.tsikunov.day6.model.service.response.Response;

public interface LibraryService {
    Response addBook(Book book);

    Response removeBook(Book book);

    Response findById(long bookId);

    Response findByTitle(String title);

    Response findByAuthor(String author);

    Response findByPublicationYear(int yearPublication);

    Response findByPages(int pages);

    Response findAll();

    Response sortById();

    Response sortByTitle();

    Response sortByAuthor();

    Response sortByPublicationYear();

    Response sortByPages();
}
