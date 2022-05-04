package ChatProgramme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class MainGUI extends JFrame implements ActionListener, Runnable {
    JFrame frame = new JFrame("Wazzup");
    JPanel panel = new JPanel(); // the panel is not visible in output
   // JLabel nameLabel = new JLabel("Enter username:");
    JTextArea nameField = new JTextArea("Enter a username");
    JTextArea inputName = new JTextArea();
    //JLabel label = new JLabel("Enter Text");
    //JTextField textField = new JTextField(1000); // accepts upto 1000characters
    JButton send = new JButton("Send"); // send button
    JButton reset = new JButton("Reset"); //reset button
    JButton exit = new JButton("Exit"); // Exit chat

    JButton connect = new JButton("Connect");// Connect with username
    JTextArea textArea = new JTextArea();
    JTextArea sendMes = new JTextArea("Enter text here", 10, 10);
    JTextArea sideArea = new JTextArea(2, 8);
    JScrollPane scroll = new JScrollPane();
    String username;
    String message;
    String IPAddress = "234.235.236.237";
    int port = 12540;


    MainGUI() {

        panel.setLayout(new GridLayout(3, 2));
        frame.add(panel);

        /*nameLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.white);
        panel.add(nameLabel);

         */

        panel.add(nameField);

        connect.setFont(new Font("Serif", Font.PLAIN, 12));
        panel.add(connect);

        /*
        label.setFont(new Font("Serif", Font.PLAIN, 12));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        panel.add(label);

        panel.add(textField);
         */

        send.setFont(new Font("Serif", Font.PLAIN, 12));
        panel.add(send);

        reset.setFont(new Font("Serif", Font.PLAIN, 12));
        panel.add(reset);


        exit.setFont(new Font("Serif", Font.PLAIN, 12));
        panel.add(exit);

        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        //frame.add(scroll);
        //panel.add(scroll);


        //frame.add(scroll);
        //Adding Components to the frame.
        //frame.getContentPane().add(BorderLayout.WEST, scroll);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.WEST, sendMes);
        //scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        //frame.getContentPane().add(BorderLayout.NORTH, scroll);
        frame.getContentPane().add(BorderLayout.EAST, sideArea);
        textArea.setOpaque(true);
        textArea.setBackground(Color.pink);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);

        frame.pack();
        frame.setSize(500, 500); //Creating the panel at bottom and adding components
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(nameField.getText() == "Enter username") {
                        //String s = JOptionPane.showInputDialog(null, "You must enter a username");
                        nameField.setText("Enter a username first!");
                    }
                    else if(nameField.getText() != null) {
                        sendMessage(nameField.getText() + " is online", IPAddress, port);
                        nameField.setEnabled(false);
                        nameField.setEditable(false);
                        connect.setEnabled(false);
                        //sendMessage(nameField.getText() + "\n", IPAddress, port);
                        //nameField.append(String.valueOf(sideArea));
                       /// sideArea.append(nameField.getText());
                        username = nameField.getText();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                try{

                        sendMessage((username + " " + sendMes.getText()), IPAddress, port);

                } catch (IOException ex) {
                  ex.printStackTrace();
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage(nameField.getText() + " is offline", IPAddress, port);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sendMes.setText("");
                //textfield.setText(null); //or use this
                }
        });
    }


    public void sendMessage(String message, String IPAddress, int port) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress toAdr = InetAddress.getByName(this.IPAddress);
        byte[] dataToSend = message.getBytes();
        DatagramPacket packet = new DatagramPacket(dataToSend, dataToSend.length, toAdr, port);
        socket.send(packet);
        socket.close();
    }

    public void receiveMessage(String IPAddress, int port) throws IOException {
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress mcAddress = InetAddress.getByName(IPAddress);
        InetSocketAddress group = new InetSocketAddress(mcAddress, port);
        NetworkInterface ifFace = NetworkInterface.getByName("wlp3s0");
        socket.joinGroup(group, ifFace);
        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while (true) {
            this.textArea.append("Incoming message: \n");
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            //ta.setText(ta.getText() + nameLabel + " " + message + "\n");
            //this.sideArea.append(nameField.getText() + "\n");
            this.textArea.append(message + "\n");
            //this.ta.append(tf.getText() + "\n");
        }
    }


    @Override
    public void run() {
        try {
            receiveMessage(IPAddress, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
