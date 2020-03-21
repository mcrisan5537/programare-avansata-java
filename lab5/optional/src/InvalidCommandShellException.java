public class InvalidCommandShellException extends ShellException {

    public InvalidCommandShellException() {
        super("Invalid shell command.");
    }

    public InvalidCommandShellException(String message) {
        super(message);
    }
}
