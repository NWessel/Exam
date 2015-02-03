/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.sem4.view;

import dk.cphbusiness.sem4.control.Controller;
import dk.cphbusiness.sem4.model.DTO.HappinessDTO;
import dk.cphbusiness.sem4.model.DTO.Runde1DTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import dk.cphbusiness.sem4.model.ETO.ExistingDataException;
import dk.cphbusiness.sem4.model.ETO.HarStemtException;
import dk.cphbusiness.sem4.model.ETO.PersistentException;
import dk.cphbusiness.sem4.model.ETO.StuderendeException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ultroman
 */
public class ValgFagsJFrame extends JFrame {

    private Stack<JPanel> menuStack = new Stack<>();
    private boolean confirmed = false;
    private Controller control;
    /**
     * Creates new form NewGUI
     */
    public ValgFagsJFrame(Controller control) {
        this.control = control;
        // init look-and-feel
        myInit();
        // init JFrame
        initComponents();
        // instantiate and add Login-panel to the stack
        menuStack.add(new Login());
        // set JFrame ContentPane to the login screen
        setContentPane(menuStack.get(0));
        // make the JFrame visible
        setVisible(true);
    }
    
    /*
     * goTo-commands add a new panel to the stack.
     * If any resources are required by the panel, we ask the controller to
     * supply them, making the controller the only class that has correspondence
     * with the database.
     * 
     * A "Back" command simply calls goBackThroughMenus() to try to backtrack
     * through the stack, and recieves a boolean indicating if it succeeded.
     */
    public boolean goTo(String command){
        switch (command) {
            case "StudLogin":
                menuStack.add(new StuderendeMenu());
                break;
                
            case "UDKLogin":
                menuStack.add(new UDKMenu());
                break;
                
            case "StudValgfagsRunde1":
                menuStack.add(new StudValgfagsRunde1(new ArrayList<>(control.listValgfagRunde1())));
                break;
                
            case "UDKValgfagsRunde1":
                menuStack.add(new UDKValgfagsRunde1(new ArrayList<>(control.listValgfagRunde1Resultat())));
                break;
                
            case "Back":
                confirmed = goBackThroughMenus();
                break;
            
            default:
                return false;
        }
        return true;
    }
    
    private boolean action(String command) {
        switch (command) {
            case "RegistrerValgfagRunde1":
                StudValgfagsRunde1 panel = (StudValgfagsRunde1)this.getContentPane();
                ArrayList<Runde1DTO> runde1DTOListe = new ArrayList<>();
                ArrayList<ValgfagsDTO> valgfagsListe = panel.getValgfagsListe();
                ArrayList<String> valgteFag = panel.getValgteValgfag();
                
//                System.out.println("Finder valgfag:\n");
                // 1. prioriteter
                for (ValgfagsDTO valgfag : valgfagsListe) {
                    if(valgfag.getNavn().equals(valgteFag.get(0))){
                        runde1DTOListe.add(new Runde1DTO(valgfag.getId(), 1, panel.getEmail()));
//                        System.out.println("ID1: "+valgfag.getId()+", Prioritet: 1, E-mail: "+panel.getEmail()+"\n");
                    }else if(valgfag.getNavn().equals(valgteFag.get(1))){
//                        System.out.println("ID2: "+valgfag.getId()+", Prioritet: 1, E-mail: "+panel.getEmail()+"\n");
                        runde1DTOListe.add(new Runde1DTO(valgfag.getId(), 1, panel.getEmail()));
                    }
                }
                
                // 2. prioriteter
                for (ValgfagsDTO valgfag : valgfagsListe) {
                    if(valgfag.getNavn().equals(valgteFag.get(2))){
                        runde1DTOListe.add(new Runde1DTO(valgfag.getId(), 2, panel.getEmail()));
//                        System.out.println("ID3: "+valgfag.getId()+", Prioritet: 2, E-mail: "+panel.getEmail()+"\n");
                    }else if(valgfag.getNavn().equals(valgteFag.get(3))){
                        runde1DTOListe.add(new Runde1DTO(valgfag.getId(), 2, panel.getEmail()));
//                        System.out.println("ID4: "+valgfag.getId()+", Prioritet: 2, E-mail: "+panel.getEmail()+"\n");
                    }
                }
                
                try {
                    control.registrerRunde1Valg(runde1DTOListe);
                    JOptionPane.showMessageDialog(null, "Dit valg er accepteret og registreret.");
                } catch (StuderendeException ex) {
                    JOptionPane.showMessageDialog(null, "Den indtastede e-mail er ikke tilknyttet en studrende");
                } catch (HarStemtException ex) {
                    JOptionPane.showMessageDialog(null, "Der er allerede registreret valgfag med denne e-mail!");
                } catch (PersistentException ex) {
                    JOptionPane.showMessageDialog(null, "Fejl i forbindelsen til databasen! Kontakt skolens IT-afdeling.\n\nFejl: "+ex.getCause());
                }
                break;
            case "UDKOpdaterHappinessRunde1":
                // saml oplysninger om valgte valgfag fra panelet, og send dem til control, hvorfra der modtages
                // en liste af emails og deres happiness som Integer.
                // Kør opdaterHappinessTabel(Vector happiness) i panelet med den liste vi har modtaget.
                UDKValgfagsRunde1 temp = (UDKValgfagsRunde1)this.getContentPane();
                
                temp.opdaterStuderendeJTable(new ArrayList<HappinessDTO>(control.opdaterHappiness(temp.getValgteValgfag())));
                break;
                
            case "UDKRegistrerRunde1Valgfag":
                UDKValgfagsRunde1 temp2 = (UDKValgfagsRunde1)this.getContentPane();
                try {
                    control.gemValgfagTilRunde2(temp2.getValgteValgfag());
                    JOptionPane.showMessageDialog(null, "Listen med valgfag er nu gemt.");
                }catch(ExistingDataException ee){
                    int reply = JOptionPane.showConfirmDialog(null, "Der findes allerede en gemt Liste med valgfag til Runde 2."
                            + "\nTryk OK for at slette eksisterende liste og gemme dine nye valg eller\ntryk NO for at afbryde.", "Oops. Du kan miste data !!! ", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.YES_OPTION) {
                        int slettet = control.sletRunde2iDB();
                        UDKValgfagsRunde1 temp3 = (UDKValgfagsRunde1)this.getContentPane();
                            try {
                                control.gemValgfagTilRunde2(temp3.getValgteValgfag());
                                JOptionPane.showMessageDialog(null, "Ny liste med valgfag til Runde 2 er gemt.\nGamle liste med (" + slettet +" stk) valgfag er nu slettet.");
                            } catch (PersistentException | ExistingDataException ex) {
                            JOptionPane.showMessageDialog(null, "Fejl ved forsøg på persistering i databasen.\n\nFejl: "+ex.getCause());
                            }
                    }
                } 
                catch (PersistentException ex) {
                    JOptionPane.showMessageDialog(null, "Fejl ved forsøg på persistering i databasen.\n\nFejl: "+ex.getCause());
                }
                break;
                
            default:
                return false;
        }
        return true;
    }
    
