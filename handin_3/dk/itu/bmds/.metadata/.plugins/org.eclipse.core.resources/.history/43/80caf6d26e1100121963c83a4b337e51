/*
This is the "web service"
It handles expected errors, ie. errors such as 
+"you tryed to add a task with the id of an existing one".
However, it fails miserably on errors such as
+"you tryed to add a task, but provided data
+that could not be unmarshalled into a task object"
Currently the server class is responsible for handling connections, managing the persistance store, and verifyring data.
Idealy, it would only handle connections and connection errors, and a seperate store module would handle persistance and integrity.
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

import org.jgroups.util.TimeScheduler.Task;

public class TaskManager {
	private TMChannel tg = new TMChannel(this);
	private static final String PUT = "PUT", POST = "POST", GET = "GET", DELETE = "DELETE";
	
	/**
	 * Starts the server on port 4444
	 *@param fPath path to xml store file
	 */
	public TaskManager() {
		
	}

	public void put(String taskXml){
		if(!taskXml.startsWith(TMChannel.PREFIX)){
			tg.send(PUT + taskXml);
			return;
		}
		
		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		
		Task t = Marshall.unMarshall(xml, Task.class);
		save();
		con.writeString(msg);
	}

	//send a list of tasks (TaskList) that a given user participates in
	public void get(){
		System.out.println("'GET' recieved");
		con.writeString("GET");
		String xml = con.readString();
		String msg;

		User u = Marshall.unMarshall(xml, User.class); 
		TaskList ret = cal.userSched(u);
		if(ret.tasks.isEmpty()) {
			msg = "user has no tasks";
		} else {
			msg = Marshall.marshall(ret);
		}
		con.writeString(msg);
	}

	//add task
	//has poor integrety checks (will add tasks for nonexstisting users etc.)
	public void post(){
		System.out.println("'POST' recieved");
		con.writeString("POST");
		String xml = con.readString();
		String msg;

		Task t = Marshall.unMarshall(xml, Task.class);
		if (cal.addTask(t)) {
			save();
			msg = "Task added";
		} else {
			msg = "non critical faliure: task id allready in use";
		}

		con.writeString(msg);
	}

	//if a given task exists, delete it. respond with either success or task not found,
	//+no real faliure message
	public void del(){
		System.out.println("'DELETE' recieved");;
		con.writeString("DELETE");
		String xml = con.readString();
		Task t = Marshall.unMarshall(xml, Task.class);
		String msg;
		if(cal.del(t)) {
			msg = "task deleted";
			save();
		} else {
			msg = "task not found";
		}
		con.writeString(msg);
	}

	//start listening on port 4444
	//call moved here from run() to give more controll over 
	//+the binding call.
	private void initListen(){
		con = new Connection(4444);
	}
	
	private void stopListen(){
		if (con != null) {
			con.kill();
		}
	}

	//returns a Calendar object deserialized from the xml file at [store]
	private Calendar initRead(){
		File f = new File(store);
		Calendar ret = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			ret = Marshall.unMarshall(fis, Calendar.class);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	//save cal : Calendar to xml store file at [store]
	private void save(){
		try {
			File f = new File(store);
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write(Marshall.marshall(cal));
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	

	//Start the server.
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Params: [path to xmlStore document]");
			System.exit(128);;
		}
		new TaskManagerTCPServer(args[0]);
	}
}
