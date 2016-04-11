package package_1;

public class RequestThread extends Thread{
	private String areaCode;
	private Thread t;
	@Override
	public void run(){
			R_Parser parser = new R_Parser();
			parser.Parse(areaCode);
	}
	
	public void start(){
		if(t==null){
			t = new Thread (this);
			t.start();
		}
	}
	
	public void setAreaCode(String ac){
		this.areaCode = ac;
	}
}