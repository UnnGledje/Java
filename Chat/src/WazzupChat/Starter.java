package WazzupChat;

public class Starter {

    public static void main(String[] args){

        Thread thread = new Thread(new MainGui());
        thread.start();
    }
}
