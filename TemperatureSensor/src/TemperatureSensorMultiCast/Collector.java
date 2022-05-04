package TemperatureSensorMultiCast;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Collector extends JFrame{
    JPanel panel = new JPanel();
    JTextArea text = new JTextArea(30, 20);

    Collector() throws IOException { // Bygg ut collector med Send button, ska skicka meddelanden till Snesor som printar ut i ett extra fönster, Collector startas i sensor
        this.add(panel);
        panel.add(text);
        this.pack();
        this.setLocation(300, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        int myPort= 5555;
       // DatagramSocket socket = new DatagramSocket(myPort);
        MulticastSocket socket = new MulticastSocket(myPort);
        InetAddress mcAddress = InetAddress.getByName("239.0.1.2");
        InetSocketAddress group = new InetSocketAddress(mcAddress, myPort);
        NetworkInterface ifFace = NetworkInterface.getByName("wlp3s0");
        socket.joinGroup(group, ifFace);

        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while(true){
            socket.receive(packet);
            String message = new String(packet.getData(),0, packet.getLength());
            text.setText(text.getText() + " " + message + "\n");
        }

    }

    public static void main(String[] args) throws IOException, SocketException{
        Collector c = new Collector();
    }

}
