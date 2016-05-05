package package_1;

public class RequestThread extends Thread{
	private String areaCode;
	private static R_Parser parser;
	private Thread t;
	@Override
	public void run(){
			parser.Parse(areaCode);
	}
	
	public void setParser(R_Parser parser){
		this.parser = parser;
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