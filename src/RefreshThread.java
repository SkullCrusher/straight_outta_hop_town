package package_1;

public class RefreshThread extends Thread{
	private String threadName;
	private int sleepTime;
	private Thread t;
	private static R_Parser parser;
	private boolean running= true;
	
	public void setParser(R_Parser parser){
		this.parser = parser;
	}
	
	@Override
	public void run(){
		try {
			while(running){ //just keep chugging away
			if(this.isInterrupted()){
				this.running=false;
				throw new InterruptedException();
			}
			if(running){
				Thread.sleep(this.sleepTime*60*100);//this way sleep time setting is like number of minutes
		//		System.out.println("refreshing--"+this.threadName+"--"+this.sleepTime);
					if(running){
				parser.Parse(this.threadName);//thread named after area code
				}}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//System.out.println("Another one bites the dust");
			this.running = false;
		}
	}
	
	public void start(){
		if(t==null){
			t = new Thread (this, this.threadName);
			t.start();
		}
	}
	
	public void setSleepTime(int st){
		this.sleepTime=st;
	}
	public void setRunning(boolean running){
		this.running = running;
	}
	public void setThreadName(String name){
		this.threadName = name;
	}
	public String getThreadName(){
		return this.threadName;
	}
	
}
