import java.io.PrintWriter;

public class HTMLRepresentation {

    public static void insertHeader(PrintWriter pw) {
        pw.println("<!doctype html>");
        pw.println("<html>");
            pw.println("<head>");
                pw.println("<title>Gomoku game representation</title>");
                pw.println("<style>");
                    pw.println("body { margin: 0 auto; text-align: center; }");
                pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");
    }

    public static void insertGameRound(PrintWriter pw, int roundNumber, Player currentPlayer, Board board) {
        pw.println("Round: " + roundNumber);
        pw.println(currentPlayer.getName() + "'s turn");
        pw.println("<pre>");
            pw.println(board);
        pw.println("</pre>");
    }

    public static void insertFooter(PrintWriter pw, Player winner) {
        pw.println(winner.getName() + " won the game!!!");
        pw.println("</body>");
        pw.println("</html>");
    }

}
