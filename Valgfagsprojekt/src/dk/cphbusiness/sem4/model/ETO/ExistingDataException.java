package dk.cphbusiness.sem4.model.ETO;


public class ExistingDataException extends Exception {

    public ExistingDataException(){
        
    }
    
    public ExistingDataException(String message) {
        super(message);
    }
    
}
