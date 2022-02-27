import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server
    static int bufferSize = 1024;

    public static void main(String[] args) {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int serverPort = 0;

        String[] regex = {"hostname=", "port=", "limit=", "shutdown=", "timeout=", "string="};
        String getParams[][] = new String[regex.length][];
        String query;

        try {
            // Your code here
            // binds a socket to serverPort
            serverPort = Integer.parseInt(args[0]);
            ServerSocket welcomeSocket = new ServerSocket(serverPort);

            while(true) {
                // TCPClient params
                String hostname = null;
                Integer clientPort = null;
                byte[] clientMsg = null;
                // Optional TCPClient params
                Integer limit = null;
                Integer timeout = null;
                boolean shutdown = false;

                byte[] fromClient = new byte[bufferSize];

                // Accept connection
                Socket connectionSocket = welcomeSocket.accept();

                // read message from client
                int fromClientLength = connectionSocket.getInputStream().read(fromClient);
                query = new String(fromClient);
                /*while(fromClientLength == bufferSize) {
                    buffer.write(fromClient);
                    fromClientLength = connectionSocket.getInputStream().read(fromClient);
                }
                buffer.write(fromClient, 0, fromClientLength);
                 */

                System.out.println(query);
                // Compute response
                // ERROR Handling
                if(!query.contains("hostname=") || !query.contains("port=")) {
                    connectionSocket.getOutputStream().write("HTTP/1.1 404 Page Not Found\r\n\r\nError Page Not Found 404".getBytes());
                }
                else if(!query.contains("HTTP/1.1") || !query.contains("GET /ask?")) {
                    connectionSocket.getOutputStream().write("HTTP/1.1 400 Bad Request\r\n\r\nError Bad Request 400".getBytes());
                }
                // Split URI
                else {

                    String[] help = query.split("\n");
                    help = help[0].split(" ");
                    query = help[1];

                    System.out.println(query);

                    for(int i = 0; i < regex.length; i++) {
                        if(query.contains(regex[i])) {
                            getParams[i] = query.split(regex[i]);
                            getParams[i] = getParams[i][1].split("&");
                        }
                    }
                    // FELSÖK
                    for(int i = 0; i < regex.length; i++) {
                        if(query.contains(regex[i])) {
                            System.out.println(getParams[i][0]);
                        }
                    }
                    hostname = getParams[0][0];
                    clientPort = Integer.parseInt(getParams[1][0]);
                    clientMsg = new byte[0];
                    if(query.contains("string=")) {
                        clientMsg = getParams[5][0].getBytes();
                    }
                    if(query.contains("limit=")) {
                        limit = Integer.parseInt(getParams[2][0]);
                    }
                    if(query.contains("timeout=")) {
                        timeout = Integer.parseInt(getParams[4][0]);
                    }
                    if(query.contains("shutdown=")) {
                        shutdown = Boolean.parseBoolean(getParams[3][0]);
                    }

                    // Call TCP
                    TCPClient newClient = new TCPClient(shutdown, timeout, limit);
                    fromClient = newClient.askServer(hostname, clientPort, clientMsg);

                    connectionSocket.getOutputStream().write("HTTP/1.1\r\n\r\n".getBytes());
                    connectionSocket.getOutputStream().write(fromClient);
                }
                /*
                if(buffer.toString().contains("GET /ask?")){
                    byte[] toClient = buffer.toByteArray();
                    connectionSocket.getOutputStream().write(toClient);
                }
                else{
                    connectionSocket.getOutputStream().write("ERROR 400 or 404".getBytes());
                }

                 */
                //END
                //buffer.reset();
                connectionSocket.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

