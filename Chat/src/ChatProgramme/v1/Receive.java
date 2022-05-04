package ChatProgramme.v1;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Receive implements Runnable {
    JTextArea textArea;

        //The Multicast address
    String IPAddress = "234.235.236.237";
            //The port
    int port = 12540;

    public Receive(JTextArea textArea){
        this.textArea = textArea;
    }

    public void receiveMessage() throws IOException {
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress mcAddress = InetAddress.getByName(IPAddress);
        InetSocketAddress group = new InetSocketAddress(mcAddress, port);
        NetworkInterface ifFace = NetworkInterface.getByName("wlp3s0"); //wlan2,
        socket.joinGroup(group, ifFace);
        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while (true) {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            this.textArea.append(message + "\n");
        }
    }


            //Implementing threading for the message so that it can reach all users
    @Override
    public void run() {
        try {
            receiveMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
