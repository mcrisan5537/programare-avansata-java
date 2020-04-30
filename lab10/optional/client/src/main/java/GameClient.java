import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class GameClient {

    public static boolean hasFinished = false;

    public static void main(String[] args) throws Exception {

        String string;

        try(Socket socket = new Socket("", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {

            // send name
            string = scanner.nextLine();
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
            while(!hasFinished) {

                string = scanner.nextLine();
                int row = Integer.parseInt(string.split(" ")[0]);
                char column = string.split(" ")[1].charAt(0);

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
                    System.out.println(string);
                }
        }
    }

}
