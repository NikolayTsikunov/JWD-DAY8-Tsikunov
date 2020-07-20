package by.tsikunov.day6.model.service.response;

import by.tsikunov.day6.model.entity.Book;

import java.util.List;
import java.util.Objects;

public class Response {
    private String message;
    private List<Book> resultBooks;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getResultBooks() {
        return resultBooks;
    }

    public void setResultBooks(List<Book> resultBooks) {
        this.resultBooks = resultBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return message.equals(response.message) &&
                resultBooks.equals(response.resultBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, resultBooks);
    }
}
