/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.sem4.view;

import dk.cphbusiness.sem4.model.DTO.HappinessDTO;
import dk.cphbusiness.sem4.model.DTO.ValgfagsDTO;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ultroman
 */
public class UDKValgfagsRunde1 extends JPanel {
    
    ArrayList<ValgfagsDTO> valgfagsListe;
    private DefaultTableModel tm;
    private DecimalFormat fmt;
    
    /**
     * Creates new form Login
     */
    public UDKValgfagsRunde1(ArrayList<ValgfagsDTO> valgfagsListe) {
        fmt = new DecimalFormat("#.##");
        fmt.setRoundingMode(RoundingMode.HALF_UP);
        this.valgfagsListe = valgfagsListe;
        initComponents();
        jList_Valgfag.setModel(new DefaultListModel());
        jList_Valgfag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList_aPulje.setModel(new DefaultListModel());
        jList_bPulje.setModel(new DefaultListModel());
        
        for (ValgfagsDTO valgfagsDTO : valgfagsListe) {
            ((DefaultListModel)jList_Valgfag.getModel()).addElement(valgfagsDTO.getNavn());
        }
        
        tm = (DefaultTableModel)jTable1.getModel();
	jTable1.setDefaultRenderer(String.class, new EntryCellRenderer()); // the first parameter ensures the cell renderer will be used only for Integer type cells.
        
        jLabel_Score0.setOpaque(true);
        jLabel_Score1.setOpaque(true);
        jLabel_Score2.setOpaque(true);
        jLabel_Score3.setOpaque(true);
        jLabel_Score4.setOpaque(true);
        jLabel_Score6.setOpaque(true);
        
        jLabel_Score0.setBackground(EntryCellRenderer.score0);
        jLabel_Score1.setBackground(EntryCellRenderer.score1);
        jLabel_Score2.setBackground(EntryCellRenderer.score2);
        jLabel_Score3.setBackground(EntryCellRenderer.score3);
        jLabel_Score4.setBackground(EntryCellRenderer.score4);
        jLabel_Score6.setBackground(EntryCellRenderer.score6);
        refreshAllStatusLabels();
    }
    
    private void refreshAllStatusLabels(){
        if(jTable1.getModel().getRowCount()>0){
            int scored0 = 0, scored1 = 0, scored2 = 0, scored3 = 0, scored4 = 0, scored6 = 0;
            double score0Percentage = 0.0, score1Percentage = 0.0, score2Percentage = 0.0, score3Percentage = 0.0, score4Percentage = 0.0, score6Percentage = 0.0;
            int numberOfValues = jTable1.getModel().getRowCount();
            
            for (int i = 0; i < numberOfValues; i++) {
                switch((String)jTable1.getModel().getValueAt(i, 1)){
                    case "0":
                        scored0++;
                        break;
                        
                    case "1":
                        scored1++;
                        break;
                        
                    case "2":
                        scored2++;
                        break;
                        
                    case "3":
                        scored3++;
                        break;
                        
                    case "4":
                        scored4++;
                        break;
                        
                    case "6":
                        scored6++;
                        break;
                        
                    default:
                        break;
                }
            }
            
            score0Percentage = (double)scored0/numberOfValues*100;
            score1Percentage = (double)scored1/numberOfValues*100;
            score2Percentage = (double)scored2/numberOfValues*100;
            score3Percentage = (double)scored3/numberOfValues*100;
            score4Percentage = (double)scored4/numberOfValues*100;
            score6Percentage = (double)scored6/numberOfValues*100;
            
            jLabel_Score0.setText("<html><center>"+fmt.format(score0Percentage)+"%<BR>Nul matches</center></html>");
            jLabel_Score1.setText("<html><center>"+fmt.format(score1Percentage)+"%<BR>Lort</center></html>");
            jLabel_Score2.setText("<html><center>"+fmt.format(score2Percentage)+"%<BR>Semi</center></html>");
            jLabel_Score3.setText("<html><center>"+fmt.format(score3Percentage)+"%<BR>Godt</center></html>");
            jLabel_Score4.setText("<html><center>"+fmt.format(score4Percentage)+"%<BR>Rigtig Godt</center></html>");
            jLabel_Score6.setText("<html><center>"+fmt.format(score6Percentage)+"%<BR>Perfekt</center></html>");
        }else{
            jLabel_Score0.setText("Nul matches");
            jLabel_Score1.setText("Lort");
            jLabel_Score2.setText("Semi");
            jLabel_Score3.setText("Godt");
            jLabel_Score4.setText("Rigtig Godt");
            jLabel_Score6.setText("Perfekt");
        }
    }
    
    public ArrayList<ValgfagsDTO> getValgfagsListe() {
        return valgfagsListe;
    }
    
