package MedecineProgramme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Medecine implements Runnable{
    public Thread thread = new Thread(this);
    String text;
    long interval = 0;

    public Medecine(String text, long times){
        this.text = text;
        this.interval = interval/60000;
    }

    @Override
    public void run() {
        while(!(Thread.interrupted())){
            try {
                Thread.sleep(interval);
                System.out.println("Take " + text);
            } catch (InterruptedException e) {
                break;
                //e.printStackTrace();
            }
        }
    }
}



