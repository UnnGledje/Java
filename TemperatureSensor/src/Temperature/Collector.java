package Temperature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Collector extends JFrame{
    JPanel panel = new JPanel();
    JTextArea text = new JTextArea(30, 20);

    Collector() throws IOException {
        this.add(panel);
        panel.add(text);
        this.pack();
        this.setLocation(300, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        int myPort= 5555;
        DatagramSocket socket = new DatagramSocket(myPort);
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