    public ArrayList<ValgfagsDTO> getValgteValgfag(){
        ArrayList<ValgfagsDTO> valgfag = new ArrayList<ValgfagsDTO>();
        for (int i = 0; i < jList_aPulje.getModel().getSize(); i++) {
            int id = 0;
            String fagNavn = (String)jList_aPulje.getModel().getElementAt(i);
            for (int j = 0; j < valgfagsListe.size(); j++) {
                if(fagNavn.equals(valgfagsListe.get(j).getNavn())){
                    id = valgfagsListe.get(j).getId();
                }
            }
            valgfag.add(new ValgfagsDTO(id, "a"));
        }
        
        for (int i = 0; i < jList_bPulje.getModel().getSize(); i++) {
            int id = 0;
            String fagNavn = (String)jList_bPulje.getModel().getElementAt(i);
            for (int j = 0; j < valgfagsListe.size(); j++) {
                if(fagNavn.equals(valgfagsListe.get(j).getNavn())){
                    id = valgfagsListe.get(j).getId();
                }
            }
            valgfag.add(new ValgfagsDTO(id, "b"));
        }
        return valgfag;
    }
    
    public void opdaterStuderendeJTable(ArrayList<HappinessDTO> nyTabel) {

        Vector<Vector<String>> nyVector = new Vector<Vector<String>>();
        
        for (HappinessDTO happy : nyTabel) {
            Vector<String> newRow = new Vector<String>();
//            System.out.println("Adding e-mail: "+happy.getEmail() +" with score: "+happy.getScore());
            newRow.add(happy.getEmail());
            newRow.add(""+happy.getScore());
            
            nyVector.add(newRow);
        }
        
        // Vi sletter alle rækker i JTable
        rydStuderendeJTable();

        // Vi smider de allokerede udviklere ind i JTable
        for (int i = 0; i < nyVector.size(); i++) {
            tm.addRow(nyVector.get(i));
        }
        
        refreshAllStatusLabels();
    }

    private void rydStuderendeJTable() {
        // TableModel (tm) er en slags interface-objekt i JTable.
        // JTable opdateres alt efter hvad der sker i dens TableModel.
        tm = (DefaultTableModel)jTable1.getModel();

        // vi rydder alle rækker fra JTable's TableModel
        while (tm.getRowCount()>0) {
                tm.removeRow(0);
        }
    }
    
    private void angivValgfagSomAPulje(){
        String temp = (String) jList_Valgfag.getSelectedValue();
        if( temp != null )
        {
            ((DefaultListModel)jList_aPulje.getModel()).addElement(temp);	
            ((DefaultListModel)jList_Valgfag.getModel()).removeElement(temp);

            revalidate();
            repaint();
        }
        giveCommandToParentJFrame("ACTION_UDKOpdaterHappinessRunde1");
    }
    
    private void fjernValgfagFraAPulje(){
        String temp = (String) jList_aPulje.getSelectedValue();
        if( temp != null )
        {
            ((DefaultListModel)jList_Valgfag.getModel()).addElement(temp);
            ((DefaultListModel)jList_aPulje.getModel()).removeElement(temp);

            revalidate();
            repaint();
        }
        giveCommandToParentJFrame("ACTION_UDKOpdaterHappinessRunde1");
        refreshAllStatusLabels();
    }
    
    private void angivValgfagSomBPulje(){
            String temp = (String) jList_Valgfag.getSelectedValue();{
            if( temp != null )
            {
                ((DefaultListModel)jList_bPulje.getModel()).addElement(temp);	
                ((DefaultListModel)jList_Valgfag.getModel()).removeElement(temp);

                revalidate();
                repaint();
            }
        }
        giveCommandToParentJFrame("ACTION_UDKOpdaterHappinessRunde1");
    }
    
    
    private void fjernValgfagFraBPulje(){
        String temp = (String) jList_bPulje.getSelectedValue();
        if( temp != null )
        {
            ((DefaultListModel)jList_Valgfag.getModel()).addElement(temp);
            ((DefaultListModel)jList_bPulje.getModel()).removeElement(temp);

            revalidate();
            repaint();
        }
        giveCommandToParentJFrame("ACTION_UDKOpdaterHappinessRunde1");
    }

