import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Proxy{
	private static int socketCounter=0;
	private static byte[] endl="\r\n".getBytes();

	public static void main(String[] args){
		try{
			ServerSocket serverSocket=new ServerSocket(8080);
			log("Server created");
			while(true){
				Socket socket=serverSocket.accept();
				Thread th=new Thread(()->{
					try{
						socket(socket);
					}catch(Exception e){
						//e.printStackTrace();
					}
				});
				th.start();
			}


		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void socket(Socket socket) throws Exception{
		socketCounter++;
		InetAddress clientIp=socket.getInetAddress();
		int clientPort=socket.getPort();
		log("Client connected from: "+clientIp+":"+clientPort);
		InputStream is=socket.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		OutputStream os=socket.getOutputStream();

		List<String> message=new ArrayList<>();
		String line;
		while((line=br.readLine())!=null&&!line.isEmpty()){
			//System.out.println(line);
			if(!line.startsWith("Proxy-Connection:")) message.add(line);
		}
		if(!message.isEmpty()){
			log(message.get(0));
			Socket server;
			int port;
			if(message.get(0).startsWith("CONNECT")){
				port=(Integer.parseInt(message.get(0).split(" ")[1].split(":")[1])!=0)?Integer.parseInt(message.get(0).split(" ")[1].split(":")[1]):443;
				server=new Socket(message.get(1).split(" ")[1].split(":")[0],port);

				os.write("HTTP/1.1 200 OK".getBytes());
				os.write(endl);
				os.write(endl);
				os.flush();

			}else{
				server=new Socket(message.get(1).split(" ")[1].split(":")[0],80);
			}
			InputStream isS=server.getInputStream();
			OutputStream osS=server.getOutputStream();

			if(!message.get(0).startsWith("CONNECT")){
				for(int i=0;i<message.size();i++){
					byte[] tab=(message.get(i)).getBytes();
					osS.write(tab);
					osS.write(endl);
				}
				osS.write(endl);
				osS.flush();
			}

			Thread income=new Thread(()->{
				try{
					byte[] bytes=new byte[4096];
					while(true){
						int size=isS.read(bytes);
						if(size==-1) break;
						os.write(bytes,0,size);
					}
				}catch(IOException e){
					//e.printStackTrace();
				}
				log("Finished");
			});
			income.start();
			//Thread outcome=new Thread(()->{
				try{
					byte[] bytes=new byte[4096];
					while(true){
						int size=is.read(bytes);
						if(size==-1) break;
						osS.write(bytes,0,size);
					}
				}catch(IOException e){
					//e.printStackTrace();
				}
			//});
			//outcome.start();
			log("Finished");
		}
	}

	public static void log(String message){
		System.out.println("[S]["+socketCounter+"] : "+LocalTime.now()+" : "+message);
	}


}
