import java.net.*;
import java.io.*;

public class ConcHTTPAsk {

    public static void main(String args[]) {
        int serverPort = 0;

        try {
            serverPort = Integer.parseInt(args[0]);
            ServerSocket welcomeSocket = new ServerSocket(serverPort);

            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                (new Thread(new ServerRunnable(connectionSocket))).start();
            }
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
