package ServerTCP;
import java.io.*;
import java.net.*;
import java.util.*;

class Server extends Thread{
    private List<ServerHandler> usernameList = new ArrayList<>();
    private int port;

    public Server(int port){
        this.port = port;
    }

    public void run() {

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(6666)) {
                System.out.println("Trying to connect");
                final Socket clientSocket = serverSocket.accept();
                System.out.println("Connected " + clientSocket  + " " + serverSocket);
                ServerHandler clientHandler = new ServerHandler(clientSocket, usernameList);
                usernameList.add(clientHandler);

                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }

        }

    }
/*
  public void removeUser(ServerHandler clientHandler){
            usernameList.remove(handler);
        }
 */
    public static void main (String argv[]){
        Server server = new Server(6666);
        server.start();

    }

}


