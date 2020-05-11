/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class ChatServer{
	private static Charset charset=Charset.forName("ISO-8859-2");
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;
	ExecutorService executorService;
	StringBuilder serverLog;
	boolean isServerRunning;
	public ChatServer(String host,int port){
		try{
			serverSocketChannel=ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(host,port));
			selector=Selector.open();
			serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
			serverLog=new StringBuilder();
			isServerRunning=false;
			executorService=Executors.newCachedThreadPool();
		}catch(IOException ignored){
		}
	}

	public void startServer(){
		executorService.execute(this::serverService);
		System.out.println("Server started"+"\n");
	}

	public void stopServer(){
		isServerRunning=false;
		executorService.shutdownNow();
		System.out.println("Server stopped");
	}

	public String getServerLog(){
		return serverLog.toString();
	}

	private void writeResp(SocketChannel socketChannel,String message) throws IOException{
		socketChannel.write(charset.encode(CharBuffer.wrap(message)));
	}

	public void serverService(){
		isServerRunning=true;
		Pattern pattern=Pattern.compile(" +",Pattern.UNIX_LINES|Pattern.CASE_INSENSITIVE);
		StringBuffer reqString=new StringBuffer();
		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
		List<String> loginList=new ArrayList<>();
		HashMap<SocketChannel,String> socketLogin=new HashMap<>();
		HashMap<String,SocketChannel> loginSocket=new HashMap<>();
		while(isServerRunning){
			try{
				selector.select();
				Set keys=selector.selectedKeys();
				Iterator iter=keys.iterator();
				while(iter.hasNext()){
					SelectionKey key=(SelectionKey)iter.next();
					iter.remove();
					if(key.isAcceptable()){
						SocketChannel socketChannel=serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector,SelectionKey.OP_READ);
					}else{
						if(key.isReadable()){
							SocketChannel socketChannel=(SocketChannel)key.channel();
							if(!socketChannel.isOpen())
								return;
							reqString.setLength(0);
							byteBuffer.clear();
							try{
								readLoop:
								while(true){
									int n=socketChannel.read(byteBuffer);
									if(n>0){
										byteBuffer.flip();
										CharBuffer charBuffer=charset.decode(byteBuffer);
										while(charBuffer.hasRemaining()){
											char c=charBuffer.get();
											if(c=='\r'||c=='\n') break readLoop;
											reqString.append(c);
										}
									}
								}
								String[] req=pattern.split(reqString,2);
								String cmd=req[0];
								if(!cmd.equals("logout")&&!cmd.equals("login")){
									serverLog.append(LocalTime.now()).append(" ").append(socketLogin.get(socketChannel)).append(": ").append(req[0]).append(" ").append(req[1]).append("\n");
									for(Map.Entry<String,SocketChannel> entry: loginSocket.entrySet()){
										writeResp(entry.getValue(),socketLogin.get(socketChannel)+": "+req[0]+" "+req[1]+"\n");
									}
								}else if(cmd.equals("login")){
									loginList.add(req[1]);
									socketLogin.put(socketChannel,req[1]);
									loginSocket.put(req[1],socketChannel);
									serverLog.append(LocalTime.now()).append(" ").append(req[1]).append(" logged in").append("\n");
									for(Map.Entry<String,SocketChannel> entry: loginSocket.entrySet()){
										writeResp(entry.getValue(),req[1]+" logged in"+"\n");
									}
								}else{
									serverLog.append(LocalTime.now()).append(" ").append(socketLogin.get(socketChannel)).append(" logged out").append("\n");
									for(Map.Entry<String,SocketChannel> entry: loginSocket.entrySet()){
										writeResp(entry.getValue(),socketLogin.get(socketChannel)+" logged out"+"\n");
									}
									loginSocket.remove(socketLogin.get(socketChannel));
									socketLogin.remove(socketChannel);
									socketChannel.close();
									socketChannel.socket().close();
								}
							}catch(Exception ignored){
							}
						}
					}
				}
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
	}
}
