package dk.cphbusiness.sem4.model.DTO;

import java.io.Serializable;

public class Runde1DTO implements Serializable{
    
    private int id;
    private int prioritet;
    private String email;

    public Runde1DTO(int id, int prioritet, String email) {
        this.id = id;
        this.prioritet = prioritet;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
