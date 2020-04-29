public class Board {

    private static final String COLOR_WHITE = "\u001B[37m";
    private static final String COLOR_BLACK = "\u001B[30m";

    public char[][] board;

    public Board(int stonesHorizontally, int stonesVertically) {
        board = new char[stonesHorizontally][stonesVertically];
        initBoard(board, stonesHorizontally, stonesVertically);
    }

    private void initBoard(char[][] board, int n, int m) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                board[i][j] = '.';
    }

    public char[][] get() {
        return board;
    }

    public boolean putWhite(int row, int column) {
        if(board[row][column] == '.') {
            board[row][column] = 'W';
            return true;
        } else {
            return false;
        }
    }
    public boolean putBlack(int row, int column) {
        if(board[row][column] == '.') {
            board[row][column] = 'B';
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        // print column indexes
        System.out.printf("%4s", "");
        for(int j = 0; j < board[0].length; j++)
            System.out.printf("%4s", j + 1);
        System.out.println();

        System.out.print("\u001B[30m");
        for(int i = 0; i < board.length; i++) {
            // print row indexes
            System.out.printf("%4s", i + 1);

                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] == 'W') {
                        System.out.print(COLOR_WHITE);
                    } else {
                        System.out.print(COLOR_BLACK);
                    }
                    System.out.printf("%4s", board[i][j]);
                }

            System.out.println();
        }
    }

}
