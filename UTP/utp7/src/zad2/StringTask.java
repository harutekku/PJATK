package zad2;

public class StringTask implements Runnable{
	String word;
	int count;
	String result;
	State state;

	public StringTask(String word, int count){
		this.word=word;
		this.count=count;
		result="";
		state=State.CREATED;
	}

	@Override
	public void run(){
		state=State.RUNNING;
		while(count>0){
			if(Thread.currentThread().isInterrupted()) break;
			result+=word;
			count--;
		}
		if(state!=State.ABORTED)state=State.READY;
	}

	public String getResult(){
		return result;
	}

	public State getState(){
		return state;
	}

	public void start(){
		new Thread(this::run).start();
	}

	public void abort(){
		Thread.currentThread().interrupt();
		state=State.ABORTED;
	}

	public boolean isDone(){
		return state==State.READY||state==State.ABORTED;
	}
}
