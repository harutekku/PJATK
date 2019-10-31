import java.io.IOException;
import java.net.*;
import java.time.LocalTime;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private static int counterClient=0;

    public static void main(String[] args) throws IOException, InterruptedException {
        while(true){
            clientSocket();
            Thread.sleep(10000);
        }
    }
    public static void clientSocket() throws IOException {
        log("Start");
        log("Datagram socket creating");
        DatagramSocket socket = new DatagramSocket(5001);
        log("Datagram socket created");

        String textToSend="Welcome to the JACKASS spam number "+counterClient;
        String ascii="\n            _____ _____    _   _         _    _ _  __            ____  _    _ ______ ______ _______ \n" +
                "     /\\    / ____|  __ \\  | \\ | |   /\\  | |  | | |/ /    /\\     |  _ \\| |  | |  ____|  ____|__   __|\n" +
                "    /  \\  | (___ | |  | | |  \\| |  /  \\ | |  | | ' /    /  \\    | |_) | |  | | |__  | |__     | |   \n" +
                "   / /\\ \\  \\___ \\| |  | | | . ` | / /\\ \\| |  | |  <    / /\\ \\   |  _ <| |  | |  __| |  __|    | |   \n" +
                "  / ____ \\ ____) | |__| | | |\\  |/ ____ \\ |__| | . \\  / ____ \\  | |_) | |__| | |    | |____   | |   \n" +
                " /_/    \\_\\_____/|_____/  |_| \\_/_/    \\_\\____/|_|\\_\\/_/    \\_\\ |____/ \\____/|_|    |______|  |_|   ";
        InetAddress destAdress=InetAddress.getByName("172.22.132.255");
        int destPort = 5000;
        DatagramPacket packetToSend =
                new DatagramPacket(textToSend.getBytes(), textToSend.getBytes().length,destAdress,destPort);

        log("Packet sending");
        socket.send(packetToSend);
        log("Packet sended");

        log("ACK receiving");
        DatagramPacket packetToReceive = new DatagramPacket(new byte[1460], 1460);
        socket.receive(packetToReceive);
        log("ACK received");


        log("Datagram socket closing");
        socket.close();
        log("Datagram socket closed");
        log("End");
        counterClient++;
    }

    public static void log(String message) {
        System.out.println("[C]["+counterClient+"] : " + LocalTime.now() + " : " + message);
    }
}
