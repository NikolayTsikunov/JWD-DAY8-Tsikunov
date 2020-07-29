package by.tsikunov.day8.exception;

public class ConnectionException extends Exception {
    public ConnectionException() {
        super();
    }
    public ConnectionException(String message) {
        super(message);
    }
    public ConnectionException(Throwable e) {
        super(e);
    }
    public ConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
