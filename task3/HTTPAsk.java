import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server
    public static void main( String[] args) {
        // Your code here
        int port = 0;
        String str = "HelloWorld, connected";
        byte[] arr = str.getBytes();

        try {
            // read port num from arg line
            port =  Integer.parseInt(args[0]);
            // creates a server socket that listens to port "portnum"
            ServerSocket welcomeSocket = new ServerSocket(port);
            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                connectionSocket.getOutputStream().write(arr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get request
    // call TCPClient.askServer();
}