    /*
     * actuateCommand recieves a String, and tries to decipher it.
     * If it is a GOTO-command (ex. GOTO_StudLogin), the current panel is set
     * not visible, and the subcommand (the String after the "_") is sent to the
     * goTo(String) method, which tries to add the corresponding panel to the
     * stack.
     * 
     * Regardless of the success of the call to goTo(String), the top panel is
     * set as the new contentPane on this JFrame and made visible.
     * If a new panel has been added, it will become visible, and if not then
     * the old panel will remain visible and the current contentPane.
     */
    public void actuateCommand(String command){
        confirmed = false;
        
        String mainCommand = command.substring(0, command.indexOf("_", 0));
        String subCommand = command.substring(command.indexOf("_", 0)+1);
//        System.out.println("Main command: "+mainCommand+"\nSubCommand: "+subCommand);
        
        switch (mainCommand) {
            case "GOTO":
                menuStack.get(menuStack.size()-1).setVisible(false);
                confirmed = goTo(subCommand);
                break;
            
            case "ACTION":
                confirmed = action(subCommand);
            default:
                break;
        }
        if(!confirmed){
//            JOptionPane.showMessageDialog(null, "Program has failed");
//            System.out.println("Not a valid command");
        }
        
        setContentPane(menuStack.get(menuStack.size()-1));
        menuStack.get(menuStack.size()-1).setVisible(true);
    }

    /*
     * This function simply pops the top JPanel from the stack, and sets the
     * previous one as the new contentPane.
     * It makes sure not to pop the last menu, which is the login-panel, and if
     * someone tries to do this, it will return false.
     * 
     * It is only run by the goTo(String) method.
     */
    public boolean goBackThroughMenus(){
        
        if(menuStack.size()>1
//        && 0==JOptionPane.showConfirmDialog(null, "Er du sikker på du vil gå tilbage?\nAlle ændringer der ikke er gemt vil gå tabt!")
        ){
            menuStack.pop();
            setContentPane(menuStack.get(menuStack.size()-1));
            return true;
        }
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stjerneholdets Valgfagsprogram");
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Stack<JPanel> getMenuStack() {
        return menuStack;
    }

    public void setMenuStack(Stack<JPanel> menuStack) {
        this.menuStack = menuStack;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void myInit() {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ValgFagsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ValgFagsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ValgFagsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ValgFagsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
}
