import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameServer {

    public static String request;
    public static List<ClientThread> clientThreads = new ArrayList<>();
    static final int PORT = 12345;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(PORT)) {

            // check for messages every second
            serverSocket.setSoTimeout(1000);
            Socket socket = null;

            while(true) {

                try {
                    socket = serverSocket.accept();
                } catch(SocketTimeoutException e) {
                    if(request != null) {
//                        System.out.println("gameserver received: " + request);
                        clientThreads.forEach(thread -> thread.setRequest(request));
                        if(request.equalsIgnoreCase("stop"))
                            break;
                        // reset request
                        request = null;
                    }
                }

                // if someone has connected
                if(socket != null) {
                    ClientThread clientThread = new ClientThread(socket);
                    clientThreads.add(clientThread);
                    clientThread.start();
                    socket = null;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

//        Board board = new Board(15, 15);
//        board.putBlack(7,7);
//        board.putBlack(7,9);
//        board.putBlack(7,10);
//        board.putWhite(7,8);
//        board.putWhite(7,10);
//        board.print();

    }

}
