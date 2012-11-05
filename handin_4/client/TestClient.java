public class TestClient {
	public static void main(String[] args) {
		Connection con = new Connection();
		String ack = con.clientSendToServer("Hrello?");
		System.out.println(ack);
	}
}
