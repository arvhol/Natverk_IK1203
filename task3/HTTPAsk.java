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
        // test
        int testWhile = 0;

        ByteArrayOutputStream buff = new ByteArrayOutputStream();

        try {
            // creates a server socket that listens to port "portnum"
            ServerSocket welcomeSocket = new ServerSocket(port);
            System.out.println(port+1);

            while(true) {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("OK1");

                arr = (str + testWhile).getBytes();
                connectionSocket.getOutputStream().write(arr);
                System.out.println("OK2");

                int len = connectionSocket.getInputStream().read(fromClient);
                System.out.println("OK3");
                while(len == buffSize) {
                    buff.write(fromClient);
                    len = connectionSocket.getInputStream().read(fromClient);
                    System.out.println("99");
                    testWhile++;
                }
                buff.write(fromClient);
                System.out.println("fin");
                byte[] finitMSG = new byte[(buffSize*testWhile) + len];
                finitMSG = buff.toByteArray();
                connectionSocket.getOutputStream().write("almost".getBytes());
                connectionSocket.getOutputStream().write(finitMSG);
                connectionSocket.getOutputStream().write("fin".getBytes());

                connectionSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get request
    // call TCPClient.askServer();
}

