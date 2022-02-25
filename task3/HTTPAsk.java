import java.net.*;
import java.io.*;
import tcpclient.*;

public class HTTPAsk {
    // main is server
    public static void main(String[] args) {
        // Your code here
        int port;
        String query;
        String[][] str = new String[6][];
        String[] regex = {"hostname=", "port=", "limit=", "shutdown=", "timeout=", "string="};

        String hostname = null;
        int clientPort = 0;
        Integer limit = null;
        Integer timeout = null;
        boolean shutdown = false;
        byte[] clientInput = new byte[0];

        // testar buffer till read
        int buffSize = 1024;
        byte[] fromClient = new byte[buffSize];

        ByteArrayOutputStream buff = new ByteArrayOutputStream();

        try {
            // read port num from arg line
            port =  Integer.parseInt(args[0]);
            System.out.println(port);

            // creates a server socket that listens to port "portnum"
            ServerSocket welcomeSocket = new ServerSocket(port);

            while(true) {

                // accept connection and create a new socket for the connection
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("OK1");

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
                System.out.println("OK4");
                //fromClient = buff.toByteArray();
                connectionSocket.getOutputStream().write(buff.toByteArray());

                //uri hantering

                query = buff.toString();
                System.out.println(query);
                /*
                String[] test2 = query.split("\n");
                //query = test2[0];
                test2 = test2[0].split(" ");
                query = test2[1];

                test2 = test2[1].split("hostname=");
                test2 = test2[1].split("&");
                hostname = test2[0];

                test2 = query.split("port=");
                test2 = test2[1].split("&");
                clientPort = Integer.parseInt(test2[0]);

                if(query.contains("string=")) {
                    test2 = query.split("string=");
                    test2 = test2[1].split("&");
                    clientInput = test2[0].getBytes();
                }

                if(query.contains("limit=")) {
                    test2 = query.split("limit=");
                    test2 = test2[1].split("&");
                    limit = Integer.parseInt(test2[0]);
                }

                if(query.contains("timeout=")) {
                    test2 = query.split("timeout=");
                    test2 = test2[1].split("&");
                    timeout = Integer.parseInt(test2[0]);
                }

                if(query.contains("shutdown=")) {
                    test2 = query.split("shutdown=");
                    test2 = test2[1].split("&");
                    shutdown = Boolean.parseBoolean(test2[0]);
                } /*

                 */

                /*
                    for(int i = 0; i < regex.length; i++) {
                        str[i] = query.split(regex[i]);
                        if(str[i].length > 1) {
                            str[i] = str[i][1].split("&");
                            str[i] = str[i][0].split(" ");
                        }
                    }
                    if(query.contains("limit="))
                        limit = Integer.parseInt(str[2][0]);
                    if(query.contains("shutdown="))
                        shutdown = Boolean.parseBoolean(str[3][0]);
                    if(query.contains("timeout="))
                        timeout = Integer.parseInt(str[4][0]);
                    if(query.contains("string="))
                        clientInput = str[5][0].getBytes();

                    hostname = str[0][0];
                    clientPort = Integer.parseInt(str[1][0]);
                    for(int i = 0; i < str.length; i++){
                        for(int e = 0; e < str[i].length-1; i++){
                            System.out.println(str[i][e]);
                        }
                    }

 */
                /*
                    System.out.println("Params: " + hostname + clientPort +limit + shutdown + timeout + clientInput.toString());
                    TCPClient client = new TCPClient(shutdown, timeout, limit);
                    byte[] answerByte = client.askServer(hostname, clientPort, clientInput);

                    connectionSocket.getOutputStream().write(answerByte);
                    */

                connectionSocket.close();
                fromClient = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException f) {
            f.printStackTrace();
        }
    }
}

