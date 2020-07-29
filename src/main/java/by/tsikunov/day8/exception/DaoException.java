package by.tsikunov.day8.exception;

public class DaoException extends Exception {
    public DaoException() {
        super();
    }
    public DaoException(String message) {
        super(message);
    }
    public DaoException(Throwable e) {
        super(e);
    }
    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
