package ScannerDemoPack;
import java.util.Scanner;
public class ScannerD {
     Scanner sc = new Scanner(System.in);

        public void getData() {
            System.out.println("Enter today's meter reading: ");
            String currMeterRead = sc.nextLine();
            double parseCurrMeterRead = Double.parseDouble(currMeterRead);

            System.out.println("Enter the meter reading from one year ago: ");
            String oldMeterRead = sc.nextLine();
            double parseOldMeterRead = Double.parseDouble(oldMeterRead);

            System.out.println("Enter the amount of liters of petrol consumed during the year: ");
            String petrol = sc.nextLine();
            double parsePetrol = Double.parseDouble(petrol);

            double amountOfMiles = parseCurrMeterRead - parseOldMeterRead;
            double petrolUseMile = parsePetrol / amountOfMiles;

            System.out.println(String.format("Amount of miles driven: %.2f", amountOfMiles));
            System.out.println(String.format("Amount of petrol: %.2f", parsePetrol));
            System.out.println(String.format("Petrol usage per mile: %.2f", petrolUseMile));
        }
}
