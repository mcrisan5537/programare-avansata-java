import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String request;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

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

        String input;
        char[] buf = new char[256];

        while(true) {

            try {
                Thread.sleep(1000);
                if(request != null) {
                    if(request.equalsIgnoreCase("stop")) {
                        out.println("Server stopped.");
                        GameServer.request = request;
                        out.close();
                        in.close();
                        socket.close();
                        break;
                    } else {
                        System.out.println("Server received the request: " + request);
                    }
                    request = null;
                } else {

                    input = in.readLine();
//                    System.out.println("client received:" + input);
                    if(input.equalsIgnoreCase("exit")) {
                        GameServer.clientThreads.remove(this);
                        socket.close();
                        break;
                    } else {
                        GameServer.request = input;
                    }
                }

            } catch(IOException e) {
                System.err.println("fail closing socket");
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

}
