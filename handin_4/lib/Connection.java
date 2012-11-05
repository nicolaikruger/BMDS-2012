/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MrGreen
 */
public class Connection {

    private final int SERVERPORT = 6000;
    private final String DEFAULTSERVERADDRESS = "localhost";
    private DatagramSocket socket;
    private boolean isServer;
    private Map<String, List<DatagramPacket>> packetByMessage;

    /**
     *
     * @param isServer specify if this object represents is associated with a
     * server (true) or a client (false)
     */
    public Connection(boolean isServer) {

        try {
            if (isServer) {
                socket = new DatagramSocket(SERVERPORT);
            } else {
                socket = new DatagramSocket();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.isServer = isServer;
        packetByMessage = new HashMap<>();
    }

    /**
     * Listen for incoming message. Used by both server and client. Note: This
     * is a blocking call
     *
     * @return received message
     */
    public String receive() {
        byte[] receiveData = new byte[256];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String receiveMessage = new String(receivePacket.getData());

        List<DatagramPacket> associatedPackets = packetByMessage.get(receiveMessage);
        if (associatedPackets == null) {
            associatedPackets = new LinkedList<>();
        }
        associatedPackets.add(receivePacket);
        packetByMessage.put(receiveMessage, associatedPackets);
        return receiveMessage;
    }

    /**
     * Respond to a message
     *
     * @param originalMessage The message originally received
     * @param response The response
     */
    public void respond(String originalMessage, String response) {
        List<DatagramPacket> associatedPackets = packetByMessage.get(originalMessage);
        if (associatedPackets != null) {
            for (DatagramPacket packet : associatedPackets) {
                InetAddress IPAddress = packet.getAddress();
                int port = packet.getPort();
                try {
                    send(response, IPAddress, port);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            packetByMessage.put(originalMessage, null);
        }
    }
        
    /**
     * Send a message to the server. If this object is a server, this method has
     * no effect
     *
     * Note: This method assumes the server is run locally
     *
     * @param message to the server
     */
    public void send(String message) {
        if (!isServer) {
            try {
                InetAddress IPAddress = InetAddress.getByName(DEFAULTSERVERADDRESS);
                send(message, IPAddress, SERVERPORT);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Send a message to the server. If this object is a server, this method has
     * no effect
     *
     * @param message to the server
     */
    public void send(String message, String serverAddress) {
        if (!isServer) {
            try {
                InetAddress IPAddress = InetAddress.getByName(serverAddress);
                send(message, IPAddress, SERVERPORT);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Send a message to another process with the specified address and port
     * 
     * @param message The message to send
     * @param IPAddress The address of the end point process
     * @param port The port associated with the end point process
     * @throws IOException If an I/O error occurs.
     */
    private void send(String message, InetAddress IPAddress, int port) throws IOException {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        socket.send(sendPacket);
    }
}