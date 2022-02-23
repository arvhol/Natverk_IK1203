import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server
    public static void main( String[] args) {
        // Your code here
        int port = 0;
        String str = "HelloWorld, \r\nconnected?";
        byte[] arr = str.getBytes();
        System.out.println(str + " ok");

        // read port num from arg line
        port =  Integer.parseInt(args[0]);
        System.out.println(port);

        // testar buffer till read
        int buffSize = 1024;
        byte[] fromClient = new byte[buffSize];

        ByteArrayOutputStream buff = new ByteArrayOutputStream();

        try {
            // creates a server socket that listens to port "portnum"
            ServerSocket welcomeSocket = new ServerSocket(port);

            while(true) {
                // accept connection and create a new socket for the connection
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("OK1");

                // Display static msg
                //arr = (str + testWhile).getBytes();
                connectionSocket.getOutputStream().write(arr);
                System.out.println("OK2");

                // read 1024 bytes from client into fromClient
                int len = connectionSocket.getInputStream().read(fromClient);
                System.out.println("OK3");
                // if client msg was as long as buffsize, keep reading
                while(len == buffSize) {
                    buff.write(fromClient);
                    len = connectionSocket.getInputStream().read(fromClient);
                    System.out.println("666");
                }
                buff.write(fromClient, 0, len);
                System.out.println("FIN");
                fromClient = buff.toByteArray();

                //uri hantering


                connectionSocket.getOutputStream().write("almost\r\n\r\n".getBytes());
                connectionSocket.getOutputStream().write(fromClient);
                connectionSocket.getOutputStream().write("FIN".getBytes());

                connectionSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get request
    // call TCPClient.askServer();
}

