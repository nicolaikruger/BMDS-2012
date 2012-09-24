package dk.itu.bmds.teamkolera.src.proj_main;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*;
import dk.itu.bmds.teamkolera.src.lib.*;

public class TaskManagerTCPServer {
	Calendar cal;
	Connection con = new Connection(4444);

	public TaskManagerTCPServer(String fPath) {
		cal = initRead(fPath);
		run();
	}

	private void run() {
		System.out.println("Server listens");
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
	}	

	private void put(){
		System.out.println("PUT");
	}

	private void get(){
		System.out.println("GET");
	}

	private void post(){
		con.writeString("POST");
		String xml = con.readString();

		Task t = Marshall.unMarshall(xml, Task.class);
		cal.tasks.add(t);
		save();
	}

	private void del(){
		System.out.println("DELETE");
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
