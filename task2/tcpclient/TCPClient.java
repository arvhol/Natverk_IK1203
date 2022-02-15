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
        String STR = "shutdown";
        byte [] fromServerBytes = STR.getBytes();

        ByteArrayOutputStream Buffer = new ByteArrayOutputStream();
        int data = 0;

        clientSocket.getOutputStream().write(toServerBytes);

        // if shutdown is true, or timeout is less then or equal to zero, never listen to the server
        if(shutdown == true || timeout <= 0) {
            clientSocket.close();
        }
        else {
            // if timeout isnt null, and greater than zero we set a timer. Otherwise
            if(timeout != null && timeout > 0) {
                clientSocket.setSoTimeout(timeout);
            }
            try {
                while(limit == null || limit >= 1) {
                    data = clientSocket.getInputStream().read();
                    if(data == - 1) break;
                    if(limit != null) limit--;
                    Buffer.write(data);
                }
            }
            catch(SocketTimeoutException STE) {
                clientSocket.close();
            }
            clientSocket.close();
        }

        fromServerBytes = Buffer.toByteArray();
        //clientSocket.close();

        return fromServerBytes;
    }
}
