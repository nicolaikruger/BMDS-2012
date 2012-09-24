package dk.itu.bmds.teamkolera.src.proj_main;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import dk.itu.bmds.teamkolera.src.lib.*;
import dk.itu.bmds.teamkolera.src.taskmanager.*;


public class TaskManagerTCPClient {

	Connection con = new Connection("127.0.0.1", 4444);

	public TaskManagerTCPClient(String message) {
		con.writeString("POST");
		System.out.println(con.readString());
		Task doShit = new Task("42", "Executed", "10-10-10", "Nicolai", "doShieeeeet");
		String taskAsXML = Marshall.marshall(doShit);
		con.writeString(taskAsXML);
		con.kill();
	}

	public static void main(String[] args) {
		new TaskManagerTCPClient("Lars");
	}
}
