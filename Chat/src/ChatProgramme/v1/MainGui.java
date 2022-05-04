
/* This is a chat using UDP through Multicast socket. I have created several panels to be able to use several
border layouts and then put them all together in one panel.  Scroll is used in every textarea.
Problems is that after implementing a KeyListener for the enter key to be used for sending and clearing the input
 message field it also reacts on any other button and send an error message on the private chat Pane.
 At the moment, you can only see yourself as online in the online bar, in your private chat-window.
 */

package ChatProgramme.v1;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.*;
import java.util.Objects;


public class MainGui extends JFrame implements ActionListener, KeyListener {
            //The Frame
    JFrame frame = new JFrame("Wazzup");
            // The Panels
    JPanel p, p1, p2, p3;
            //JButton send,connect,reset,disconnect;
            // send button
    JButton send = new JButton("Send");
            //reset button
    JButton reset = new JButton("Reset");
            // Exit chat
    JButton disconnect = new JButton("Disconnect");
            // Connect with username
    JButton connect = new JButton("Connect");
            //TextaAreas and their scrollPanes
            // The Area where message sare shown
    public JTextArea textArea = new JTextArea(31, 12);

    JScrollPane scrollTextArea = new JScrollPane(textArea);
            //Input field for writing messages
    JTextArea sendMes = new JTextArea("Enter text here", 15, 22);
    JScrollPane scrollSendMs = new JScrollPane(sendMes);
            //Showing who's online
    JTextArea online = new JTextArea("online now \n", 2, 8);
    JScrollPane scrollOnline = new JScrollPane(online);
            //Inputfield for username
    JTextArea nameField = new JTextArea("Enter username", 1, 1);
         //Creating a border that i can use for my text areas
    Border border = BorderFactory.createLineBorder(Color.BLACK);

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

            //Giving the Textareas some color
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

        sendMes.addKeyListener(this);

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

                //Making sure that the ScrollPane is vertical
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
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                /*The connect button shows other users when another user gets online, making username uneditable*/
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   // if (Objects.equals(nameField.getText(), "Enter username" )){
                        if(manageName(false)) {
                            //String s = JOptionPane.showInputDialog(null, "You must enter a username");
                            textArea.append("ERROR, Enter a username first! \n");
                        }
                   // }
                    else if(manageName(true))   //if (nameField.getText() != null) {
                    {
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

                // ends message if username is set and message is written
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(management(true)) {
                        sendMessage((nameField.getText() + ": " + " " + sendMes.getText()), IPAddress, port);
                    }
                    else{
                        management(false);
                    }
                                    //Made a method to be used instead
                                   /* if (Objects.equals(nameField.getText(), "Enter username")) {
                                        textArea.append("ERROR, Enter a username first! \n");
                                    } else if (Objects.equals(sendMes.getText(), "Enter text here")) {
                                        textArea.append("ERROR, No empty messages can be sent \n");
                                    } else if (Objects.equals(sendMes.getText(), "")) {
                                        textArea.append("ERROR, No empty messages can be sent \n");
                                    } else {
                                        sendMessage((nameField.getText() + ": " + " " + sendMes.getText()), IPAddress, port);
                                    }
                                    */

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
                   sendMessage(nameField.getText() + " is offline\n", IPAddress, port);
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

    /*
    public void sendName(String name, String IPAddress, int port) throws IOException {
        DatagramSocket s = new DatagramSocket();
        InetAddress toAdr = InetAddress.getByName(this.IPAddress);
        byte[] dataToSend = name.getBytes();
        DatagramPacket p = new DatagramPacket(dataToSend, dataToSend.length, toAdr, port);
        s.send(p);
        s.close();
    }
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

            //For the enter key
    @Override
    public void keyPressed(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(management(true)) {
                    sendMessage((nameField.getText() + ": " + " " + sendMes.getText()), IPAddress, port);
                    sendMes.setText("");
                    e.consume();
                }
            }
            else{
                management(false);
                e.consume();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
                //Method to handle that username is put in firts
    private boolean manageName(boolean b){
        if (Objects.equals(nameField.getText(), "Enter username")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        else if (Objects.equals(nameField.getText(), "")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        else if (Objects.equals(nameField.getText(), "\n")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        return b;
    }
                //Method to handle no input or wrong input before sending a message
    private boolean management(boolean b) {
        if (Objects.equals(nameField.getText(), "Enter username")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        else if (Objects.equals(nameField.getText(), "")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        else if (Objects.equals(nameField.getText(), "\n")) {
            b = false;
            textArea.append("ERROR, Enter a username first! \n");
        }
        else if (Objects.equals(sendMes.getText(), "Enter text here")) {
            b = false;
            textArea.append("ERROR, No empty messages can be sent \n");
        } else if (Objects.equals(sendMes.getText(), "")) {
            textArea.append("ERROR, No empty messages can be sent \n");
            b = false;
        } else if (Objects.equals(sendMes.getText(), "\n")) {
            textArea.append("ERROR, No empty messages can be sent \n");
            b = false;

        } else {
            b = true;
        }
        return b;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args){

        MainGui mg = new MainGui();
        Thread thread = new Thread(new Receive(mg.textArea));
        thread.start();

    }



}