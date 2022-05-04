package SLASKEN;

public class Slask {
    /* Socket connectionSocket;
    private int serverPort;

    //public static Vector clients=new Vector(); // Should Be list
    private ArrayList<ServerHandler> usernameList = new ArrayList<>();

    public Server(int serverPort){
        this.serverPort = serverPort;

    }
    public void run(){
        try{
            //BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            ServerSocket serverSocket = new ServerSocket(12345);
                /*ServerHandler user = new ServerHandler(this, connectionSocket);
                usernameList.add(user);
                user.start();


            while(true){
                System.out.println("Trying to connect");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected" + clientSocket);

                ServerHandler serverHandler = new ServerHandler(this, clientSocket);
                //serverHandler.socketHandler();
                //ServerHandler serverHandler = new ServerHandler();
                //Server server = new Server(clientSocket);
                usernameList.add(serverHandler);
                System.out.println(serverHandler);

                Thread serverThread = new Thread(serverHandler);
                serverThread.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = reader.readLine().trim();
                System.out.println("Received : " + data);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                writer.write("Blah");
                writer.flush();
                for (int i=0;i<usernameList.size();i++){
                    try{
                        BufferedWriter bw= (BufferedWriter) usernameList.get(i);
                        bw.write(data1);
                        bw.write("\r\n");
                        bw.flush();
                    }catch(Exception e){e.printStackTrace();}
                }

            }
        }catch(Exception e){e.printStackTrace();}

    }

    */
 /* while(true){
            try (ServerSocket serverSocket = new ServerSocket(12345)){
                final Socket clientSocket = serverSocket.accept();

                ServerHandler clientHandler = new ServerHandler(clientSocket);
                usernameList.add(clientHandler);
                clientHandler.start();
            }

        */


    //Server server = new Server(12346);
    //server.start();


    //System.out.println("Threaded Chat Server is Running  " );
    //ServerSocket mysocket = new ServerSocket(5555);
        /*while(true){
            Socket sock = mysocket.accept();
            Server server=new Server(sock);
            Thread serverThread=new Thread(server);
            serverThread.start();
        }*/

    /*void socketHandler() throws IOException {
        InputStream inputStream = clientSocket.getInputStream(); //objectinput,
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        while (true) {
            //String data1 = reader.readLine().trim();
            String message = "Incoming : " + read.readLine().trim() + "\n";
            System.out.println("Received : " + message);
            outputStream.write(message.getBytes());
        }
    }

    @Override
    public void run() {
        try {
            socketHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */


    // writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    // reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

 /*String input = LoginField.getText();
                    input = input.replace("\n", "");
                    sendMessage(input + ": is online
                    This was to not detect the newline but it continued to be sent with the message
                     */
    //sendMessage(LoginField.getText() + ": is online \n");

    //client.setAlias(LoginField.getText());
    //sendMessage(client);

     /*if (!LoginField.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(InputField, "Enter a username");
                }else{
                    try {
                        sendMessage(LoginField.getText() + " is online \n");
                        usernameArea.append(LoginField.getText());
                        LoginField.setEnabled(false);
                        LoginField.setEditable(false);
                        Connect.setEnabled(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }

                 */
    //sendMessage(client + ": is offline\n");
    //sendMessage(LoginField.getText() + ": is offline\n");

    //sendMessage(LoginField.getText() + ": " + InputField.getText() + "\n");
    //InputField.setText("");

    /*sendMessage(InputField.getText());
                    InputField.setText("InPut: ");
                    e.consume();

                     */

    //client = (Client) in.readObject();
    //while ((inputLine = in.readObject()) != null) {
    //while(true) { !Objects.equals(client.getMessage(), "")
    // while(in.readObject() instanceof Client) {
    // inputLine = in.readLine();
    //System.out.println("in server, received " + inputLine);
    //user.out.println(inputLine);
    // writer.writeObject(client);

    //client.setMessage("");

                    /*String serverMsg = "";
                    while ((serverMsg = reader.readLine()) != null) { //readObject --> ObjectOutputStream
                        System.out.println("from server: " + serverMsg);
                        textArea1.append(serverMsg + "\n");
                }
                     */

    // PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    //ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

    //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    //String inputLine;
    // out.println("Client online");
    //while ((inputLine = in.readLine()) != null) {//(inputLine = in.readLine()) != null){

    // while ((inputLine = in.readObject()) != null) {
    //while(true) {
    //while (in.readObject() instanceof Client){ // inputLine = in.readLine();

    //System.out.println("in server, received " + inputLine);
    //user.out.println(inputLine);

    //out = new PrintWriter(clientSocket.getOutputStream(), true);

}
