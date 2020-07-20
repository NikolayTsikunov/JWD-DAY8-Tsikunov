package by.tsikunov.day6.model.entity;

import by.tsikunov.day6.model.util.IdGenerator;

import java.util.List;

public class Book {
    private long bookId;
    private String title;
    private List<String> authors;
    private int publicationYear;
    private int pages;

    public Book(String title, List<String> authors, int publicationYear, int pages) {
        this.bookId = IdGenerator.generateId();
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }


    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean equalsWithoutId(Book book) {
        return title .equals(book.title) &&
                authors.equals(book.authors) &&
                publicationYear == book.publicationYear &&
                pages == book.pages;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        return bookId == book.bookId &&
                title .equals(book.title) &&
                authors.equals(book.authors) &&
                publicationYear == book.publicationYear &&
                pages == book.pages;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) bookId;
        result = 31 * result + (title == null ? 0 : title.hashCode());
        result = 31 * result + (authors == null ? 0 : authors.hashCode());
        result = 31 * result + publicationYear;
        result = 31 * result + pages;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Book(bookId: %d; title: %s; authors: %s; publicationYear: %d; pages: %d)",
                bookId, title, authors.toString(), publicationYear, pages);
    }
}