
import org.jgroups.*;

// Ad-hoc class biatch
public class TMChannel extends ReceiverAdapter{
	public static final String PREFIX = "JMSG";
	private static final String PUT = "PUT", POST = "POST", GET = "GET", DELETE = "DELETE";
	private TaskManager tm;
	
	private JChannel channel = initChannel();
	
	private static JChannel initChannel(){
		JChannel tmpChannel = null;
		try{
		tmpChannel = new JChannel();
		}catch(Exception e){
			System.out.println("WHATAFAK :O!");	
		}
		
		return tmpChannel;
	}
	
	public TMChannel(TaskManager tm){
		this.tm = tm;
		try {
			channel.connect("TaskGroupKolera");
			channel.setReceiver(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void put(String taskXml){
		Message msg = new Message(null, PUT + taskXml);
		try {
			channel.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String get(String userXml){
		
	}
	
	public String post(String taskXml){
		
	}
	
	public String delete(String taskId){
		
	}
	
	public void send(String msg) {
		Message msg = new Message(null, msg);
		try {
			channel.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void receive(Message msg) {
		
	}
}
