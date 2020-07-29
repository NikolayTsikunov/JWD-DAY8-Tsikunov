package by.tsikunov.day8.exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable e) {
        super(e);
    }
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
