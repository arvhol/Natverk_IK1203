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

       /* if(shutdown == false) { // if shutdown is true, then skip all reading and close socket
            ByteArrayOutputStream Buffer = new ByteArrayOutputStream();

            if(limit >= 1) { //
                int data = clientSocket.getInputStream().read();
                while(data != - 1) {
                    limit--;
                    Buffer.write(data);
                    if(limit == 0)
                        break;
                    data = clientSocket.getInputStream().read();
                }
            }

            fromServerBytes = Buffer.toByteArray();
        }*/

        // long maxTime = System.currentTimeMillis() + timeout;
        // && (limit >= 1 || limit == null) && (timeout != 0 || timeout == null)

        /*if(shutdown == false) {
            ByteArrayOutputStream Buffer = new ByteArrayOutputStream();
            int data = clientSocket.getInputStream().read();
            while(data != -1) {
                Buffer.write(data);
                data = clientSocket.getInputStream().read();
            }
            fromServerBytes = Buffer.toByteArray();
        }*/ //shtdwn funkar

        /*while(limit == null || limit >= 1) {
            data = clientSocket.getInputStream().read();
            if(data == -1) break;
            if(limit != null) limit--;
            Buffer.write(data);
        }
        fromServerBytes = Buffer.toByteArray(); */ //limit ok

        /*if(timeout != null && timeout > 0) {
            clientSocket.setSoTimeout(timeout);
        }
        try {
            while((data = clientSocket.getInputStream().read()) != - 1) {
                Buffer.write(data);
            }
        } catch(SocketTimeoutException STE) {
            clientSocket.close();
        }*/ // timeout med setSoTimeout

        if(shutdown==false) {
            if(timeout != null && timeout > 0) {
                clientSocket.setSoTimeout(timeout);
            }
            try {
                while (limit == null || limit >= 1) {
                    data = clientSocket.getInputStream().read();
                    if (data == -1) break;
                    if (limit != null) limit--;
                    Buffer.write(data);
                }
            }
            catch(SocketTimeoutException STE) {
                clientSocket.close();
            }
        } // finito

        fromServerBytes = Buffer.toByteArray();
        clientSocket.close();

        return fromServerBytes;
    }
}
