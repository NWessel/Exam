/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.sem4.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Ultroman
 */
public class MyListPanel extends JPanel{
    
    public MyListPanel(int posX, int posY, int width, int height){
        setBounds(posX, posY, width, height);
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        Color prevColor = g.getColor();
        g.setColor(new Color(0x000000));
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        
        
        g.setColor(prevColor);
    }
}
