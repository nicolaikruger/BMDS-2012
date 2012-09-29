package dk.itu.bmds.teamkolera.src.proj_main;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import dk.itu.bmds.teamkolera.src.lib.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*;


public class TaskManagerTCPClient {

	Connection con = new Connection("127.0.0.1", 4444);

	public void put(Task t) {
		con.writeString("PUT");
		System.out.println(con.readString());
		String xml = Marshall.marshall(t);
		con.writeString(xml);
		System.out.println(con.readString());
	}

	public void delete(Task t) {
		con.writeString("DELETE");
		System.out.println(con.readString());
		String xml = Marshall.marshall(t);
		con.writeString(xml);
		System.out.println(con.readString());
	}

	public void get(User u) {
		con.writeString("GET");
		System.out.println(con.readString());
		String userAsXML = Marshall.marshall(u);
		con.writeString(userAsXML);
		String xml = con.readString();
		if(xml.equals("user has no tasks")) {
			System.out.println(xml);
		} else {
			 TaskList tl = Marshall.unMarshall(xml, TaskList.class);
			 System.out.println("user " + u.getName() + " has the following tasks");
			 for(Task t : tl.tasks) {
				System.out.println(t.getName());
			 }
		}
	}

	public void post() {
		con.writeString("POST");
		System.out.println(con.readString());
		Task doShit = new Task("42", "Executed", "10-10-10", "Nicolai", "doShieeeeet");
		String taskAsXML = Marshall.marshall(doShit);
		con.writeString(taskAsXML);
		System.out.println("Server responded: " + con.readString());
		con.kill();
	}

	public static void main(String[] args) {
		TaskManagerTCPClient client = new TaskManagerTCPClient();
		//User u = new User("42", "rao", "42");
		//client.get(u);
		//Task t = new Task("assn-01-approve", "blah", "blah", "blah", "blah");
		//client.delete(t);
		Task t = new Task("assn-01-approve", "blah", "blah", "blah", "blah");
		client.put(t);
	}
}
