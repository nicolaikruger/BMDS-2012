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

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}

		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		cal.replace(t);	
		System.out.println("Saving after update...");
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
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
		
		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		if (cal.addTask(t)) {
			System.out.println("Saving after posting...");
			save();
		}
	}

	//if a given task exists, delete it. respond with either success or task not found,
	//+no real faliure message
	public void del(String taskXml){
		if(!taskXml.startsWith(TMChannel.PREFIX)){
			tg.send(DELETE + taskXml);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
			}
			return;
		}
		
		taskXml = taskXml.substring(TMChannel.PREFIX.length());
		Task t = Marshall.unMarshall(taskXml, Task.class);
		if(cal.del(t)) {
			System.out.println("Saving after deletion...");
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
			System.out.println("The file could not be found! Are you sure you gave the right path to the file?"
					+ "\nThere should be 3 files at the root of the program."
					+ "\n1: task-manager-store0.xml"
					+ "\n2: task-manager-store1.xml"
					+ "\n3: task-manager-store2.xml");
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
		TaskManager t0 = new TaskManager("C:/Users\\Nicolai\\Documents\\GitHub\\BMDS-2012\\handin_3\\resources\\task-manager-store0.xml");
		TaskManager t1 = new TaskManager("C:/Users\\Nicolai\\Documents\\GitHub\\BMDS-2012\\handin_3\\resources\\task-manager-store1.xml");
		TaskManager t2 = new TaskManager("C:/Users\\Nicolai\\Documents\\GitHub\\BMDS-2012\\handin_3\\resources\\task-manager-store2.xml");
		
		System.out.println("Adds a new task (with attendant name \"Nicolai Krüger\", to TM-0");
		Task task1 = new Task("Do MDS Mandatory Exercise 3", "blah", "somedate", "Do MDS Mandatory Exercise 3", "do this shit");
		task1.attendants = "Nicolai Kruger";
		t0.post(Marshall.marshall(task1));
		
		System.out.println("Prints out Nicolai Krügers tasks from TM-1");
		User kruger = new User("id", "Nicolai Kruger", "Why?");
		List<Task> krugerTasks = t1.get(Marshall.marshall(kruger));
		for(Task task : krugerTasks) {
			System.out.println(Marshall.marshall(task));
		}
		
		System.out.println("Updates the task from TM-2, new attendant is \"Morten Therkildsen\"");
		Task task2 = new Task("Do MDS Mandatory Exercise 3", "blah blah blah Bladt blah blah blah", "some other date", "Don't do MDS Mandatory Exercise 3", "do this shit");
		task2.attendants = "Morten Therkildsen";
		t2.put(Marshall.marshall(task2));
		
		
		System.out.println("Prints out Morten Therkildsen tasks from TM-0");
		User morten = new User("id", "Morten Therkildsen", "Follet");
		List<Task> mortenTasks = t0.get(Marshall.marshall(morten));
		for(Task task : mortenTasks) {
			System.out.println(Marshall.marshall(task));
		}
		
		System.out.println("Removes the task from TM-1");
		t1.del(Marshall.marshall(task2));
		
		System.out.println("\nDoes Morten Therkildsen still contain the same tasks as before? Check with TM-2\n");
		
		mortenTasks = t2.get(Marshall.marshall(morten));
		for(Task task : mortenTasks) {
			System.out.println(Marshall.marshall(task));
		}
		System.out.println("I'm all done for now. See ya!");
		System.exit(0);
	}
}
