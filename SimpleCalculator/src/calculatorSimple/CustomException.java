package calculatorSimple;

public class CustomException extends Exception{

    String message = new String();
    public CustomException(String message) {
        this.message = message;
    }
}

