package dk.cphbusiness.sem4.test;

import dk.cphbusiness.sem4.control.Controller;
import dk.cphbusiness.sem4.model.DTO.Assembler;
import dk.cphbusiness.sem4.model.DTO.HappinessDTO;
import dk.cphbusiness.sem4.model.DTO.Runde1DTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import dk.cphbusiness.sem4.model.ETO.ExistingDataException;
import dk.cphbusiness.sem4.model.ETO.HarStemtException;
import dk.cphbusiness.sem4.model.ETO.PersistentException;
import dk.cphbusiness.sem4.model.ETO.StuderendeException;
import dk.cphbusiness.sem4.model.Runde1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tester {
    
    public Tester() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // Tester om vi modtager de rigtige objekter og at ArrayList indeholder data.
    @Test
    public void testListValgfagRunde1(){
        Controller c = new Controller();
        Collection<ValgfagsDTO> vfDtoList = c.listValgfagRunde1();
        assertTrue (!vfDtoList.isEmpty());
    }
    
  
    // Tester runde1DTOListeTilRunde1Liste i Assembler.
    @Test
    public void testRunde1DTOListeTilRunde1Liste(){
        Runde1DTO r1DTO1 = new Runde1DTO(1, 1, "test1@test.dk");
        Runde1DTO r1DTO2 = new Runde1DTO(2, 2, "test2@test.dk");
        Collection<Runde1DTO> rundeDTOListe = new ArrayList<>();
        rundeDTOListe.add(r1DTO1);
        rundeDTOListe.add(r1DTO2);        
        ArrayList<Runde1> r1Liste;
        r1Liste = (ArrayList<Runde1>) Assembler.runde1DTOListeTilRunde1Liste(rundeDTOListe);
        
        assertTrue (r1Liste.get(0).getValgfagId() == 1);
        assertTrue (r1Liste.get(1).getValgfagId() == 2);     
    }
    
    
    // Tester at metoden til at gemme Runde2 objekter i Databasen
    @Test
    public void testGemValgfagTilRunde2(){
        Controller c = new Controller();
        boolean handled = false;
        
        ValgfagsDTO vfDTO1 = new ValgfagsDTO(1, "a");
        ValgfagsDTO vfDTO2 = new ValgfagsDTO(2, "b");
        ValgfagsDTO vfDTO3 = new ValgfagsDTO(3, "a");
        ValgfagsDTO vfDTO4 = new ValgfagsDTO(4, "b");
        ValgfagsDTO vfDTO5 = new ValgfagsDTO(5, "a");
        ValgfagsDTO vfDTO6 = new ValgfagsDTO(6, "b");
        
        ArrayList<ValgfagsDTO> vfList = new ArrayList<>();
        
        vfList.add(vfDTO1);
        vfList.add(vfDTO2);
        vfList.add(vfDTO3);
        vfList.add(vfDTO4);
        vfList.add(vfDTO5);
        vfList.add(vfDTO6);
        
        try{
            c.gemValgfagTilRunde2(vfList);
        }
        catch(PersistentException pe){
            handled = true;
        }
        catch(ExistingDataException ee){
            
        }
        
        assertTrue(handled == false);
    }
    
    
    // Tester registrerRunde1Valg, som gemmer resultatet for afstemning i runde 1.
    // Test hvor stud. der har stemt prøver at stemme igen
    @Test
    public void testRegistrerRunde1ValgScenario1() throws StuderendeException{
        
        Controller c = new Controller();
        Boolean handled = false;
        Collection<Runde1DTO> r1DTOListe = new ArrayList<>();
        
        Runde1DTO r1DTO1 = new Runde1DTO(1, 2, "patrick@gmail.com");
       
        r1DTOListe.add(r1DTO1);
        
        try {
            c.registrerRunde1Valg(r1DTOListe);
        } catch (HarStemtException ex) {
            handled = true;
        }
        catch (PersistentException ex){
        }
        assertTrue (handled == true);
    }
    
    
    // Denne stud. har IKKE stemt og vil gerne vælge sine 2 første pri. og 2 anden pri.
    @Test
    public void testRegistrerRunde1ValgScenario2() throws StuderendeException{        
        Controller c = new Controller();
        Boolean handled = false;
        Collection<Runde1DTO> r1DTOListe = new ArrayList<>();
        
        Runde1DTO r1DTO2 = new Runde1DTO(2, 1, "thomas@gmail.com");
        Runde1DTO r1DTO3 = new Runde1DTO(3, 1, "thomas@gmail.com");
        Runde1DTO r1DTO4 = new Runde1DTO(4, 2, "thomas@gmail.com");
        Runde1DTO r1DTO5 = new Runde1DTO(5, 2, "thomas@gmail.com");
    
        r1DTOListe.add(r1DTO2);
        r1DTOListe.add(r1DTO3);
        r1DTOListe.add(r1DTO4);
        r1DTOListe.add(r1DTO5);
        
        try {
            c.registrerRunde1Valg(r1DTOListe);
        } catch (HarStemtException ex){   
        }
        catch (PersistentException ex) {
            handled = true;
        }
        assertTrue (handled == false);
    }
        
       
    // Test hvor en stud. indtaster ugyldig email, som ikke er reg. i DB
    @Test
    public void testRegistrerRunde1ValgScenario3() throws StuderendeException{ 
        Controller c = new Controller();
        Boolean handled = false;
        Collection<Runde1DTO> r1DTOListe = new ArrayList<>();
            
        Runde1DTO r1DTO6 = new Runde1DTO(5, 2, "gunnar@gmail.com");
        
        handled = false;
        r1DTOListe.add(r1DTO6);
        
        try {
            c.registrerRunde1Valg(r1DTOListe);
        } catch (HarStemtException | PersistentException ex){
        }
        catch (StuderendeException ex){
            handled = false;
        }
        assertTrue (handled == false);  
    } 
    
    
    // Tester om vi modtager de rigtige objekter og at ArrayList indeholder data.
    @Test
    public void testListValgfagRunde1Resultat(){
        Controller c = new Controller();
        Collection<ValgfagsDTO> vfDtoList = c.listValgfagRunde1Resultat();
        assertTrue (!vfDtoList.isEmpty());
    }
    
    
    // Tester vores opdaterHapiness Metode, hvorvidt den tildeler korrekt score
    @Test
    public void testOpdaterHapinessScenario1(){   
        Controller c = new Controller();
        ArrayList<ValgfagsDTO> tmpList = new ArrayList<>();
        
        ValgfagsDTO v1 = new ValgfagsDTO(2, "a");
        ValgfagsDTO v2 = new ValgfagsDTO(3, "a");
        ValgfagsDTO v3 = new ValgfagsDTO(4, "b");
        ValgfagsDTO v4 = new ValgfagsDTO(5, "b");
        
        tmpList.add(v1); 
        tmpList.add(v2);
        tmpList.add(v3);
        tmpList.add(v4);
        
        ArrayList<HappinessDTO> hDTOList = new ArrayList<>(c.opdaterHappiness(tmpList));
        
        assertTrue(hDTOList.size() == 1);
        assertTrue(hDTOList.get(0).getScore() == 4);
        
    }
    
    
    // Tester vores opdaterHapiness Metode, hvorvidt den tildeler korrekt score
    @Test
    public void testOpdaterHapinessScenario2(){
        Controller c = new Controller();
        ArrayList<ValgfagsDTO> tmpList = new ArrayList<>();
        
        ValgfagsDTO v1 = new ValgfagsDTO(2, "b");
        ValgfagsDTO v2 = new ValgfagsDTO(3, "b");
        ValgfagsDTO v3 = new ValgfagsDTO(4, "a");
        ValgfagsDTO v4 = new ValgfagsDTO(5, "a");
        
        tmpList.add(v1); 
        tmpList.add(v2);
        tmpList.add(v3);
        tmpList.add(v4);
        
        ArrayList<HappinessDTO> hDTOList = new ArrayList<>(c.opdaterHappiness(tmpList));
        
        assertTrue(hDTOList.size() == 1);
        assertTrue(hDTOList.get(0).getScore() == 4);    
    }  
    
    
    // Tester vores opdaterHapiness Metode, hvorvidt den tildeler korrekt score
    @Test
    public void testOpdaterHapinessScenario3(){
        Controller c = new Controller();
        ArrayList<ValgfagsDTO> tmpList = new ArrayList<>();
        
        ValgfagsDTO v1 = new ValgfagsDTO(2, "a");
        ValgfagsDTO v2 = new ValgfagsDTO(3, "b");
        ValgfagsDTO v3 = new ValgfagsDTO(4, "a");
        ValgfagsDTO v4 = new ValgfagsDTO(5, "b");
        
        tmpList.add(v1); 
        tmpList.add(v2);
        tmpList.add(v3);
        tmpList.add(v4);
        
        ArrayList<HappinessDTO> hDTOList = new ArrayList<>(c.opdaterHappiness(tmpList));
        
        assertTrue(hDTOList.size() == 1);
        assertTrue(hDTOList.get(0).getScore() == 6);    
    }
   
    
   // Tester vores opdaterHapiness Metode, hvorvidt den tildeler korrekt score
    @Test
    public void testOpdaterHapinessScenario4(){
        Controller c = new Controller();
        ArrayList<ValgfagsDTO> tmpList = new ArrayList<>();
        
        ValgfagsDTO v1 = new ValgfagsDTO(8, "a");
        ValgfagsDTO v2 = new ValgfagsDTO(3, "b");
        ValgfagsDTO v3 = new ValgfagsDTO(10,"a");
        ValgfagsDTO v4 = new ValgfagsDTO(5, "b");
        
        tmpList.add(v1); 
        tmpList.add(v2);
        tmpList.add(v3);
        tmpList.add(v4);
        
        ArrayList<HappinessDTO> hDTOList = new ArrayList<>(c.opdaterHappiness(tmpList));
        
        assertTrue(hDTOList.size() == 1);
        assertTrue(hDTOList.get(0).getScore() == 3);    
    }
}
