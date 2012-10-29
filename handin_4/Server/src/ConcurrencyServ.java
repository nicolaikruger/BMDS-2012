import java.util.concurrent.*;
import java.util.Random;

public class ConcurrencyServ {
	BlockingQueue<Integer> q = new LinkedBlockingQueue<>(100);
	Random rnd = new Random();

	public ConcurrencyServ(){
		new WorkerThread(q).start();
		while(true) {
			try {
				synchronized(q) {
					q.put(rnd.nextInt());
				}
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
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
