# Natverk_IK1203

javac TCPAsk.java

java TCPAsk --shutdown --limit 10 --timeout 300 time.nist.gov 13

--shutdown, flag to shutdown immediately
--limit 10, read only 10 bytes
--timeout 300, close connection after no packets has been received in 300ms
               timeout 0 is inf
