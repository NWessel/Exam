package dk.cphbusiness.sem4.model.DTO;


public class Runde2DTO {
    
    private int id;
    private String pulje;

    public Runde2DTO() {
    }
    
    public Runde2DTO(int id, String pulje) {
        this.id = id;
        this.pulje = pulje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPulje() {
        return pulje;
    }

    public void setPulje(String pulje) {
        this.pulje = pulje;
    }
}
