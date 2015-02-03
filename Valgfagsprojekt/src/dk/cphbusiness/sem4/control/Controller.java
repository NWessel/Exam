package dk.cphbusiness.sem4.control;

import dk.cphbusiness.sem4.model.DTO.HappinessDTO;
import dk.cphbusiness.sem4.model.DTO.Runde1DTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import dk.cphbusiness.sem4.model.ETO.ExistingDataException;
import dk.cphbusiness.sem4.model.ETO.HarStemtException;
import dk.cphbusiness.sem4.model.ETO.PersistentException;
import dk.cphbusiness.sem4.model.ETO.StuderendeException;
import dk.cphbusiness.sem4.model.Runde1;
import java.util.Collection;

public class Controller {
    
    ValgfagsLogic vfl;
    Runde1Logic r1l;
    Runde2Logic r2l;

    public Controller() {
        vfl = new ValgfagsLogic();
        r1l = new Runde1Logic();
        r2l = new Runde2Logic();
    }
    
    public Collection<ValgfagsDTO> listValgfagRunde1() {
        return vfl.listValgfagRunde1();
    }

    public void registrerRunde1Valg(Collection<Runde1DTO> runde1DTOListe) throws HarStemtException, PersistentException, StuderendeException{
        r1l.registrerRunde1Valg(runde1DTOListe);
    }
    
    public Collection<ValgfagsDTO> listValgfagRunde1Resultat() {
        return vfl.listValgfagRunde1Resultat();
    }
    
    public Collection<HappinessDTO> opdaterHappiness(Collection<ValgfagsDTO> vfDTOListe){
        return r1l.opdaterHappiness(vfDTOListe);
    }
    
    public void gemValgfagTilRunde2 (Collection<ValgfagsDTO> vfDTOListe) throws PersistentException, ExistingDataException{
        r2l.gemValgfagTilRunde2(vfDTOListe);
    }
    
    public int sletRunde2iDB(){
        return r2l.sletRunde2iDB();
    }
}