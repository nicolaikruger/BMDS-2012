package client;
import lib.*;

public class TestClient {
	public static void main(String[] args) {
		Connection con = new Connection(false);
		con.send(args[0]);
		System.out.println(con.receive());
	}
}
