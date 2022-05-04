
package StreamDemoPack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static java.lang.System.*;

public class Celcius {
    String readLine;
    List<Double> temperatures = new ArrayList<>();
    BufferedReader bufIn = new BufferedReader(new FileReader("src//StreamDemoPack//temp.txt"));

    {

        while (true) {
            try {
                if (!((readLine = bufIn.readLine()) != null))
                    break;

            }catch (IOException e) {
                e.printStackTrace();
            }
        temperatures.add(Double.parseDouble(String.valueOf(readLine)));
            //System.out.println(temperatures);
        }
    }

    public Celcius() throws FileNotFoundException {

       /* int adjacentElementsProduct(temperatures[] inputArray) {
            int[] temp = new int[inputArray.length - 1];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = inputArray[i] * inputArray[i + 1];
            }
            int x = temp[0];
            for (int i = 1; i < temp.length; i++) {
                if (temp[i] > x) {
                    x = temp[i];
                }
            }
            return x;
        }
        */
        double sum = 0;

        System.out.println(Collections.max(temperatures));
        System.out.println(Collections.min(temperatures));
        for (double i : temperatures) {
            sum += i;
        }
        System.out.println("Average of list is: " + sum / temperatures.size());
    }
    /*public void calculateAverage(List <Double> temp) {
        //double result;
        double sum = 0;
        for (double i = 0; i < temp.size(); i++) {
            sum += i;
        }
       //sum / temp.size();
        System.out.println("Average of list is: " + sum / temp.size());
    }
    */

}


