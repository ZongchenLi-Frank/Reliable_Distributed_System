import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import util.utilFunc;

public class LFD {
 
    public static void main(String[] args) throws IOException, ClassNotFoundException{
      
        int heartBeatFreq = 1;

        int port = 9876;
        Timer timer;
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
  
      
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the heartBeatFreq:");
        String timeInterval = scanner.nextLine();
        int timeIntervalInt = Integer.parseInt(timeInterval);

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask(){

            int heartBeatCount = 0;
            @Override
            public void run(){

                try {
                    sendHeartBeatMessage(heartBeatCount);
                    heartBeatCount++;
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }, 0, timeIntervalInt);
    }


    public static void sendHeartBeatMessage(int heartBeatCount) throws IOException, ClassNotFoundException{

        try{
            Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 9876);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject("heartBeat");

            System.out.println("[" + utilFunc.getTime() + "] " + heartBeatCount + " LFD1 sending heartbeat to S1");
        
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String inputMessage = (String)inputStream.readObject();

            //Verify if the server successfully receives the heartbeat message
            if(inputMessage.equals("heartbeat message received")){
                System.out.println("[" + utilFunc.getTime() + "] " + heartBeatCount + " LFD1 receives heartbeat from S1");
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Server is currently unreachable...");
        }

        //Increment heartBeatCount by 1
        heartBeatCount++;
    }

}
