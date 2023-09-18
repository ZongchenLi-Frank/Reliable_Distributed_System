package milestone;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static void createServer(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            int count = 1; 
            while (true) {
                Socket clientSocket = socket.accept();
                LocalDateTime current = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String Timestamp = current.format(format);
                System.out.println("[ " + Timestamp +" ] Client " + count + " has been connected. ");
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
