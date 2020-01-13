import java.io.IOException;
import java.net.*;
import java.time.LocalTime;

public class Client{
	public static void main(String[] args){
		try{
			int password=pass(args);
			DatagramSocket datagramSocket = new DatagramSocket();
			for(String port:args){
				String message=String.valueOf(password+Integer.parseInt(port));
				byte[] tab=message.getBytes();
				DatagramPacket packet=new DatagramPacket(tab, tab.length, InetAddress.getByName("localhost"), Integer.parseInt(port));
				datagramSocket.send(packet);
				log("Wysłałem na port "+port);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static int pass(String[] args){
		int sum=0,result;
		for(String number:args){
			sum+=Integer.parseInt(number);
		}
		while(sum>9){
			result=0;
			while(sum>0){
				result+=sum%10;
				sum/=10;
			}
			sum=result;
		}
		return sum;
	}

	public static void log(String message) {
		System.out.println("[C] : " + LocalTime.now() + " : " + message);
	}
}
