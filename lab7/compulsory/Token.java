public class Token {

    private int value;

    public Token() {
        // blank token by default, identified by value 0
        value = 0;
    }

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
