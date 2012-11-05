package server;
import java.util.concurrent.*;
import java.util.List;
import java.util.Random;
import java.net.DatagramPacket;
import java.io.*;
import lib.*;
import server.*;

public class ConcurrencyServ {
	BlockingQueue<Task> q = new LinkedBlockingQueue<>(100); //arbitrary limit FTW
	Connection con = new Connection(true);
	private String xmlStore; 
	private Calendar cal;

	public ConcurrencyServ(String xmlStorePath){
		xmlStore=xmlStorePath;
		cal = initRead();
		new WorkerThread(q, cal.getList()).start();
		listen();
	}

	private void listen(){
		while(true) {
			String req = con.receive();
			System.out.println("Server got:" + req);
			Task t = cal.getTask(req);
			if (t == null) {
				con.respond(req, "No task with id: " + req);
				return;
			}
			try {
				q.put(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Calendar initRead() {
		Calendar ret = null;
		try {
			File f = new File(xmlStore);
			FileInputStream fis = new FileInputStream(f);
			ret = Marshall.unMarshall(fis, Calendar.class);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	public static void main(String[] args) {
		new ConcurrencyServ(args[0]);
	}

	private class WorkerThread extends Thread{
	BlockingQueue<Task> workLoad;
	List<Task> taskL;

		public WorkerThread(BlockingQueue<Task> q, List<Task> tl) {
			workLoad = q;
			taskL = tl;
		}

		public void run() {
			while (true) {
				Task t = null;
				try {
					t = workLoad.take();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				
				if (conditionsMet(t)) {
					execute(t);
				} else {
					try {
						q.put(t);
					}catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					System.out.println("Could not execute " + t.getId());
				}
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}

		private void execute(Task t){
			t.setStatus("executed");
			for (String s : t.getResponses()) {
				Task resp =  getTaskFromId(s);
				resp.setRequired(true);
			}
			System.out.println("Successfully executed " + t.getId());
			answer(t);
		}

		private void answer(Task t) {
			con.respond(t.getId(), t.getId() + " done");
		}

		private boolean conditionsMet(Task t) {
			for(String s : t.getConditions()){
				Task c = getTaskFromId(s);
				if (c.getStat().equals("not-executed")){
					return false;
				}
			}
			return true;
		}

		private Task getTaskFromId(String id) {
			for (Task t : taskL) {
				if (t.getId().equals(id)) {
					return t;
				}
			}
			throw new RuntimeException("No task with id: " + id + " known.");
		}

	}
}
//lars
