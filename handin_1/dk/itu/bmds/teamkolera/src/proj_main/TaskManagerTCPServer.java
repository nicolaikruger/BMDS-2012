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

package dk.itu.bmds.teamkolera.src.proj_main;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*;
import dk.itu.bmds.teamkolera.src.lib.*;

public class TaskManagerTCPServer {
	Calendar cal;
	Connection con;

	public TaskManagerTCPServer(String fPath) {
		cal = initRead(fPath);
		run();
	}

	private void run() {
		while(true) {
			System.out.println("Server listens");
			initListen();
			String comm = (con.readString());

			switch (comm) {
				case "PUT": 	put();
						break;
				case "POST": 	post();
						break;
				case "GET": 	get();
						break;
				case "DELETE":	del();
						break;
				default:
						con.writeString("WHATYOUSAY?");
						break;
			}
			
			stopListen();
		}
	}	

	private void put(){
		System.out.println("'PUT' recieved");
		con.writeString("PUT");
		String xml = con.readString();
		Task t = Marshall.unMarshall(xml, Task.class);
		String msg = cal.replace(t);
		save();
		con.writeString(msg);
	}

	private void get(){
		System.out.println("'GET' recieved");
		con.writeString("GET");
		String xml = con.readString();
		String msg;

		User u = Marshall.unMarshall(xml, User.class); //should take an id rather than a user
		TaskList ret = cal.userSched(u);
		if(ret.tasks.isEmpty()) {
			msg = "user has no tasks";
		} else {
			msg = Marshall.marshall(ret);
		}
		con.writeString(msg);
	}

	private void post(){
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

	private void del(){
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

	private void initListen(){
		con = new Connection(4444);
	}
	
	private void stopListen(){
		if (con != null) {
			con.kill();
		}
	}

	private Calendar initRead(String fPath){
		File f = new File(fPath);
		Calendar ret = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			ret = Marshall.unMarshall(fis, Calendar.class);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	private void save(){
		try {
			File f = new File("task-manager.xml");
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write(Marshall.marshall(cal));
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Params: [path to xmlStore document]");
			System.exit(128);;
		}
		new TaskManagerTCPServer(args[0]);
	}
}
