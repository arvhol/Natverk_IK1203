package tcpclient;

import java.net.*;
import java.io.*;

public class TCPClient {

    public TCPClient() {
    }

    public byte[] askServer(String hostname, int port, byte[] toServerBytes) throws IOException, UnknownHostException {

        // Create a socket and connect it to the server
        Socket clientSocket = new Socket(hostname, port);

        // Send byte array to server on the socket, no need for offset or length since it has been made exactly from
        // a string. No empty indexes in the array.
        clientSocket.getOutputStream().write(toServerBytes);

        // ByteArrayOutputStream is dynamic and can add each byte separately until the input stream is empty (-1).
        ByteArrayOutputStream fromServerBuffer = new ByteArrayOutputStream();
        int data = clientSocket.getInputStream().read();
        while(data != - 1) {
            fromServerBuffer.write(data);
            data = clientSocket.getInputStream().read();
        }

        // Terminate the connection between the sockets.
        clientSocket.close();

        return fromServerBuffer.toByteArray();
    }
}
