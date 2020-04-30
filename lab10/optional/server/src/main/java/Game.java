import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {

    private static int gameID = 1;

    private String filename = "game" + new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + ".html";

    private final Board board;
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;
    private boolean isFinished = false;

    private PrintWriter representation;
    private int roundNumber = 0;

    public Game(Board board, Player player1, Player player2) throws FileNotFoundException {
        Game.gameID++;
        this.board = board;
        this.currentPlayer = player1;
        this.player1 = player1;
        player1.setColor('B');
        this.player2 = player2;
        player2.setColor('W');

        representation = new PrintWriter(filename);
        HTMLRepresentation.insertHeader(representation);
    }

    public boolean put(Player player, int row, char column) {
        // if game is not finished
        if(!isFinished) {
            column = Character.toUpperCase(column);

            if(player == currentPlayer) {
                row--;
                column = (char) (column - 'A');

                // if position is blank take position
                if(board.get()[row][column] == '.') {
                    board.get()[row][column] = currentPlayer.getColor();
                    roundNumber++;
                    HTMLRepresentation.insertGameRound(representation, roundNumber, currentPlayer, board);
                    // change player turns
                    currentPlayer = switchPlayer(currentPlayer);
                    return true;
                }

                // if position is occupied return false
                return false;
            }

            // if it's not this player's turn
            return false;
        }

        // if game is finished return false
        return false;
    }

    public boolean isWon(Player player, int rowPick, int columnPick) {
        // check for horizontal, vertical, rightDiagonal and leftDiagonal 5 in a row
        if(horizontal(player, rowPick, columnPick) || vertical(player, rowPick, columnPick) ||
            rightDiagonal(player, rowPick, columnPick) || leftDiagonal(player, rowPick, columnPick)) {
            isFinished = true;
            endGame(player);
            return true;
        }
        return false;
    }

    public boolean rightDiagonal(Player player, int rowPick, int columnPick) {
        int i = rowPick, j = columnPick;
        while(isNotOutOfBounds(i - 1, j + 1)) {
            i--;
            j++;
        }

        int countInARow = 0;
        while(isNotOutOfBounds(i, j)) {
            if(board.get()[i][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
            i++;
            j--;
        }

        return false;
    }

    public boolean leftDiagonal(Player player, int rowPick, int columnPick) {
        int i = rowPick, j = columnPick;
        while(isNotOutOfBounds(i - 1, j - 1)) {
            i--;
            j--;
        }

        int countInARow = 0;
        while(isNotOutOfBounds(i, j)) {
            if(board.get()[i][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
            i++;
            j++;
        }

        return false;
    }

    public boolean vertical(Player player, int rowPick, int columnPick) {
        int countInARow = 0;
        for(int i = 0; i < board.get().length; i++) {
            if(board.get()[i][columnPick] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
        }
        return false;
    }

    public boolean horizontal(Player player, int rowPick, int columnPick) {
        int countInARow = 0;
        for(int j = 0; j < board.get()[0].length; j++) {
            if(board.get()[rowPick][j] == player.getColor()) {
                countInARow++;
                if(countInARow == 5)
                    return true;
            } else {
                countInARow = 0;
            }
        }
        return false;
    }

    private boolean isNotOutOfBounds(int row, int column) {
        return (row >= 0 && row < board.get().length) &&
                (column >= 0 && column < board.get()[0].length);
    }

    private Player switchPlayer(Player player) {
        if(player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    private void endGame(Player player) {
        if(representation != null) {
            HTMLRepresentation.insertFooter(representation, player);
            representation.close();
            representation = null;
        }
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("192.168.64.2", 21);
            ftpClient.login("daemon", "xampp");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
            ftpClient.storeFile(filename, new FileInputStream(filename));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static int getID() {
        return Game.gameID;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
