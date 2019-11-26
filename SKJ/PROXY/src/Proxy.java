import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class Proxy{
	private final static String ACK = "ACK";
	private static int socketCounter=0;

	public static void main(String[] args){
		Thread th=new Thread(()->{
			try {
				while(true)socket();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		th.start();
	}

	public static void socket() throws IOException {
		log("Socket created");
		ServerSocket welcomeSocket = new ServerSocket(8080);
		Socket clientSocket = welcomeSocket.accept();
		InetAddress clientIp = clientSocket.getInetAddress();
		int clientPort = clientSocket.getPort();
		log("Client connected from: " + clientIp + " : " + clientPort);
		BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		String line=br.readLine(),message="";
		while(!line.equals("")){
			message+=line;
			line=br.readLine();
		}
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
		socketCounter++;
	}
	public static void log(String message) {
		System.out.println("[S]["+socketCounter+"] : " + LocalTime.now() + " : " + message);
	}


}
