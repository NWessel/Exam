package dk.cphbusiness.sem4.control;

import dk.cphbusiness.sem4.view.ValgFagsJFrame;


public class Starter {

    public static void main(String[] args) {
        
        Controller control = new Controller();
        ValgFagsJFrame mainframe = new ValgFagsJFrame(control);
    }   
}
