package milestone;
import java.io.*;
import java.net.*;

public class Server {
    public static void createServer(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            int count = 1; 
            while (true) {
                Socket clientSocket = socket.accept();
                System.out.println("Client " + count + " has been connected. ");
                // Handle client communication in a new thread
                clientHandler clientHandler = new clientHandler(clientSocket, count);
                new Thread(clientHandler).start();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int port = 10002; 
        createServer(port); 
    }
}
