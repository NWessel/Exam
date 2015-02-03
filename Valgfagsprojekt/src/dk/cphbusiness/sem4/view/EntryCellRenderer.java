/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.sem4.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ultroman
 */
public class EntryCellRenderer extends DefaultTableCellRenderer {

    public static final Color score0 = new Color(0xd40000); // RØD
    public static final Color score1 = new Color(0xffa100); // ORANGE
    public static final Color score2 = new Color(0xffff00); // GUL
    public static final Color score3 = new Color(0x50aa00); // MØRKEGRØN
    public static final Color score4 = new Color(0x00ef00); // GRØN
    public static final Color score6 = new Color(0x307eff); // BLÅ
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cr = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null && column == 1) {
            switch((String)value){
                case "0":
                    setBackground(score0);
                    setForeground(score0);
                    break;

                case "1":
                    setBackground(score1);
                    setForeground(score1);
                    break;

                case "2":
                    setBackground(score2);
                    setForeground(score2);
                    break;

                case "3":
                    setBackground(score3);
                    setForeground(score3);
                    break;

                case "4":
                    setBackground(score4);
                    setForeground(score4);
                    break;

                case "6":
                    setBackground(score6);
                    setForeground(score6);
                    break;

                default:
                    setForeground(Color.BLACK);
                    setBackground(Color.WHITE);
                    break;
            }
        }else{
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        }
        return cr;
    }
}