    public void giveCommandToParentJFrame(String command){
        ValgFagsJFrame temp = (ValgFagsJFrame)SwingUtilities.getWindowAncestor(this);
        temp.actuateCommand(command);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Tilbage = new javax.swing.JButton();
        jLabel_Valgfag = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_Valgfag = new javax.swing.JList();
        jButton_aPuljeTil = new javax.swing.JButton();
        jButton_aPuljeFra = new javax.swing.JButton();
        jButton_bPuljeTil = new javax.swing.JButton();
        jButton_bPuljeFra = new javax.swing.JButton();
        jLabel_bPulje = new javax.swing.JLabel();
        jLabel_aPulje = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_aPulje = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_bPulje = new javax.swing.JList();
        jButton_Accepter = new javax.swing.JButton();
        jTextField_Beskrivelse = new javax.swing.JTextField();
        jLabel_bPulje1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_aPulje1 = new javax.swing.JLabel();
        jLabel_Score0 = new javax.swing.JLabel();
        jLabel_Score6 = new javax.swing.JLabel();
        jLabel_Score4 = new javax.swing.JLabel();
        jLabel_Score3 = new javax.swing.JLabel();
        jLabel_Score2 = new javax.swing.JLabel();
        jLabel_Score1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(894, 572));
        setMinimumSize(new java.awt.Dimension(894, 572));
        setName("Login"); // NOI18N
        setPreferredSize(new java.awt.Dimension(894, 572));
        setLayout(null);

