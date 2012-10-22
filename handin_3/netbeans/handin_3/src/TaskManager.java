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
import java.util.List;

public class TaskManager {
	private Calendar cal;
	private String store;

	private TMChannel tg = new TMChannel(this);
	private static final String PUT = "PUT", POST = "POST", GET = "GET", DELETE = "DELETE";

	/**
	 * Starts the server on port 4444
	 * @param fPath path to xml store file
	 */
	public TaskManager(String store) {
		this.store = store;
		cal = initRead();
	}

	public void put(String taskXml){
		if(!taskXml.startsWith(TMChannel.PREFIX)){
			tg.send(PUT + taskXml);
			return;
		}

		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		cal.replace(t);		
		save();
	}

	//send a list of tasks (TaskList) that a given user participates in
	public List<Task> get(String userXml){		
		User u = Marshall.unMarshall(userXml, User.class); 
		return cal.userSched(u).tasks;
	}

	//add task
	//has poor integrety checks (will add tasks for nonexstisting users etc.)
	public void post(String taskXml){
		if(!taskXml.startsWith(TMChannel.PREFIX)){
			tg.send(POST + taskXml);
			return;
		}
		
		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		if (cal.addTask(t)) {
			save();
		}
	}

	//if a given task exists, delete it. respond with either success or task not found,
	//+no real faliure message
	public void del(String taskXml){
		if(!taskXml.startsWith(TMChannel.PREFIX)){
			tg.send(DELETE + taskXml);
			return;
		}
		
		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		if(cal.del(t)) {
			save();
		}
	}

	//returns a Calendar object deserialized from the xml file at [store]
	private Calendar initRead(){
		File f = new File(store);		
		Calendar ret = null;
		try {
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f));
			ret = Marshall.unMarshall(fis, Calendar.class);
		} catch(IOException e) {
			System.out.println("The file could not be found! Are you sure you gave the right path to the file?");
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
			System.exit(128);
		}
		new TaskManager(args[0]);
	}
}
