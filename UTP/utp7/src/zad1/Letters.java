package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters{
	List<Thread> threads;

	public Letters(String letters){
		threads=new ArrayList<>();
		char[] lets=letters.toCharArray();
		for(char c: lets){
			threads.add(new Thread(()->{
				while(true){
					try{
						if(Thread.currentThread().isInterrupted()) break;
						Thread.sleep(1000);
						System.out.print(c);
					}catch(Exception e){
						break;
					}
				}
			},"Thread "+c));
		}
	}

	public List<Thread> getThreads(){
		return threads;
	}
}
