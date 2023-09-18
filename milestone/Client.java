package milestone;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change to the server's IP if needed
        int serverPort = 10002; // Use the same port as the server
        
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            // Send messages to the server

            while (true) {
                System.out.print("Enter a message: ");
                String userInput = scanner.nextLine();

                // Send the user's input to the server
                out.println(userInput);

                // Check if the user wants to exit
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

            }
            
            // Close the socket and scanner
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
    }
}
