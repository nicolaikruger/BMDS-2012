package dk.itu.bmds.teamkolera.src.lib;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Connection {

	DataOutputStream out; 
	DataInputStream in;

	/**
	 * constructor for client socket
	 * @param addr ip address of the server to connect to
	 * @param port port of the server to connect to
	 */
	public Connection(String addr, int port) {
		try {
			Socket sock = new Socket(addr, port);
			out = new DataOutputStream(sock.getOutputStream());
			in= new DataInputStream(sock.getInputStream());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * constructor for server socket
	 * @param port port of the server to connect to
	 */
	public Connection(int port) {
		try {
			ServerSocket servSock = new ServerSocket(port);
			Socket sock = servSock.accept();
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void writeString(String message){
		try {
			out.writeUTF(message);
			out.flush();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readString() {
		String ret=null;
		try{
			ret = in.readUTF();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	public void kill(){
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Connection c = new Connection("127.0.0.1", 4444);
		c.writeString("Ring, Ring");
		c.writeString("lars");
		c.kill();
	}

}
