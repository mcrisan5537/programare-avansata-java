public class InvalidDocumentException extends InvalidCatalogException {

    public InvalidDocumentException() {
        super("Document not found within catalog.");
    }

    public InvalidDocumentException(String message) {
        super(message);
    }

    public InvalidDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
