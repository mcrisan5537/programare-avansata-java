import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws Exception {

        String string;

        try(Socket socket = new Socket("", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {

            while(true) {
                string = scanner.nextLine();
                out.println(string);
                if(string.equalsIgnoreCase("exit") ||
                    string.equalsIgnoreCase("stop"))
                    break;

            }

        }

    }

}
