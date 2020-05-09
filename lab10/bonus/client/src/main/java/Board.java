import java.util.Random;

public class Board {

    private volatile char[][] board;
    private String boardString;

    public Board() {
        this.board = new char[15][15];
        this.boardString = "null";
    }

    public void parseBoard(String board) {
        this.boardString = board;
        int boardi = 0;
        int boardj = 0;

        String[] splitBoard = board.split("\\n");
        splitBoard[0] = null;
        for(String line : splitBoard) {
            if(line != null) {
                for(int i = 0; i < line.length(); i++)
                    if(line.charAt(i) == 'B' || line.charAt(i) == 'W' || line.charAt(i) == '.') {
                        this.board[boardi][boardj++] = line.charAt(i);
                    }
                boardj = 0;
                boardi++;
            }
        }
    }

    public String getMove() {
        int i = getLastBlackI();
        int j = getLastBlackJ();

        System.out.println(i + " " + j);
        if(i == -1 && j == -1) {
            Random random = new Random();
            int picki = random.nextInt(15);
            int pickj = 'A' + random.nextInt(15);
            board[picki][pickj - 'A'] = 'B';
            return picki + " " + pickj;
        } else if(putSouthEast(i, j)) {
            return (i+1) + " " + ('A' + (j+1));
        } else if(putNorthWest(i, j)) {
            return (i-1) + " " + ('A' + (j-1));
        } else if(putNorthEast(i, j)) {
            return (i-1) + " " + ('A' + (j+1));
        } else if(putSouthWest(i, j)) {
            return (i+1) + " " + ('A' + (j-1));
        } else if(putSouth(i, j)) {
            return (i-1) + " " + ('A' + j);
        } else if(putNorth(i, j)) {
            return (i+1) + " " + ('A' + j);
        } else if(putEast(i, j)) {
            return i + " " + ('A' + (j+1));
        } else if(putWest(i, j)) {
            return i + " " + ('A' + (j-1));
        }

        return "0 65";
    }

    public boolean putNorth(int i, int j) {
        if(isNotOutOfBounds(i+1, j) &&
                board[i+1][j] != 'B' && board[i+1][j] != 'W') {
            board[i-1][j] = 'B';
            return true;
        }
        return false;
    }

    public boolean putSouth(int i, int j) {
        if(isNotOutOfBounds(i-1, j) &&
                board[i-1][j] != 'B' && board[i-1][j] != 'W') {
            board[i-1][j] = 'B';
            return true;
        }
        return false;
    }

    public boolean putEast(int i, int j) {
        if(isNotOutOfBounds(i, j+1) &&
                board[i][j+1] != 'B' && board[i][j+1] != 'W') {
            board[i][j+1] = 'B';
            return true;
        }
        return false;
    }

    public boolean putWest(int i, int j) {
        if(isNotOutOfBounds(i, j-1) &&
                board[i][j-1] != 'B' & board[i][j+1] != 'W') {
            board[i][j-1] = 'B';
            return true;
        }
        return false;
    }

    public boolean putNorthEast(int i, int j) {
        if(isNotOutOfBounds(i-1, j+1) &&
                board[i-1][j+1] != 'B' && board[i-1][j+1] != 'W') {
            board[i-1][j+1] = 'B';
            return true;
        }
        return false;
    }

    public boolean putSouthEast(int i, int j) {
        if(isNotOutOfBounds(i+1, j+1) &&
                board[i+1][j+1] != 'B' && board[i+1][j+1] != 'W') {
            board[i+1][j+1] = 'B';
            return true;
        }
        return false;
    }

    public boolean putNorthWest(int i, int j) {
        if(isNotOutOfBounds(i-1, j-1) &&
                board[i-1][j-1] != 'B' && board[i-1][j-1] != 'W') {
            board[i-1][j-1] = 'B';
            return true;
        }
        return false;
    }

    public boolean putSouthWest(int i, int j) {
        if(isNotOutOfBounds(i+1, j-1) &&
                board[i+1][j-1] != 'B' && board[i+1][j-1] != 'W') {
            board[i+1][j-1] = 'B';
            return true;
        }
        return false;
    }

    public int getLastBlackI() {
        int lasti = -1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++)
                if(board[i][j] == 'B')
                    lasti = i;
        }
        return lasti;
    }

    public int getLastBlackJ() {
        int lastj = -1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++)
                if(board[i][j] == 'B')
                    lastj = j;
        }
        return lastj;
    }

    private boolean isNotOutOfBounds(int row, int column) {
//        return (row >= 0 && row < board.length) &&
//                (column >= 0 && column < board[0].length);
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return boardString;
    }

}
