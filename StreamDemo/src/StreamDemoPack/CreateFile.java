package StreamDemoPack;

import java.io.File;
import java.io.IOException;

public class CreateFile {

            void createFile() {
                try {
                    File myFile = new File("src/Files/pplTallerThan2m.txt");
                    if (myFile.createNewFile()) {
                        System.out.println("File created: " + myFile.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
}
