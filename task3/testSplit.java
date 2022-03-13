public class testSplit {

    public static void main(String args[]) {
        String query = "GET /ask?hostname=time.nist.gov&limit=1200&port=13&string=kth.se HTTP/1.1\n" +
                "Host: localhost:8888";

        String[] regex = {"hostname=", "port=", "limit=", "shutdown=", "timeout=", "string="};

        String getParams[][] = new String[regex.length][];

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

        for(int i = 0; i < regex.length; i++) {
            if(query.contains(regex[i])) {
                System.out.println(getParams[i][0]);
            }
        }
    }
}
