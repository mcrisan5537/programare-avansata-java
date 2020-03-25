public class InvalidCatalogException extends Exception {

    public InvalidCatalogException() {
        super("Invalid catalog file.");
    }

    public InvalidCatalogException(String message) {
        super(message);
    }

    public InvalidCatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}
