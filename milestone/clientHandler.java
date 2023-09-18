package milestone;
import java.io.*;
import java.net.*;

public class clientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int number;

    public clientHandler(Socket socket, int number) {
        this.clientSocket = socket;
        this.number = number;
        try {
            // write to clients
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            // read from clients
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Server received message from client " + number + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}