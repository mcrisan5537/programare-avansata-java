import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class GameServer {

    public static String request;
    public static List<ClientThread> clientThreads = Collections.synchronizedList(new ArrayList<>());
    public static List<Game> startedGames = Collections.synchronizedList(new ArrayList<>());
    public static Map<Integer, List<Player>> notStartedGames = Collections.synchronizedMap(new HashMap<>());
    public static Map<Player, Socket> playerToSocket = Collections.synchronizedMap(new HashMap<>());
    static final int PORT = 12345;

    public static void main(String[] args) throws FileNotFoundException {

        try(ServerSocket serverSocket = new ServerSocket(PORT)) {

            // check for messages every second
            serverSocket.setSoTimeout(1000);
            Socket socket = null;

            while(true) {

                try {
                    socket = serverSocket.accept();
                } catch(SocketTimeoutException e) {
                    // handleRequest returns true if request is "stop"
                    if(request != null && handleRequest())
                        break;
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
//        try {
//            Registry registry = LocateRegistry.createRegistry(5100);
//            registry.rebind("client", new ClientThread());
//        } catch(RemoteException e) {
//            e.printStackTrace();
//        }

    }

    private static boolean handleRequest() {
        // System.out.println("gameserver received: " + request);
        clientThreads.forEach(client -> client.setRequest(request));
        if(request.equalsIgnoreCase("stop")) {
            return true;
        }

        // reset request
        request = null;
        return false;
    }

}
