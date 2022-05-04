package Temperature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class Sensor extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JTextField text= new JTextField(20);
    JButton button = new JButton("Send");
    String city;
    String temperature = "";
    String dataToSend = "";
    InetAddress toAdr = InetAddress.getLocalHost();
    int toPort = 5555;
    DatagramSocket socket = new DatagramSocket();

    public Sensor(String city) throws UnknownHostException, SocketException , IOException, InterruptedException {
        this.add(panel);
        panel.add(text);
        panel.add(button);
        button.addActionListener((ActionListener)this);
        this.setTitle(city);
        this.city = city;
        this.pack();
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        temperature = text.getText();
        dataToSend = city + " " + temperature;
        byte[] data = dataToSend.getBytes();
        System.out.println(temperature);
        DatagramPacket packet = new DatagramPacket(data, data.length, toAdr, toPort);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        text.setText("");
        System.out.println("Im here");
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Sensor s = new Sensor(JOptionPane.showInputDialog(null, "City"));

    }
}