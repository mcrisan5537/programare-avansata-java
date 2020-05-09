import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class GameClient {

    public static boolean hasFinished = false;

//    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//
//        Client client = (Client) Naming.lookup("rmi://localhost:5100/client");
//        String number = Integer.toString(new Random().nextInt(50));
//        System.out.println("I sent: " + client.handleInput(number));
//        try {
//            Thread.sleep(5000);
//        } catch(InterruptedException e) {
//
//        }
//        System.out.println(client.getInput());
////        System.out.println();
//
//    }

    public static Board board = new Board();

    public static void main(String[] args) throws Exception {

//        Board board = new Board();
//        board.parseBoard(" A   B   C   D   E   F   G   H   I   J   K   L   M   N   O\n   1   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   2   .   .   .   W   .   .   .   .   .   .   .   .   .   .   .\n   3   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   4   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   5   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   6   .   .   .   .   .   .   B   .   .   .   .   .   .   .   .\n   7   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   8   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n   9   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  10   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  11   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  12   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  13   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  14   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .\n  15   .   .   .   .   .   .   .   .   .   .   .   .   .   .   .");
//        char[][] charboard = board.getBoard();
//        for(int i = 0; i < charboard.length; i++) {
//            for(int j = 0; j < charboard[0].length; j++)
//                System.out.print(charboard[i][j]);
//            System.out.println();
//        }
//        board.putNorth(6, 5);
//        System.out.println();
//        for(int i = 0; i < charboard.length; i++) {
//            for(int j = 0; j < charboard[0].length; j++)
//                System.out.print(charboard[i][j]);
//            System.out.println();
//        }

        String string, boardString = "null";
        boolean AI = false;

        try(Socket socket = new Socket("", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {

            // send name
            string = scanner.nextLine();
            if(string.equals("AI"))
                AI = true;

            out.println("setname");
            out.println(string);

            // send command (create game / join game)
            string = scanner.nextLine();
            out.println(string);
            if(string.equalsIgnoreCase("exit") ||
                string.equalsIgnoreCase("stop"))
                System.exit(0);

            new Thread(() -> {
                try {
                    handleInput(in);
                } catch(Exception e) {
                }
            }).start();

            // play game
            String move;
            int row;
            char column;
            while(!hasFinished) {

                if(!AI) {
                    string = scanner.nextLine();
                    row = Integer.parseInt(string.split(" ")[0]);
                    column = string.split(" ")[1].charAt(0);
                } else {
                    // wait for board change
                    Thread.sleep(5000);
                    boardString = board.toString();
                    move = board.getMove();
                    row = Integer.parseInt(move.split(" ")[0]) + 1;
                    column = (char)Integer.parseInt(move.split(" ")[1]);
                    System.out.println(row);
                    System.out.println(column);
                }

                out.println("put");
                out.println(row);
                out.println(column);

            }

        }

    }

    public static void handleInput(BufferedReader in) throws Exception {
        String string;
        while(true) {
            while((string = in.readLine()) != null)
                if(string.equals("finished")) {
                    hasFinished = true;
                    break;
                } else {
                    if(!string.split(":")[0].equals("game"))
                        board.parseBoard(string);
                    System.out.println(string);
                }
        }
    }

    public static void playAI() {

    }

}
