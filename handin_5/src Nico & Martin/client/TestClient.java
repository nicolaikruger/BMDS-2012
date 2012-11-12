package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.*;

public class TestClient {

    public static void main(String[] args) {
        Connection con = new Connection(5555);
        String username, password;
        System.out.print("Enter username: ");
        username = System.console().readLine();
        System.out.print("Enter password: ");
        char[] tmpPass = System.console().readPassword();
        password = new String(tmpPass);
        // Password should be encrypted with Token key
        //System.out.println("U/N: " + username + "\tPass: " + password);
        try {
            con.send(username + "," + password, "localhost", 7777);
            String returnsMsg = con.receive();
            System.out.println(returnsMsg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
