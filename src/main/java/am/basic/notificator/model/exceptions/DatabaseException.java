package am.basic.notificator.model.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(Throwable throwable) {
        super(throwable);
    }
}
