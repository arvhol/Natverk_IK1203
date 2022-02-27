import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server

    public static void main(String[] args) {

        // TCPClient params
        String hostname = null;
        Integer clientPort = null;
        byte[] clientMsg = null;

        // Optional TCPClient params
        Integer limit = null;
        Integer timeout = null;
        boolean shutdown = false;

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int serverPort = 0;
        byte[] fromClient = new byte[1024];

        try {
            // Your code here
            // binds a socket to serverPort
            serverPort = Integer.parseInt(args[0]);
            ServerSocket welcomeSocket = new ServerSocket(serverPort);

            while(true) {
                Socket connectionSocket = welcomeSocket.accept();

                int fromClientLength = connectionSocket.getInputStream().read(fromClient);

                // Compute response
                buffer.write(fromClient, 0, fromClientLength);
                if(buffer.toString().contains("GET /ask?")){
                    byte[] toClient = buffer.toByteArray();
                    connectionSocket.getOutputStream().write(toClient);
                }
                else{
                    connectionSocket.getOutputStream().write("ERROR 400 or 404".getBytes());
                }

                //END
                buffer.reset();
                connectionSocket.close();
            }

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

