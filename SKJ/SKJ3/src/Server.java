import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server {
    private final static int SERVER_PORT = 10000;
    private final static String USER_FROM_DB = "ala";
    private final static String USER_PASS = "1234";
    private final static String ACK = "ACK";
    private final static String NAK = "NAK";
    private static int SESSIONID;

    public static void main(String[] args) throws IOException {
        log("Started");
        log("Server socket creating");
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        log("Server socked created");
        log("Server listening");

        Socket clientSocket = welcomeSocket.accept();
        String clientIp = clientSocket.getInetAddress().toString();
        int clientPort = clientSocket.getPort();
        log("Client connected from: " + clientIp + " : " + clientPort);
        log("Streams collecting");
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        log("Waiting for login");
        String login = br.readLine();
        log("Login received: " + login);
        String answer = (USER_FROM_DB.compareTo(login) == 0 ? ACK : NAK);
        log("Sending answer");
        bw.write(answer);
        bw.newLine();
        bw.flush();

        log("Waiting for password");
        String password = br.readLine();
        log("Password received: " + password);
        if (USER_PASS.compareTo(password) == 0) {
            log("Sending answer");
            bw.write(ACK);
            bw.newLine();
            bw.flush();

            SESSIONID = (int) (Math.random() * 10000);
            log("SESSION ID: " + SESSIONID);
            log("Sending SESSIONID");
            bw.write(SESSIONID);
            bw.flush();

            log("Waiting for message");
            String message = br.readLine();
            while (message.compareTo(String.valueOf(SESSIONID))!=0) {
                log("Message received: " + message);
                log("Sending answer");
                bw.write(message.toUpperCase() + "\n");
                bw.flush();
                message = br.readLine();
            }

            log("Logout succesful");
            SESSIONID = 0;
            bw.write("Logout succesful\n");
            bw.flush();

        } else {
            bw.write(NAK + "\n");
            bw.flush();
        }


        log("Client socket closing");
        clientSocket.close();

        log("Client socked closed");

        log("Server socket closing");
        welcomeSocket.close();

        log("Server socked closed");

        log("Finished");

    }

    public static void log(String message) {
        System.out.println("[S] : " + LocalTime.now() + " : " + message);
    }
}
