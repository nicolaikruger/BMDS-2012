
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
			e.printStackTrace();	
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
	
	public void send(String message) {
		Message msg = new Message(null, message);
		try {
			channel.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void receive(Message msg) {
		String message = (String) msg.getObject();
		if(message.startsWith(PUT)){
			tm.put(createReturn(message, PUT));
		}else if(message.startsWith(POST)){
			tm.post(createReturn(message, POST));
		}else if(message.startsWith(GET)){
			tm.get(createReturn(message, GET));
		}else if(message.startsWith(DELETE)){
			tm.del(createReturn(message, DELETE));
		}
	}
	
	private String createReturn(String input, String prefix)
	{
		input = input.substring(prefix.length());
		return PREFIX + input;
	}
}
