package dk.cphbusiness.sem4.control;

import dk.cphbusiness.sem4.model.DTO.HappinessDTO;
import dk.cphbusiness.sem4.model.DTO.Runde1DTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import dk.cphbusiness.sem4.model.DbFacade;
import dk.cphbusiness.sem4.model.ETO.ExistingDataException;
import dk.cphbusiness.sem4.model.ETO.HarStemtException;
import dk.cphbusiness.sem4.model.ETO.PersistentException;
import dk.cphbusiness.sem4.model.ETO.StuderendeException;
import dk.cphbusiness.sem4.model.Runde1res;
import dk.cphbusiness.sem4.model.Studerende;
import dk.cphbusiness.sem4.model.Valgfag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Runde1Logic {
    
    public void registrerRunde1Valg(Collection<Runde1DTO> runde1DTOListe) throws HarStemtException, PersistentException, StuderendeException
    {
        ArrayList<Runde1DTO> r1DTOListe = new ArrayList<>(runde1DTOListe);
        Collection<Valgfag> vfCollection = DbFacade.getInstance().listValgfagRunde1();
        ArrayList<Valgfag> vfListe = new ArrayList<>(vfCollection);
        Studerende studerende = DbFacade.getInstance().findStuderende(r1DTOListe.get(0).getEmail());
        Runde1res r1res;
            
        if (studerende == null){
                throw new StuderendeException("Denne mail eksisterer ikke i Databasen");
            }
        
        if(studerende.getHarstemt1() == 0){
            
            r1res = new Runde1res();
            r1res.setEmail(r1DTOListe.get(0).getEmail());
            
            for (Valgfag vf : vfListe) {
                for (Runde1DTO r1dto : runde1DTOListe) {
                    
                    if (vf.getValgfagId() == r1dto.getId()){
                        
                        if (r1dto.getPrioritet() == 1 && r1res.getP1a() == null){
                            r1res.setP1a(vf);
                        }
                        else if (r1dto.getPrioritet() == 1){
                            r1res.setP1b(vf);
                        }
                        
                        else if (r1dto.getPrioritet() == 2 && r1res.getP2a() == null){
                            r1res.setP2a(vf);
                        }
                        else if (r1dto.getPrioritet() == 2){
                            r1res.setP2b(vf);
                        }
                    }    
                }                  
            }
            studerende.setHarstemt1(1);
        }
        else
        {
            throw new HarStemtException("Du har allerede afgivet din stemme på: " + studerende.getEmail());
        }
        
        if(studerende.getHarstemt1() == 1 )
        {
            try{
                DbFacade.getInstance().gemRunde1res(r1res);
                DbFacade.getInstance().gemStuderende(studerende);
            }
            catch(Exception e1){
                throw new PersistentException(e1.getMessage());
            }
        }
    }  
    
    public Collection<HappinessDTO> opdaterHappiness(Collection<ValgfagsDTO> vfDTOListe){
        
        Collection<Runde1res> tmpList = DbFacade.getInstance().hentRunde1Resultater();
        ArrayList<Runde1res> r1resList = new ArrayList<>(tmpList);
        ArrayList<ValgfagsDTO> vfDTOList = new ArrayList<>(vfDTOListe);
        ArrayList<HappinessDTO> hapDTOList = new ArrayList<>();
        HappinessDTO hDTO;
        
        for (Runde1res r1res : r1resList) {
            hDTO = new HappinessDTO(r1res.getEmail(), 0);
            boolean flagA = false;
            boolean flagB = false;
            
            for (ValgfagsDTO vfDTO : vfDTOList) {
                
                if(vfDTO.getPulje().equals("a") && r1res.getP1a().getValgfagId() == vfDTO.getId() && !flagA ){
                    hDTO.setScore(hDTO.getScore()+3);
                    flagA = true;
                }
                else if(vfDTO.getPulje().equals("a") && r1res.getP1b().getValgfagId() == vfDTO.getId() && !flagA) {
                    hDTO.setScore(hDTO.getScore()+3);
                    flagA = true;
                }
                else if(vfDTO.getPulje().equals("a") && r1res.getP2a().getValgfagId() == vfDTO.getId()&& !flagA) {
                    hDTO.setScore(hDTO.getScore()+1);
                    flagA = true;
                }
                else if(vfDTO.getPulje().equals("a") && r1res.getP2b().getValgfagId() == vfDTO.getId() && !flagA) {
                    hDTO.setScore(hDTO.getScore()+1);
                    flagA = true;
                }
                
                if(vfDTO.getPulje().equals("b") && r1res.getP1a().getValgfagId() == vfDTO.getId() && !flagB ){
                    hDTO.setScore(hDTO.getScore()+3);
                    flagB = true;
                }
                else if(vfDTO.getPulje().equals("b") && r1res.getP1b().getValgfagId() == vfDTO.getId() && !flagB){
                    hDTO.setScore(hDTO.getScore()+3);
                    flagB = true;
                }
                else if(vfDTO.getPulje().equals("b") && r1res.getP2a().getValgfagId() == vfDTO.getId() && !flagB){
                    hDTO.setScore(hDTO.getScore()+1);
                    flagB = true;
                }
                else if(vfDTO.getPulje().equals("b") && r1res.getP2b().getValgfagId() == vfDTO.getId() && !flagB){
                    hDTO.setScore(hDTO.getScore()+1);
                    flagB = true;
                }
            }
            hapDTOList.add(hDTO);
        }
        Collections.sort(hapDTOList);     
        return hapDTOList;
    }
}       
        
      