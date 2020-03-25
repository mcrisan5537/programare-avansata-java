public class ShellException extends Exception {

    public ShellException() {
        super("Shell error");
    }

    public ShellException(String message) {
        super(message);
    }
}
