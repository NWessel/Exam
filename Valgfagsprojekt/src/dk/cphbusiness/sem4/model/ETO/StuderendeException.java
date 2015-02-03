package dk.cphbusiness.sem4.model.ETO;

public class StuderendeException extends Exception {
    
    private String email;

    public StuderendeException(){   
    }
    
    public StuderendeException(String message) {
        super(message);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
