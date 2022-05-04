package ServerTCP;

import ClientTCP.Client;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler extends Thread implements Serializable {
    //private final Server server;
    private final Socket clientSocket;
    private List<ServerHandler> usernameList;

    //PrintWriter out;
    ObjectOutputStream out;
    Client client;

    public ServerHandler(Socket clientSocket, List<ServerHandler> usernameList) throws IOException {
        this.usernameList = usernameList;
        this.clientSocket = clientSocket;
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        client = new Client();
    }

    public void run() {

        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());) {

            while(clientSocket.isConnected()) {
                client = (Client) in.readObject();
                for (ServerHandler user : usernameList) {
                    user.out.writeObject(this.client);
                    user.out.reset();
                }
                String exit = client.getMessage();
                System.out.println("receiving object "+client.getMessage() +" "+client.getAlias());
                if (exit.contains("offline")) {
                    System.out.println("removing "+client.getAlias());
                    usernameList.remove(this);
                }
            }
            } catch (IOException ex) {
            System.out.println(Thread.currentThread().toString());
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}


