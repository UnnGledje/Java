package StreamDemoPack;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonalData {
    //BufferedReader bufIn;
    //String line1, line2, fullLine;


    String readLine = null;
    List<String> pData = new ArrayList<>();
    List<String> pDataTall = new ArrayList<>();
   // BufferedReader bufIn = new BufferedReader(new FileReader("src/Files/PersonalData.txt"));

    void readPData() {
            try(FileReader filereader = new FileReader("src/Files/PersonalData.txt");
                 BufferedReader bufIn = new BufferedReader(filereader); ){
                while ((readLine = bufIn.readLine()) != null) {
                    pData.add(readLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

           // pData.add(readLine);
            //System.out.println(pData);


            for(int i = 0; i < pData.size(); i++) {
                if (i % 2 != 0 && pData.get(i).charAt(pData.size() - 2) == '2') {
                    pDataTall.add(pData.get(i - 1));
                    pDataTall.add(pData.get(i));
                    System.out.println(pDataTall);
                }
            }
            /*
            try {
                if (!((line1 = bufIn.readLine()) != null
                        && ((line2 = bufIn.readLine()) != null)))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            fullLine = line1 +" " + line2;
            pData.add(fullLine);
            for(String list: pData){
            System.out.println(list);
            }

             */
        }
    void writeToFile() {
        try(PrintWriter writer = new PrintWriter(new FileWriter("src/Files/pplTallerThan2m.txt"))) {
            // Write using the PrintWriter instance
                writer.append(pDataTall.toString());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public PersonalData() throws FileNotFoundException {

    }

}