import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server
    byte[] fromClient = new byte[1024];

    // TCPClient params
    String hostname = null;
    Integer clientPort = null;
    byte[] clientMsg = null;
    // Optional TCPClient params
    Integer limit = null;
    Integer timeout = null;
    boolean shutdown = false;

    public static void main( String[] args) {
        try {
            // Your code here
            int serverPort = Integer.parseInt(args[0]);
            ServerSocket welcomeSocket = new ServerSocket(serverPort);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

