# Natverk_IK1203
TASK2
javac TCPAsk.java

java TCPAsk --shutdown --limit 10 --timeout 300 time.nist.gov 13

--shutdown, flag to shutdown immediately
--limit 10, read only 10 bytes
--timeout 300, close connection after no packets has been received in 300ms
               timeout 0 is inf
13 = portnumber
              
-----------------------------------
TASK3
javac HTTPAsk.java

java HTTPAsk 8888

8888 is the port that the server will bind to
To try the server,
http://localhost:8888/ask?hostname=whois.iis.se&port=43&limit=500&timeout=250&shutdown=true&string=kth.se

-----------------------------------
TASK4
javac ConcHTTPAsk.java

java ConcHTTPAsk 8888

8888 is the port that the server binds to.
To try server,
http://localhost:8888/ask?hostname=java.lab.ssvl.kth.se&port=9&string=Thistextshouldbedropped
and
http://localhost:8888/ask?hostname=whois.iis.se&port=43&limit=500&timeout=250&shutdown=true&string=kth.se
