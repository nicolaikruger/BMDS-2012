package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.*;

public class TestClient {

    private String authenticate() {
        Connection con = new Connection(5555);
        String username, password, key;
        System.out.print("Enter username: ");
        username = System.console().readLine();
        System.out.print("Enter password: ");
        char[] tmpPass = System.console().readPassword();
        password = new String(tmpPass);
	System.out.println("Enter secret: ");
	key = System.console().readLine();

	//encrypt password with shared key
	Encryption e = new Encryption();
	password = e.encrypt(key, password);

	String returnMsg = null;
        try {
	    System.out.println(username + "," + password);
            con.send(username + "," + password, "localhost", 7777);
	    String resvdMsg = con.receive();
	    if(resvdMsg.equalsIgnoreCase("Error: Could not authenticate!")){
		System.out.println(resvdMsg);
		System.exit(1);
	    }
            returnMsg = e.decrypt(key, resvdMsg);
            System.out.println(returnMsg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	con.close();
	return returnMsg;
    }

    private String piggyB(String token) {
	System.out.print("id of assignment to do: ");
	return System.console().readLine() + "," + token;
    }

    private void send(String msg) {
	Connection con = new Connection(5555);
	con.send(msg, "localhost", 6666);
	con.close();
    }

    public static void main(String[] args){
        TestClient tc = new TestClient();

        String token = tc.authenticate();
	String msg = tc.piggyB(token);
	tc.send(msg);
    }
}
