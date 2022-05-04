package StreamDemoPack;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Starter {

    public static void main(String[] args) throws FileNotFoundException {
        //Celcius c = new Celcius();
       // c.calculateAverage(c.temperatures);
       /* PersonalData pd = new PersonalData();
        pd.readPData();
        CreateFile cf = new CreateFile();
        cf.createFile();
        pd.writeToFile();
        */
        WordCount wc = new WordCount();
        wc.URLread();
       // wc.sort((ArrayList<String>) wc.wordList, wc.wordList.size());
        //wc.printArraystring(wc.wordList, wc.wordList.size());
    }
}
