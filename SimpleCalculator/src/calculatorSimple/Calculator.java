package calculatorSimple;

import java.util.Scanner;


public class Calculator {

    double firstNo;
    char operator;
    double secondNo;

   Scanner sc = new Scanner(System.in);

    public char getInput(String message){
        System.out.println(message);
        try {
            return sc.nextLine().charAt(0);
            //String input;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return 'A';
    }
    /*
    public void calculate(){
        if(input.equals('+') | )

    }*/

    public void calculator() throws CustomException {

        try {
            firstNo = Integer.parseInt(String.valueOf((getInput("Enter an int: "))));
            operator = (getInput("Enter an operator: "));
            secondNo = Integer.parseInt(String.valueOf((getInput("Enter another int: "))));
            if (operator == '/') {
                System.out.println(firstNo * secondNo);
            } else if (operator == '+') {
                System.out.println(firstNo + secondNo);
            } else if (operator == '-') {
                System.out.println(firstNo - secondNo);
            } else if (operator == '*') {
                System.out.println(firstNo * secondNo);
            }
            if(!(operator == '/' | operator == '*' | operator == '+' | operator == '-')) {
                throw new CustomException("Incorrect operator");

            }
        } catch (CustomException e) {
           System.out.println("Error");
            //e.printStackTrace();
        }

    }
}
