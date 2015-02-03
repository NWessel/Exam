package dk.cphbusiness.sem4.model.DTO;

import java.io.Serializable;

public class ValgfagsDTO implements Serializable{
    
    private int id;
    private String navn;
    private String beskrivelse;
    private String pulje;

    public ValgfagsDTO(int id, String pulje){
        this.id = id;
        this.pulje = pulje;
    }
    
    public ValgfagsDTO(int id, String navn, String beskrivelse) {
        this.id = id;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    } 

    public String getPulje() {
        return pulje;
    }

    public void setPulje(String pulje) {
        this.pulje = pulje;
    }
}
