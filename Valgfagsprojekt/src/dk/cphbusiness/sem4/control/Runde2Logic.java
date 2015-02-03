package dk.cphbusiness.sem4.control;

import dk.cphbusiness.sem4.model.DTO.Assembler;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import dk.cphbusiness.sem4.model.DbFacade;
import dk.cphbusiness.sem4.model.ETO.ExistingDataException;
import dk.cphbusiness.sem4.model.ETO.PersistentException;
import dk.cphbusiness.sem4.model.Runde2;
import java.util.ArrayList;
import java.util.Collection;

public class Runde2Logic {
    
    public void gemValgfagTilRunde2 (Collection<ValgfagsDTO> vfDTOListe)throws PersistentException, ExistingDataException{
        Collection<Runde2> runde2Liste = new ArrayList<>();
        Collection<Runde2> tmpList = DbFacade.getInstance().listValgfagRunde2();
        runde2Liste = Assembler.valgfagsDTOListeTilRunde2Liste(vfDTOListe);

        if (tmpList.size() > 0){
            throw new ExistingDataException("Der findes allerede en gemt liste til Runde 2");
            }
        
        try{
            DbFacade.getInstance().gemRunde2Liste(runde2Liste);
        }
        catch(Exception e){
            System.err.println(">>>>>>>>>>:  gemValgfagTilRunde2() i Runde2Logic CATCH");
            throw new PersistentException("Kunne ikke skrive til Databasen");
        }
    }

    public int sletRunde2iDB() {
        return DbFacade.getInstance().sletRunde2Liste();
    }
}
