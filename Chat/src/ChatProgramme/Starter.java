package ChatProgramme;

import javax.swing.*;

public class Starter {

    public static void main(String[] args){
        Thread thread = new Thread(new MainGUI());
        thread.start();
    }
}
