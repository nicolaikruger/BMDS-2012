/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class Connection {

    public static DatagramSocket socket;
    
    public Connection() {
        if(socket == null){
            try {
                socket = new DatagramSocket(4445);
            } catch (SocketException e) {
                e.getStackTrace();
            }
        }
    }
    
    /**   
     * Wait for a request from a client 
     * @param datagrampacket from a client
     */
    public DatagramPacket serverReceiveFromClient() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return packet;
    }
    
    /**
     * Send a message to a client
     * @param message to the client
     * @param packet originally received from client
     */
    public void serverSendToClient(String message, DatagramPacket packet)
    {
      byte[] buf = message.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
        try {
            socket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Send a message to the server
     * @param message to the server
     * @return response from server
     */
    public String clientSendToServer(String message){
        byte[] buff = new byte[message.getBytes().length];
                
        try {
            DatagramPacket serverPacket = new DatagramPacket(buff, buff.length, socket.getLocalSocketAddress());
            socket.send(serverPacket);        
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }   
        String response = "";
        try {
            DatagramSocket receiveSocket = new DatagramSocket();
            buff = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(buff, buff.length);
            receiveSocket.receive(receivePacket);
            response = (String) new ObjectInputStream(new ByteArrayInputStream(receivePacket.getData())).readObject();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public static void main(String[] args)
    {
       
    }
}
