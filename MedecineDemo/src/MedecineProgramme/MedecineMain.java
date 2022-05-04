package MedecineProgramme;

import javax.swing.*;
import java.util.Scanner;

public class MedecineMain {

    public static void main(String[] args){
        Scanner sc;
        long times = 0;

        while(true){
            String medicine = JOptionPane.showInputDialog(null, "Medicine name: ");
            if(medicine == null || medicine.length() == 0){
                System.exit(0);
            }
            String timesString = JOptionPane.showInputDialog(null, "How many times/minute: ");
            if(timesString == null || timesString.length() == 0){
                System.exit(0);
            }
            sc = new Scanner(timesString);
            if(sc.hasNextLong()){
                times = sc.nextLong();
            }
            else {
                break;
            }

            Medecine m = new Medecine(medicine, times);
            Thread t = new Thread(m);

            t.start();
        }
        System.exit(0);
    }
}
