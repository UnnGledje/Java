package ClientTCP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class MainGui extends JFrame implements Runnable{
    private JPanel contentPane;
    private JButton Connect;
    private JButton Disconnect;
    private JTextArea textArea1;
    private JTextArea LoginField;
    private JTextArea InputField;
    private JButton Send;
    private JTextArea usernameArea;
    Client client = new Client();
    Socket clientSocket = new Socket("localhost", 6666);
    ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

    public MainGui() throws IOException {

        setContentPane(contentPane);
        setTitle("TCP-Chat");

        pack();
        setVisible(true);

        Connect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!LoginField.getText().trim().equalsIgnoreCase("")){
                    usernameArea.append(LoginField.getText()+ "\n");
                    LoginField.setEnabled(false);
                    LoginField.setEditable(false);
                    Connect.setEnabled(false);
                }
                    else if(Objects.equals(LoginField.getText(), "")){
                JOptionPane.showMessageDialog(LoginField, "Enter a username first");
            }
                }
        });

        Disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.setMessage(LoginField.getText() + " is offline.\n");
                    writer.writeObject(client);
                    writer.reset();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);

            }
        });
        InputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

               try{
                   if(e.getKeyCode() == KeyEvent.VK_ENTER){
                       if(Objects.equals(LoginField.getText(), "")){
                           JOptionPane.showMessageDialog(InputField, "Enter a username first");
                       }
                       else if(!InputField.getText().trim().equalsIgnoreCase("")){
                           sendMessage(client);
                           e.consume();
                       }
                       else if(Objects.equals(InputField.getText(), "")){
                           JOptionPane.showMessageDialog(InputField, "Enter a message");
                       }
                   }
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
            }
        });
        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(Objects.equals(LoginField.getText(), "")){
                        JOptionPane.showMessageDialog(InputField, "Enter a username first");
                    }
                    else if(!InputField.getText().trim().equalsIgnoreCase("")){
                        sendMessage(client);
                    }
                    else if(Objects.equals(InputField.getText(), "")){
                        JOptionPane.showMessageDialog(InputField, "Enter a message");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        });

        LoginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                        //method to prevent newline
                    e.consume();
                }
            }
        });
    }

    private void userDisconnect(Client client) throws IOException {
        client.setMessage(LoginField.getText() + " is offline.\n");
        writer.writeObject(client);
        writer.reset();
    }

    private void sendMessage(Client client) throws IOException {
        client.setAlias((LoginField.getText()));
        client.setMessage(InputField.getText());
        writer.writeObject(client);
        writer.reset();
        InputField.setText("");
    }

    private boolean online(String alias) {
        for (String line : usernameArea.getText().split("\\n")) {
            if (Objects.equals(alias, line)) {
                return true;
            }
        }
        return false;
    }

    public void run() {

        try {
            while (true) {
                client = (Client) in.readObject();
                textArea1.append(client.getAlias() + ": " + client.getMessage() + "\n");
                if (!online(client.getAlias()))
                    usernameArea.append(client.getAlias() + "\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
        public static void main (String[] args) throws IOException {
            MainGui mainGui = new MainGui();
            Thread thread = new Thread(mainGui);
            thread.start();

        }
    }

