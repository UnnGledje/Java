/* This is a chat using UDP through */

package ChatProgramme.v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.net.*;
import java.util.Objects;

public class MainGUI extends JFrame implements ActionListener, Runnable {

        JFrame frame = new JFrame("Wazzup");
        JPanel p,p1,p2,p3,p4;
        JLabel l1,l2,l3,l4,l5,l6;

        //JButton send,connect,reset,exit;
        JButton send = new JButton("Send"); // send button
        JButton reset = new JButton("Reset"); //reset button
        JButton disconnect = new JButton("Disconnect"); // Exit chat
        JButton connect = new JButton("Connect");// Connect with username

        //JTextArea chatRoom,textField,nameField,ta3;
        JTextArea textArea = new JTextArea(31, 12);
        //JScrollPane scroll = new JScrollPane(textArea);
        //JScrollPane scrollTextArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollPane scrollTextArea = new JScrollPane(textArea);
        JTextArea sendMes = new JTextArea("Enter text here", 15, 22);
        JScrollPane scrollSendMs = new JScrollPane(sendMes);
        JTextArea online = new JTextArea("online now \n", 2, 8);
        JScrollPane scrollOnline = new JScrollPane(online);

        JTextArea nameField = new JTextArea("Enter username", 1, 1);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JTextField tf,tf1,tf2,tf3;
        String username;
        String message;
        String IPAddress = "234.235.236.237";
        int port = 12540;

        MainGUI(){
            p=new JPanel();
            p1=new JPanel();
            p2=new JPanel();
            p3=new JPanel();
            p4=new JPanel();

            p.setLayout(new BorderLayout());
            p1.setLayout(new BorderLayout());
            p2.setLayout(new BorderLayout());
            p3.setLayout(new GridLayout(2, 2));

            p.setOpaque(true);
            p1.setOpaque(true);
            p2.setOpaque(true);
            p3.setOpaque(true);
            p.setBackground(Color.GRAY);
            p1.setBackground(Color.GRAY);
            p2.setBackground(Color.GRAY);
            p3.setBackground(Color.GRAY);

            /*connect.setFont(new Font("Serif", Font.PLAIN, 12));
            send.setFont(new Font("Serif", Font.PLAIN, 12));
            reset.setFont(new Font("Serif", Font.PLAIN, 12));
            disconnect.setFont(new Font("Serif", Font.PLAIN, 12));
             */

            textArea.setOpaque(true);
            textArea.setBackground(Color.lightGray);
            online.setOpaque(true);
            online.setBackground(Color.lightGray);
            nameField.setOpaque(true);
            nameField.setBackground(Color.white);
            sendMes.setOpaque(true);
            sendMes.setBackground(Color.white);

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            online.setLineWrap(false);
            online.setWrapStyleWord(false);
            sendMes.setLineWrap(true);
            sendMes.setWrapStyleWord(true);

            textArea.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            online.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            sendMes.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            //p3.setBorder(BorderFactory.createCompoundBorder(border,
                    //BorderFactory.createEmptyBorder(30, 30, 30, 30)));

            scrollTextArea.setViewportView(textArea);
            scrollTextArea.getPreferredSize();
            scrollOnline.setViewportView(online);
            scrollOnline.getPreferredSize();
            scrollSendMs.setViewportView(sendMes);
            scrollSendMs.getPreferredSize();

            scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollSendMs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollOnline.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

           // scroll.setPreferredSize(new Dimension(250, 500));
           // scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            //textArea.setPreferredSize(new Dimension(45, 11));
            //scrollPane.setPreferredSize(new Dimension(45, 11));
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //add(scrollPane, BorderLayout.WEST);
            //textArea.getPreferredScrollableViewportSize();
            //textArea.setBounds(1,1,20,29);
           // p1.add(textArea,BorderLayout.CENTER);
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //p1.add(scrollPane, BorderLayout.WEST);

            p1.add(scrollTextArea, BorderLayout.CENTER);
            p1.add(scrollOnline, BorderLayout.EAST);
            p1.add(nameField, BorderLayout.SOUTH);

            p2.add(scrollSendMs, BorderLayout.WEST);

            p3.add(send);
            p3.add(reset);
            p3.add(connect);
            p3.add(disconnect);

            p.add(p1,BorderLayout.NORTH);
            p.add(p2,BorderLayout.CENTER);
            p.add(p3,BorderLayout.EAST);

            add(p,BorderLayout.CENTER);

            frame.add(p);
            frame.pack();
            frame.setSize(500, 700); //Creating the panel at bottom and adding components
            frame.setVisible(true);
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                connect.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            if(Objects.equals(nameField.getText(), "Enter username")) {
                                //String s = JOptionPane.showInputDialog(null, "You must enter a username");
                                textArea.append("ERROR, Enter a username first! \n");

                            }
                            else if(nameField.getText() != null) {
                                sendMessage(nameField.getText() + " is online \n", IPAddress, port);
                                nameField.setEnabled(false);
                                nameField.setEditable(false);
                                connect.setEnabled(false);
                                //username = nameField.getText();
                                //online.append(username);
                                online.append(nameField.getText() + "\n");
                                //sendMessage(username, IPAddress, port);
                                // online.add(nameField.getText());

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
                            if(Objects.equals(nameField.getText(), "Enter username")) {
                                //String s = JOptionPane.showInputDialog(null, "You must enter a username");
                                textArea.append("ERROR, Enter a username first! \n");
                            }
                            else if(Objects.equals(sendMes.getText(), "Enter text here")){
                                sendMes.append("ERROR, No empty messages can be sent \n");
                            }
                            else if(Objects.equals(sendMes.getText(), "")){
                                sendMes.append("ERROR, No empty messages can be sent \n");
                            }
                            else if(Objects.equals(sendMes.getText(), "ERROR, No empty messages can be sent")){
                                sendMes.append("ERROR, input message to send\n");
                            }
                            else if(Objects.equals(sendMes.getText(), "ERROR, input message to send")){
                                sendMes.append("ERROR, input message to send \n");
                            }
                            else {
                                sendMessage((nameField.getText()+": " + " " + sendMes.getText()), IPAddress, port);
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                disconnect.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            sendMessage(nameField.getText() + " is offline", IPAddress, port);
                            online.setText("");

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


        public String sendMessage(String message, String IPAddress, int port) throws IOException {

            DatagramSocket socket = new DatagramSocket();
            InetAddress toAdr = InetAddress.getByName(this.IPAddress);
            byte[] dataToSend = message.getBytes();
            DatagramPacket packet = new DatagramPacket(dataToSend, dataToSend.length, toAdr, port);
            socket.send(packet);
            socket.close();
            return message;
        }

        public void receiveMessage(String IPAddress, int port) throws IOException {
            MulticastSocket socket = new MulticastSocket(port);
            InetAddress mcAddress = InetAddress.getByName(IPAddress);
            InetSocketAddress group = new InetSocketAddress(mcAddress, port);
            NetworkInterface ifFace = NetworkInterface.getByName("wlp3s0"); //wlan2,
            socket.joinGroup(group, ifFace);
            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            while (true) {
                //this.textArea.append("Incoming message: \n");
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

    public static void main(String[] args){
        Thread thread = new Thread(new MainGUI());
        thread.start();


    }
    }



