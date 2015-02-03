package dk.cphbusiness.sem4.model.DTO;


import dk.cphbusiness.sem4.model.Runde1;
import dk.cphbusiness.sem4.model.Runde2;
import dk.cphbusiness.sem4.model.Valgfag;
import java.util.ArrayList;
import java.util.Collection;


public class Assembler {
    
    // Modtager et Valgfag objekt og laver det om til et ValgfagsDTO
    public static ValgfagsDTO valgfagTilValgfagsDTO (Valgfag vf){
        ValgfagsDTO vflDTO = new ValgfagsDTO(vf.getValgfagId(), vf.getNavn(), vf.getBeskrivelse());
        return vflDTO;
    }
    
    // Modtager en Collection med rund1DTO'er og laver dem om til en Collection af Runde1 objekter
    public static Collection<Runde1> runde1DTOListeTilRunde1Liste(Collection<Runde1DTO> runde1DTOListe){
        ArrayList<Runde1> runde1Liste = new ArrayList<>();
        Runde1 r1;
        
        for (Runde1DTO r1DTO : runde1DTOListe) {
            r1 = new Runde1();
            r1.setValgfagId(r1DTO.getId());
            runde1Liste.add(r1);
        }
        return runde1Liste;
    }  
    
    // Modtager en Collection med ValfagsDTO og laver dem om til en Collection af Runde2 objekter
    public static Collection<Runde2> valgfagsDTOListeTilRunde2Liste (Collection<ValgfagsDTO> vfDTOListe){
        ArrayList<Runde2> runde2Liste = new ArrayList<>();
        Runde2 r2;
        
        for (ValgfagsDTO vfDTO : vfDTOListe) {
            r2 = new Runde2();
            r2.setPulje(vfDTO.getPulje());
            r2.setValgfagId(vfDTO.getId());
            runde2Liste.add(r2);
        }
        return runde2Liste;
    }
    
    // Modtager et Runde2 objekt og laver det om til et Runde2DTO
    public static Runde2DTO runde2TilRunde2DTO (Runde2 r2){
        Runde2DTO r2lDTO = new Runde2DTO(r2.getValgfagId(), r2.getPulje());
        return r2lDTO;
    }
}
