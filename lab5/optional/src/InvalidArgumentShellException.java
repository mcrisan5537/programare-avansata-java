public class InvalidArgumentShellException extends ShellException {

    public InvalidArgumentShellException() {
        super("Invalid arguemnt");
    }

    public InvalidArgumentShellException(String message) {
        super(message);
    }
}
