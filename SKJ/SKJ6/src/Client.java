import java.io.IOException;
import java.net.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private static byte[] doWyslania = {0x08, 0x54, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x77, 0x77, 0x77, 0x02, 0x77, 0x70, 0x02, 0x70, 0x6c, 0x00, 0x00, 0x01, 0x00, 0x01};

    public static void main(String[] args) throws IOException, InterruptedException {

        clientSocket();

    }

    public static void clientSocket() throws IOException {
        log("Start");
        log("Datagram socket creating");
        DatagramSocket socket = new DatagramSocket(5001);
        log("Datagram socket created");

        InetAddress destAdress = InetAddress.getByName("8.8.8.8");
        int destPort = 53;
        DatagramPacket packetToSend =
                new DatagramPacket(doWyslania, doWyslania.length, destAdress, destPort);

        log("Packet sending");
        socket.send(packetToSend);
        log("Packet sended");

        log("Packet receiving");
        DatagramPacket packetToReceive = new DatagramPacket(new byte[1460], 1460);
        socket.receive(packetToReceive);
        log("Packet received");
        System.out.println(new String(packetToReceive.getData(),"UTF-8"));
        for (byte b : packetToReceive.getData()) {

            //System.out.printf(String.format("0x%02x",b)+" ");
            System.out.print((char)(b<0?b+128:b));
        }
        log("Datagram socket closing");
        socket.close();
        log("Datagram socket closed");
        log("End");
    }

    public static void log(String message) {
        System.out.println("[C]: " + LocalTime.now() + " : " + message);
    }
}
