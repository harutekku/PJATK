/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ChatClientTask extends FutureTask<ChatClient>{
	public ChatClientTask(Callable<ChatClient> callable){
		super(callable);
	}

	public static ChatClientTask create(ChatClient c,List<String> msgs,int wait){
		Callable<ChatClient> callable=()->{
			StringBuffer chatView=new StringBuffer();
			c.login();
			chatView.append("=== ").append(c.id).append(" chat view").append("\n");
			new Thread(()->{
				try{
					c.listener(chatView);
				}catch(IOException ignored){
				}
			}).start();
			if(wait!=0){
				msgs.forEach(t->
				{
					try{
						c.send(t);
						Thread.sleep(wait);
					}catch(IOException|InterruptedException e){
						chatView.append("***").append(e.toString());
					}
				});
			}
			c.logout();
			Thread.sleep(wait);
			c.chatView=chatView.toString();
			return c;
		};
		return new ChatClientTask(callable);
	}

	public ChatClient getClient(){
		try{
			return this.get();
		}catch(ExecutionException|InterruptedException ignored){
		}
		return null;


	}
}
