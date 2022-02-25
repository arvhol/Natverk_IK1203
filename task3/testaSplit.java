import java.io.IOException;

public class testaSplit {


    public static void main(String args[]) {
        String query = "GET /ask?hostname=time.nist.gov&shutdown=true&timeout=3000&port=13 HTTP/1.1\n" +
                "Host: localhost:8888";

        String[][] str = new String[5][];

        byte[] usrInput = new byte[0];
        Integer limit = null;
        Integer timeout = null;
        boolean shutdown = false;

        String[] test2 = query.split("\n");
        //query = test2[0];
        test2 = test2[0].split(" ");
        query = test2[1];

        test2 = test2[1].split("hostname=");
        test2 = test2[1].split("&");
        String hostname = test2[0];

        test2 = query.split("port=");
        test2 = test2[1].split("&");
        int port = Integer.parseInt(test2[0]);

        if(query.contains("string=")) {
            test2 = query.split("string=");
            test2 = test2[1].split("&");
            usrInput = test2[0].getBytes();
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
        }

        /*
        String[] hostname;
        String[] port;
        String[] limit;
        String[] shutdown;
        String[] timeout;

        hostname = query.split("hostname=");
        port = query.split("port=");
        limit = query.split("limit=");
        shutdown = query.split("shutdown=");
        timeout = query.split("timeout=");



        String[] options = query.split("\\?");
        //query.split("?");
        options = options[1].split("&");
        */

        /*String[] regex = {"hostname=", "port=", "limit=", "shutdown=", "timeout="};

        for(int i = 0; i < regex.length; i++) {
            str[i] = query.split(regex[i]);
            if(str[i].length > 1) {
                str[i] = str[i][1].split("&");
                str[i] = str[i][0].split(" ");
            }
        }

         */

        /*try {
            int lim = Integer.parseInt(str[2][0]);
            if(lim > 0)
                System.out.println(lim);
            int timeout = Integer.parseInt(str[4][0]);
            boolean shutdown = Boolean.parseBoolean(str[3][0]);


            if(timeout > 0)
                System.out.println(timeout);
            if(shutdown == true)
                System.out.println(shutdown);

        }catch(NumberFormatException exception) {

        }
        */

        /*if(query.contains("limit="))
            System.out.println(str[2][0]);
        if(query.contains("shutdown="))
            System.out.println(str[3][0]);
        if(query.contains("timeout="))
            System.out.println(str[4][0]);

         */

        System.out.println(test2[0]);
        System.out.println("Params: " + hostname + port + limit + timeout + shutdown + usrInput.toString());
        System.out.println();
        System.out.println("END" + query);

    }
}
