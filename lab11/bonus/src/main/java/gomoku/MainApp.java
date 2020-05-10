package gomoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.*;

@SpringBootApplication
public class MainApp {

    public static byte[] SECRET = Base64.getDecoder().decode("nHhb+8dWe51kIFTRUHICiO04kHzIkw9CrLT5hmEBvIo=");

    public static String request;
    public static List<ClientThread> clientThreads = Collections.synchronizedList(new ArrayList<>());
    public static List<Game> startedGames = Collections.synchronizedList(new ArrayList<>());
    public static Map<Integer, List<Player>> notStartedGames = Collections.synchronizedMap(new HashMap<>());
    public static Map<Player, Socket> playerToSocket = Collections.synchronizedMap(new HashMap<>());
    static final int PORT = 12345;

    public static void main(String[] args) throws FileNotFoundException {

//        Instant now = Instant.now();
//
//        String jwtString = Jwts.builder()
//                .setSubject("test subject")
//                .setAudience("test audience")
//                .claim("random number", new Random().nextInt(1000))
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(1, ChronoUnit.MINUTES)))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//
//        System.out.println(jwtString);
//
//        Jws<Claims> result = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(jwtString);
//
//        System.out.println(result);
//        System.out.println(result.getBody().get("random number", Integer.class));

        SpringApplication.run(MainApp.class, args);

//        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
//
//            // check for messages every second
//            serverSocket.setSoTimeout(1000);
//            Socket socket = null;
//
//            while(true) {
//
//                try {
//                    socket = serverSocket.accept();
//                } catch(SocketTimeoutException e) {
//                    // handleRequest returns true if request is "stop"
//                    if(request != null && handleRequest())
//                        break;
//                }
//
//                // if someone has connected
//                if(socket != null) {
//                    ClientThread clientThread = new ClientThread(socket);
//                    clientThreads.add(clientThread);
//                    clientThread.start();
//                    socket = null;
//                }
//            }
//
//        } catch(IOException e) {
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
