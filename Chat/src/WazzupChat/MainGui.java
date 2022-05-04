
/* This is a chat using UDP through Multicast socket. I have created several panels to be able to use several
border layouts and then put them all together in one panel.  Scroll is used in every textarea.
Problems I'm facing are clearing the textarea after pressing enter, I understand that I need tou se a KeyListener and
a getKeyCode for VK_ENTER but, I realized this after writing the code, and now I don't  fully understand how to implement
it in a graceful manner. My other problem is the online bar. At the moment, you can only see yourself as online in
the online bar, in your private chat-window, I know this might not be something that I can do using multisocket but,
I believe there must be a better way of implementing an online bar visible for everyone than to just cut the message
string. If I do that I would face difficulties when taking away a username when they disconnect from the chat.
*/

package WazzupChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.net.*;
import java.util.Objects;

public class MainGui extends JFrame implements ActionListener, Runnable {

        //The Frame
    JFrame frame = new JFrame("Wazzup");
        // The Panels
    JPanel p, p1, p2, p3;

        //JButton send,connect,reset,disconnect;
    JButton send = new JButton("Send"); // send button
    JButton reset = new JButton("Reset"); //reset button
    JButton disconnect = new JButton("Disconnect"); // Exit chat
    JButton connect = new JButton("Connect");// Connect with username

        //TextaAreas and their scrollPanes
    JTextArea textArea = new JTextArea(31, 12);
    JScrollPane scrollTextArea = new JScrollPane(textArea);
    JTextArea sendMes = new JTextArea("Enter text here", 15, 22);
    JScrollPane scrollSendMs = new JScrollPane(sendMes);
    JTextArea online = new JTextArea("online now \n", 2, 8);
    JScrollPane scrollOnline = new JScrollPane(online);
    JTextArea nameField = new JTextArea("Enter username", 1, 1);
        //Creating a border that i can use for my text areas
    Border border = BorderFactory.createLineBorder(Color.BLACK);

   // String username;
   // String message;
        //The multicast address
    String IPAddress = "234.235.236.237";
        //The port
    int port = 12540;

    MainGui() {
        p = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

            //Defining layout
        p.setLayout(new BorderLayout());
        p1.setLayout(new BorderLayout());
        p2.setLayout(new BorderLayout());
        p3.setLayout(new GridLayout(2, 2));

            //Giving the panels color
        p.setOpaque(true);
        p1.setOpaque(true);
        p2.setOpaque(true);
        p3.setOpaque(true);
        p.setBackground(Color.GRAY);
        p1.setBackground(Color.GRAY);
        p2.setBackground(Color.GRAY);
        p3.setBackground(Color.GRAY);

                //Giving the Tectareas some color
        textArea.setOpaque(true);
        textArea.setBackground(Color.lightGray);
        online.setOpaque(true);
        online.setBackground(Color.lightGray);
        nameField.setOpaque(true);
        nameField.setBackground(Color.white);
        sendMes.setOpaque(true);
        sendMes.setBackground(Color.white);

                //Wrapping the lines so that they will fit window and switch line
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        online.setLineWrap(true);
        online.setWrapStyleWord(true);
        sendMes.setLineWrap(true);
        sendMes.setWrapStyleWord(true);

                //Giving the textarea black borders
        textArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        online.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        sendMes.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

                //Setting the View port for Scrollpane and setting the size to what I defined the textArea to be before
        scrollTextArea.setViewportView(textArea);
        scrollTextArea.getPreferredSize();
        scrollOnline.setViewportView(online);
        scrollOnline.getPreferredSize();
        scrollSendMs.setViewportView(sendMes);
        scrollSendMs.getPreferredSize();

                // making sure that the ScrollPane is vertical
        scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSendMs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOnline.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //Adding on all the components to the right panel
        p1.add(scrollTextArea, BorderLayout.CENTER);
        p1.add(scrollOnline, BorderLayout.EAST);
        p1.add(nameField, BorderLayout.SOUTH);

        p2.add(scrollSendMs, BorderLayout.WEST);

        p3.add(send);
        p3.add(reset);
        p3.add(connect);
        p3.add(disconnect);

                //Adding the panels to panel 'p'
        p.add(p1, BorderLayout.NORTH);
        p.add(p2, BorderLayout.CENTER);
        p.add(p3, BorderLayout.EAST);

        add(p, BorderLayout.CENTER);

                //Adding everything to frame, packing, sets size and making sure it can't be resized.
        frame.add(p);
        frame.pack();
        frame.setSize(500, 700); //Creating the panel at bottom and adding components
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                    /*The connect button shows other users when another user gets online, making username uneditable*/
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Objects.equals(nameField.getText(), "Enter username")) {
                        //String s = JOptionPane.showInputDialog(null, "You must enter a username");
                        textArea.append("ERROR, Enter a username first! \n");

                    } else if (nameField.getText() != null) {
                        sendMessage(nameField.getText() + " is online \n", IPAddress, port);
                        nameField.setEnabled(false);
                        nameField.setEditable(false);
                        connect.setEnabled(false);
                        online.append(nameField.getText() + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
                // Sends message if username is set and message is written
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Objects.equals(nameField.getText(), "Enter username")) {
                        textArea.append("ERROR, Enter a username first! \n");
                    } else if (Objects.equals(sendMes.getText(), "Enter text here")) {
                        textArea.append("ERROR, No empty messages can be sent \n");
                    } else if (Objects.equals(sendMes.getText(), "")) {
                        textArea.append("ERROR, No empty messages can be sent \n");
                    } else {
                       sendMessage((nameField.getText() + ": " + " " + sendMes.getText()), IPAddress, port);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
                    //Shows other users that a certain user is offline
        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage(nameField.getText() + " is offline", IPAddress, port);
                    //online.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
                //Clears the textarea
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMes.setText("");
                //textfield.setText(null); //or use this
            }
        });
    }

            /* The method that sends the message, packs the message into a packet*/
    public void sendMessage(String message, String IPAddress, int port) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress toAdr = InetAddress.getByName(this.IPAddress);
        byte[] dataToSend = message.getBytes();
        DatagramPacket packet = new DatagramPacket(dataToSend, dataToSend.length, toAdr, port);
        socket.send(packet);
        socket.close();
    }
                /*Method to receive a message through the packet and send it to the multicast address */
    public void receiveMessage(String IPAddress, int port) throws IOException {
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
            //this.sideArea.append(nameField.getText() + "\n");
            this.textArea.append(message + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
        //Implementing threading for the message so that it can reach all users
    @Override
    public void run() {
        try {
            receiveMessage(IPAddress, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
