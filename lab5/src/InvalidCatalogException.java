public class InvalidCatalogException extends Exception {

    public InvalidCatalogException(Exception e) {
        super("Invalid catalog file.", e);
    }
}
