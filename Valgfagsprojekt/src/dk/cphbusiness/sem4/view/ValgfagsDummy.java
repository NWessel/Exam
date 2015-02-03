/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.sem4.view;

import dk.cphbusiness.sem4.model.DTO.Runde1DTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Ultroman
 */
public class ValgfagsDummy {

    private ValgfagsDTO valgfagsListeDTO;
    private ArrayList<ValgfagsDTO> valgfagsListe;
    
    public ValgfagsDummy(){
        init();
    }

    private void init() {
        valgfagsListeDTO = new ValgfagsDTO(1, "C-Sharp", "dksjdkd");
        
        valgfagsListe = new ArrayList<ValgfagsDTO>();
        valgfagsListe.add(new ValgfagsDTO(1, "C-Sharp", "ajshjasj"));
        valgfagsListe.add(new ValgfagsDTO(2, "Java", "sdjksdksj"));
    }
    
    public ValgfagsDTO BeanTester() {
        return valgfagsListeDTO;
    }

    public Collection<ValgfagsDTO> listValgfag() {
        return valgfagsListe;
    }

    public void registrerRunde1Valg(Collection<Runde1DTO> runde1Liste) {
        
    }
}
