package dk.itu.bmds.teamkolera.src.proj_main;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import dk.itu.bmds.teamkolera.src.lib.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*;


public class TaskManagerTCPClient {

	Connection con;

	//open a connection to the given port on the given ip address
	public TaskManagerTCPClient(String ip, int port) {
		con = new Connection(ip, port);
	}

	//update a task in the remote calendar
	public void put(Task t) {
		con.writeString("PUT");
		System.out.println(con.readString());
		String xml = Marshall.marshall(t);
		con.writeString(xml);
		System.out.println(con.readString());
	}

	//delete a task in the remote calendar
	public void delete(Task t) {
		con.writeString("DELETE");
		System.out.println(con.readString());
		String xml = Marshall.marshall(t);
		con.writeString(xml);
		System.out.println(con.readString());
	}

	//print a list of tasks a given user is involved with
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

	//add a task to the remote calendar
	public void post(Task t) {
		con.writeString("POST");
		System.out.println(con.readString());
		String taskAsXML = Marshall.marshall(t);
		con.writeString(taskAsXML);
		System.out.println("Server responded: " + con.readString());
		con.kill();
	}

	public static void main(String[] args) {
		TaskManagerTCPClient client = new TaskManagerTCPClient("127.0.0.1", 4444);
		Task t = new Task("Do MDS Mandatory Exercise 1", "blah", "somedate", "Do MDS Mandatory Exercise 1", "do this shit");
		t.attendants = "Kruger Dinesen Therkildsen Henriksen";
		client.post(t);
	}
}
