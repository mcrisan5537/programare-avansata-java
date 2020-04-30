import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ClientThread extends Thread {

    private final Socket socket;
    private String request;
    public BufferedReader in;
    public PrintWriter out;

    private int gameID;
    private Player player;
    private Game game;
    private String playerName;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            System.err.println("failed BufferedReader/PrintWriter initialisation");
            e.printStackTrace();
        }
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public void run() {

        while(true) {

            try {
                Thread.sleep(1000);
                if(request != null) {
                    if(handleRequest()) // returns true if request is "stop"
                        break;
                } else {
                    if(handleInput()) // returns true if request is "exit"
                        break;
                }
            } catch(InterruptedException e) {
                System.err.println("thread interrupted");
            }

        }

    }

    public boolean handleRequest() {
        if(request != null) {
            if(request.equalsIgnoreCase("stop")) {
                out.println("Server stopped.");
                GameServer.request = request;
                out.close();
                try {
                    in.close();
                    socket.close();
                } catch(IOException e) {
                    System.err.println("failure closing bufferedreader, printwriter");
                    e.printStackTrace();
                }
                return true;
            } else {
                System.out.println("Server received the request: " + request);
            }
            request = null;
        }
        return false;
    }

    public boolean handleInput() {
        String input;
        try {
            input = in.readLine();
//            System.out.println("client received:" + input);
            if(input.equalsIgnoreCase("exit")) {
                GameServer.clientThreads.remove(this);
                socket.close();
                return true;
            } else if(input.equalsIgnoreCase("create game")) {
                gameID = Game.getID();
                GameServer.notStartedGames.put(gameID, Collections.synchronizedList(new ArrayList<>()));
                GameServer.request = input + "(game ID: " + gameID + ")";

                player = new Player(playerName);
                GameServer.notStartedGames.get(gameID).add(player);
                GameServer.playerToSocket.put(player, socket);
            } else if(input.equalsIgnoreCase("put")) {
                game = GameServer.startedGames.get(0);

                int rowPick = Integer.parseInt(in.readLine());
                char columnPick = Character.toUpperCase(in.readLine().charAt(0));

                game.put(player, rowPick, columnPick);
                GameServer.clientThreads.forEach(client -> client.out.println(game.getBoard()));
                if(game.isWon(player,rowPick - 1, columnPick - 'A')) {
                    GameServer.clientThreads.forEach(client -> client.out.println(player.getName() + " has won the game."));
                    GameServer.clientThreads.forEach(client -> client.out.println("finished"));
                }

            } else if(input.equalsIgnoreCase("setname")) {
                playerName = in.readLine();
            } else if("join game".equalsIgnoreCase(input.split(" ")[0] + " " + input.split(" ")[1])) {
                    gameID = Integer.parseInt(input.split(" ")[2]);

                    player = new Player(playerName);
                    GameServer.notStartedGames.get(gameID).add(player);
                    GameServer.playerToSocket.put(player, socket);
                    if(GameServer.notStartedGames.get(gameID).size() == 2) {
                        // create the actual game
                        game = new Game(new Board(15, 15),
                                        GameServer.notStartedGames.get(gameID).get(0),
                                        GameServer.notStartedGames.get(gameID).get(1));
                        GameServer.startedGames.add(game);
                        // game has started, remove it from notStartedGames
                        GameServer.notStartedGames.put(gameID, null);
//                    GameServer.request = "game: " + gameID + " started";

                        GameServer.clientThreads.forEach(client -> client.out.println("game: " + gameID + " started"));
                    }

            } else {
                GameServer.request = input;
            }
        } catch(IOException e) {
            System.err.println("failure in IO");
            e.printStackTrace();
        }
        return false;
    }

}
