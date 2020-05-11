/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChatClient{
	private static Charset charset=Charset.forName("ISO-8859-2");
	String host;
	int port;
	String id, chatView;
	SocketChannel socketChannel;
	ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);

	public ChatClient(String host,int port,String id){
		this.host=host;
		this.id=id;
		this.port=port;
	}

	public String getChatView(){
		return chatView;
	}

	public void login() throws IOException, InterruptedException{
		try{
			socketChannel=SocketChannel.open();
			socketChannel.connect(new InetSocketAddress(host,port));
			socketChannel.configureBlocking(false);
		}catch(IOException e){
			e.printStackTrace();
		}
		this.send("login "+id);
	}

	public void logout() throws IOException, InterruptedException{
		this.send("logout "+id);
		Thread.sleep(20);
		socketChannel.close();
	}

	public void send(String msg) throws IOException, InterruptedException{
		msg=msg+"\n";
		Thread.sleep(60);
		socketChannel.write(charset.encode(CharBuffer.wrap(msg)));
	}

	public void listener(StringBuffer sb) throws IOException{
		CharBuffer cbudd;
		while(true){
			try{
				byteBuffer.clear();
				int readBytes=socketChannel.read(byteBuffer);
				if(readBytes==0){
					continue;
				}else if(readBytes==-1){
					socketChannel.close();
					break;
				}else{
					byteBuffer.flip();
					cbudd=charset.decode(byteBuffer);
					sb.append(cbudd);
				}
			}catch(ClosedChannelException e){
				break;
			}
		}
	}
}
