import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class Client {
    private final static int SERVER_PORT = 10000;
    private final static String SERVER_HOSTNAME = "localhost";
    private final static String ACK = "ACK";
    private final static String NAK = "NAK";
    private static int SESSIONID;

    public static void main(String[] args) throws IOException {
        log("Started");
        log("Client socket creating");
        Socket clientSocket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
        log("Client connected to: " + SERVER_HOSTNAME + " : " + SERVER_PORT);
        log("Streams collecting");
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        log("Sending login");
        bw.write("ala");
        bw.newLine();
        bw.flush();
        log("Waiting for answer");
        String answer = br.readLine();
        log("Answer received: " + answer);
        if (answer.compareTo(ACK) == 0) {
            log("Sending password");
            bw.write("1234\n");
            bw.flush();
            log("Waiting for answer");
            String answer2 = br.readLine();
            if (answer2.compareTo(ACK) == 0) {
                log("Waiting for session ID");
                SESSIONID = br.read();
                log("Session Id: "+SESSIONID);

                for(int i=0;i<Math.random()*10;i++){
                    log("Sending message");
                    bw.write("ala ma kota\n");
                    bw.flush();
                    log("Waiting for answer");
                    String answer3 = br.readLine();
                    log("Answer received: " + answer3);
                }
                log("Logout");
                bw.write(SESSIONID);
                bw.newLine();
                bw.flush();

                log("Waiting for answer");
                String answer4 = br.readLine();
                log("Answer received: " + answer4);

            }
        }
        else{
            log("Login denied");
        }


        log("Client socket closing");
        clientSocket.close();
        log("Client socked closed");
        log("Finished");
    }

    public static void log(String message) {
        System.out.println("[C] : " + LocalTime.now() + " : " + message);
    }
}
