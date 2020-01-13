import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server{
	static int password;
	public static void main(String[] args){
		password=pass(args);
		startServer(args);
		//File plik=new File("c:/hiberfil.sys");
		//System.out.println(plik.length());
	}

	public static void startServer(String[] args){
		Map<String,List<Integer>> ports=new HashMap<>();
		for(Integer i=0;i<args.length;i++){
			if(!ports.containsKey(args[i])){
				ports.put(args[i],new ArrayList<>());
			}
			ports.get(args[i]).add(i);
		}
		final byte allKnock=(byte)args.length;
		Map<String,Integer> clients=new HashMap<>(); //klient to ip:port
		Lock lock =new ReentrantLock();
		for(Map.Entry<String,List<Integer>> port:ports.entrySet()){
			System.out.println(port.getKey()+" "+port.getValue().toString());
			new Thread(()->{
				try{
					String correct=String.valueOf(password+Integer.parseInt(port.getKey()));
					DatagramSocket datagramSocket=new DatagramSocket(Integer.parseInt(port.getKey()));
					log("Nasłuchuje na porcie "+port.getKey(),port.getKey());
					DatagramPacket packetToReceive = new DatagramPacket(new byte[1460], 1460);
					while (true) {
						datagramSocket.receive(packetToReceive);
						String client = packetToReceive.getAddress().toString()+":"+packetToReceive.getPort();
						String msg = new String(packetToReceive.getData(), packetToReceive.getOffset(), packetToReceive.getLength());
						lock.lock();
						try{
							if(!correct.equals(msg)){
								log("Niepoprawny pakiet od "+client,port.getKey());
								continue;
							}
							if(!clients.containsKey(client)){
								if(port.getValue().contains(0)){
									clients.put(client,1);
									log("Nowy klient o adresie "+client,port.getKey());
								}
								else{
									log("Niepożądane połączenie od "+client,port.getKey());
								}
							}
							else{
								if(port.getValue().contains(clients.get(client))){
									log("Stary klient o adresie "+client,port.getKey());
									clients.replace(client,(clients.get(client)+1));
									if(clients.get(client)==allKnock){
										log("Poprawna konfiguracja od "+client,port.getKey());
										//new Send(packetToReceive.getAddress(),packetToReceive.getPort()).start();
										clients.remove(client);
									}
								}
								else{
									clients.remove(client);
									log("Niepożądane połączenie od "+client,port.getKey());
								}
							}
						}finally{
							lock.unlock();
						}
					}
				}catch(IOException e){
					e.printStackTrace();
					System.exit(1);
				}
			}).start();
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

	public static void log(String message, String port) {
		System.out.println("[S]["+port+"] : " + LocalTime.now() + " : " + message);
	}
}
