package ClientTCP;


import java.io.Serializable;

public class Client implements Serializable {
    private String alias;
    private String message;
    public Client(){}
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
