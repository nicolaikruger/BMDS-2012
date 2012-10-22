import org.jgroups.*;

public class GroupMock extends ReceiverAdapter {
	String pre;
	Channel ch;

	public GroupMock(String str) throws Exception{
		super();
		ch = new JChannel();
		ch.setReceiver(this);
		ch.connect("Chatter");
		pre = str;
	}

	public void sendLoop(String str) throws Exception{
		Message msg = new Message(null, pre + " " + str);
		while(true) {
			ch.send(msg);
			Thread.sleep(2000);
		}
	}

	@Override
	public void receive(Message msg) {
		System.out.println(msg.getObject());
	}
	
	public static void main(String[] args) {
		try {
		GroupMock one = new GroupMock("one");
		GroupMock two= new GroupMock("two");
		one.sendLoop("Ah send a lawd meeesage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
