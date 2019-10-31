import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalTime;
import java.util.logging.Logger;

public class Server {
    private static final Logger LOG = Logger.getLogger(Server.class.getName());
    private final static String ACK = "ACK";
    private static int socketCounter=0;

    public static void main(String[] args) throws IOException {
        while (true){
            socket();
        }
    }
    public static void socket() throws IOException {
        log("Start");
        log("Datagram socket creating");
        DatagramSocket socket = new DatagramSocket(5000);
        log("Datagram socket created");

        DatagramPacket packetToReceive = new DatagramPacket(new byte[1460], 1460);
        log("Packet receiving");
        socket.receive(packetToReceive);
        InetAddress address=packetToReceive.getAddress();
        int port = packetToReceive.getPort();
        log("Packet received from: " + address + ":" + port);
        log("Packet received data: "+new String(packetToReceive.getData(),0,packetToReceive.getLength()));

        log("ACK sending");
        DatagramPacket ack=new DatagramPacket(ACK.getBytes(),ACK.getBytes().length, address, port);
        socket.send(ack);
        log("ACK sended");


        log("Datagram socket closing");
        socket.close();
        log("Datagram socket closed");
        log("End");
        socketCounter++;
    }

    public static void log(String message) {
        System.out.println("[S]["+socketCounter+"] : " + LocalTime.now() + " : " + message);
    }
}