        jButton_Tilbage.setText("Tilbage");
        jButton_Tilbage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TilbageActionPerformed(evt);
            }
        });
        add(jButton_Tilbage);
        jButton_Tilbage.setBounds(0, 0, 100, 30);

        jLabel_Valgfag.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Valgfag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Valgfag.setText("Valgfag");
        add(jLabel_Valgfag);
        jLabel_Valgfag.setBounds(20, 80, 130, 22);

        jList_Valgfag.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_ValgfagValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList_Valgfag);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 130, 250);

        jButton_aPuljeTil.setText("===>");
        jButton_aPuljeTil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aPuljeTilActionPerformed(evt);
            }
        });
        add(jButton_aPuljeTil);
        jButton_aPuljeTil.setBounds(160, 130, 70, 23);

        jButton_aPuljeFra.setText("<===");
        jButton_aPuljeFra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aPuljeFraActionPerformed(evt);
            }
        });
        add(jButton_aPuljeFra);
        jButton_aPuljeFra.setBounds(160, 160, 70, 23);

        jButton_bPuljeTil.setText("===>");
        jButton_bPuljeTil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bPuljeTilActionPerformed(evt);
            }
        });
        add(jButton_bPuljeTil);
        jButton_bPuljeTil.setBounds(160, 280, 70, 23);

        jButton_bPuljeFra.setText("<===");
        jButton_bPuljeFra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bPuljeFraActionPerformed(evt);
            }
        });
        add(jButton_bPuljeFra);
        jButton_bPuljeFra.setBounds(160, 310, 70, 23);

        jLabel_bPulje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_bPulje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bPulje.setText("Beskrivelse");
        add(jLabel_bPulje);
        jLabel_bPulje.setBounds(20, 390, 110, 30);

        jLabel_aPulje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_aPulje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_aPulje.setText("Studerende");
        add(jLabel_aPulje);
        jLabel_aPulje.setBounds(420, 10, 110, 30);

        jList_aPulje.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jList_aPulje.setVisibleRowCount(2);
        jList_aPulje.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_aPuljeValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList_aPulje);
        jList_aPulje.getAccessibleContext().setAccessibleDescription("");
        jList_aPulje.getAccessibleContext().setAccessibleParent(this);

        add(jScrollPane2);
        jScrollPane2.setBounds(240, 110, 140, 100);

        jList_bPulje.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jList_bPulje.setVisibleRowCount(2);
        jList_bPulje.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_bPuljeValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList_bPulje);
        jList_bPulje.getAccessibleContext().setAccessibleParent(this);

        add(jScrollPane3);
        jScrollPane3.setBounds(240, 260, 140, 100);

        jButton_Accepter.setText("Accepter");
        jButton_Accepter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AccepterActionPerformed(evt);
            }
        });
        add(jButton_Accepter);
        jButton_Accepter.setBounds(770, 520, 110, 40);

        jTextField_Beskrivelse.setEditable(false);
        jTextField_Beskrivelse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_BeskrivelseActionPerformed(evt);
            }
        });
        add(jTextField_Beskrivelse);
        jTextField_Beskrivelse.setBounds(20, 420, 360, 60);

        jLabel_bPulje1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_bPulje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bPulje1.setText("Pulje: B");
        add(jLabel_bPulje1);
        jLabel_bPulje1.setBounds(250, 230, 110, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "E-mail", "Happiness"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jScrollPane4.setViewportView(jTable1);

        add(jScrollPane4);
        jScrollPane4.setBounds(400, 40, 480, 440);

        jLabel_aPulje1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_aPulje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_aPulje1.setText("Pulje: A");
        add(jLabel_aPulje1);
        jLabel_aPulje1.setBounds(250, 80, 110, 30);

        jLabel_Score0.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score0);
        jLabel_Score0.setBounds(800, 480, 80, 30);

        jLabel_Score6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score6);
        jLabel_Score6.setBounds(400, 480, 80, 30);

        jLabel_Score4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score4);
        jLabel_Score4.setBounds(480, 480, 80, 30);

        jLabel_Score3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score3);
        jLabel_Score3.setBounds(560, 480, 80, 30);

        jLabel_Score2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score2);
        jLabel_Score2.setBounds(640, 480, 80, 30);

        jLabel_Score1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Score1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel_Score1);
        jLabel_Score1.setBounds(720, 480, 80, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_TilbageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TilbageActionPerformed
        // TODO add your handling code here:
        giveCommandToParentJFrame("GOTO_Back");
    }//GEN-LAST:event_jButton_TilbageActionPerformed

    public String getBeskrivelseFraDTOUdfraNavn (String valgfagsNavn){
        for (ValgfagsDTO valgfag : valgfagsListe) {
            if(valgfagsNavn.equals(valgfag.getNavn()))return valgfag.getBeskrivelse();
        }
        return "Ingen beskrivelse fundet.";
    }
    
    private void jButton_AccepterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AccepterActionPerformed
       giveCommandToParentJFrame("ACTION_UDKRegistrerRunde1Valgfag");
    }//GEN-LAST:event_jButton_AccepterActionPerformed

    private void jButton_aPuljeTilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aPuljeTilActionPerformed
        // TODO add your handling code here:
        angivValgfagSomAPulje();
    }//GEN-LAST:event_jButton_aPuljeTilActionPerformed

    private void jButton_aPuljeFraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aPuljeFraActionPerformed
        fjernValgfagFraAPulje();
    }//GEN-LAST:event_jButton_aPuljeFraActionPerformed

    private void jButton_bPuljeTilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bPuljeTilActionPerformed
        angivValgfagSomBPulje();
    }//GEN-LAST:event_jButton_bPuljeTilActionPerformed

    private void jButton_bPuljeFraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bPuljeFraActionPerformed
        fjernValgfagFraBPulje();
    }//GEN-LAST:event_jButton_bPuljeFraActionPerformed

    private void jList_ValgfagValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_ValgfagValueChanged
        if(jList_Valgfag.getSelectedIndex()!=-1 && jList_Valgfag.getModel().getElementAt(jList_Valgfag.getSelectedIndex()) != null){
            jTextField_Beskrivelse.setText(getBeskrivelseFraDTOUdfraNavn((String)jList_Valgfag.getModel().getElementAt(jList_Valgfag.getSelectedIndex())));
            jList_aPulje.clearSelection();
            jList_bPulje.clearSelection();
        }
    }//GEN-LAST:event_jList_ValgfagValueChanged

    private void jTextField_BeskrivelseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_BeskrivelseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_BeskrivelseActionPerformed

    private void jList_aPuljeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_aPuljeValueChanged
        if(jList_aPulje.getSelectedIndex()!=-1 && jList_aPulje.getModel().getElementAt(jList_aPulje.getSelectedIndex()) != null){
            jTextField_Beskrivelse.setText(getBeskrivelseFraDTOUdfraNavn((String)jList_aPulje.getModel().getElementAt(jList_aPulje.getSelectedIndex())));
            jList_bPulje.clearSelection();
            jList_Valgfag.clearSelection();
        }
    }//GEN-LAST:event_jList_aPuljeValueChanged

    private void jList_bPuljeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_bPuljeValueChanged
        if(jList_bPulje.getSelectedIndex()!=-1 && jList_bPulje.getModel().getElementAt(jList_bPulje.getSelectedIndex()) != null){
            jTextField_Beskrivelse.setText(getBeskrivelseFraDTOUdfraNavn((String)jList_bPulje.getModel().getElementAt(jList_bPulje.getSelectedIndex())));
            jList_aPulje.clearSelection();
            jList_Valgfag.clearSelection();
        }
    }//GEN-LAST:event_jList_bPuljeValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Accepter;
    private javax.swing.JButton jButton_Tilbage;
    private javax.swing.JButton jButton_aPuljeFra;
    private javax.swing.JButton jButton_aPuljeTil;
    private javax.swing.JButton jButton_bPuljeFra;
    private javax.swing.JButton jButton_bPuljeTil;
    private javax.swing.JLabel jLabel_Score0;
    private javax.swing.JLabel jLabel_Score1;
    private javax.swing.JLabel jLabel_Score2;
    private javax.swing.JLabel jLabel_Score3;
    private javax.swing.JLabel jLabel_Score4;
    private javax.swing.JLabel jLabel_Score6;
    private javax.swing.JLabel jLabel_Valgfag;
    private javax.swing.JLabel jLabel_aPulje;
    private javax.swing.JLabel jLabel_aPulje1;
    private javax.swing.JLabel jLabel_bPulje;
    private javax.swing.JLabel jLabel_bPulje1;
    private javax.swing.JList jList_Valgfag;
    private javax.swing.JList jList_aPulje;
    private javax.swing.JList jList_bPulje;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Beskrivelse;
    // End of variables declaration//GEN-END:variables
}
