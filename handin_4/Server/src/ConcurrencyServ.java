import java.util.concurrent.*;
import java.util.Random;
import java.net.DatagramPacket;
import java.io.*;

public class ConcurrencyServ {
	BlockingQueue<Integer> q = new LinkedBlockingQueue<>(100);
	Connection con = new Connection();
	Random rnd = new Random();

	public ConcurrencyServ(){
		new WorkerThread(q).start();
	//	while(true) {
	//		try {
	//			synchronized(q) {
	//				q.put(rnd.nextInt());
	//			}
	//		}catch (InterruptedException e){
	//			e.printStackTrace();
	//		}
	//	}
		listen();
	}

	private void listen(){
		DatagramPacket d = con.serverReceiveFromClient();
		String syn = null;
		try {
			syn = (String) new ObjectInputStream(new ByteArrayInputStream(d.getData())).readObject();
		} catch (IOException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		System.out.println(syn);
		con.serverSendToClient("Hi, i'm a server. Who are you", d);
	}

	public static void main(String[] args) {
		new ConcurrencyServ();
	}

	private class WorkerThread extends Thread{
	BlockingQueue<Integer> workLoad;

		public WorkerThread(BlockingQueue<Integer> q) {
			workLoad = q;
		}

		public void run() {
			while (true) {
				try {
					System.out.println(workLoad.take() + "\t" + workLoad.size());
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
}
//lars
