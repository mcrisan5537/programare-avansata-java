package gomoku;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class Board {

    public char[][] board;

    public Board(int stonesHorizontally, int stonesVertically) {
        board = new char[stonesHorizontally][stonesVertically];
        init(board, stonesHorizontally, stonesVertically);
    }

    private void init(char[][] board, int n, int m) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                board[i][j] = '.';
    }

    public char[][] get() {
        return board;
    }

    @Override
    public String toString() {
        ByteArrayOutputStream byteBoard = new ByteArrayOutputStream(512);
        PrintWriter printWriter = new PrintWriter(byteBoard);

        // print column indexes
        printWriter.printf("%4s", "");
        for(int j = 0; j < board[0].length; j++)
            printWriter.printf("%4s", (char)('A' + j));
        printWriter.println();

        for(int i = 0; i < board.length; i++) {
            // print row indexes
            printWriter.printf("%4s", i + 1);

                for(int j = 0; j < board[0].length; j++) {
                    printWriter.printf("%4s", board[i][j]);
                }

            printWriter.println();
        }

        printWriter.flush();
        return byteBoard.toString();
    }

}
