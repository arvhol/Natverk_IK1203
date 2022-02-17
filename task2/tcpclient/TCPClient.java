package tcpclient;
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeoutException;

public class TCPClient {
    private boolean shutdown;
    private Integer timeout;
    private Integer limit;

    public TCPClient(boolean shutdown, Integer timeout, Integer limit) {
        this.shutdown = shutdown;
        this.timeout = timeout;
        this.limit = limit;
    }

    // Om shutdown är sann ska anslutningen stängas efter att meddelandet har skickats till servern.
    // Timeout, så fort anslutningen blir tyst ska timern börja.

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {

        Socket clientSocket = new Socket(hostname, port);

        byte [] fromServerBytes = new byte[0];
        ByteArrayOutputStream Buffer = new ByteArrayOutputStream();
        int data;

        clientSocket.getOutputStream().write(toServerBytes);

        // if shutdown is true, close output
        if(shutdown) clientSocket.shutdownOutput();

        // timeout 0 means inf, if timeout null set to inf
        if(timeout == null || timeout < 0) {
            timeout = 0;
        }
        clientSocket.setSoTimeout(timeout);

        try {
            while(limit == null || limit >= 1) {
                data = clientSocket.getInputStream().read();
                if(data == - 1) break;
                if(limit != null) limit--;
                Buffer.write(data);
            }
        }
        catch(SocketTimeoutException STE) {
            System.out.println("socketTimeout err");
        }

        clientSocket.close();
        fromServerBytes = Buffer.toByteArray();
        return fromServerBytes;
    }
}

