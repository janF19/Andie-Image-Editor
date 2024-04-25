package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseBasedRegionSelection extends JPanel implements MouseListener {

    public int x1 = 0; 
    public int x2 = 0; 
    public int y1 = 0; 
    public int y2 = 0; 

    public MouseBasedRegionSelection() { 
        addMouseListener(this);
    }

    /*

    JFrame frame = new JFrame(); 
    Container pane = frame.getContentPane(); 

    MouseBasedRegionSelection() { 
        pane.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(375, 450);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }
     * 
     */
    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    } 

    public void mouseClicked(MouseEvent e) { 
       
    }

    public void mouseEntered(MouseEvent e) { 
  
    }

    public void mouseExited(MouseEvent e) { 

    }

    public void mousePressed(MouseEvent e) { 
        x1 = e.getX(); 
        y1 = e.getY(); 
        System.out.println(x1 + " " + y1);
    }

    public void mouseReleased(MouseEvent e) { 
        x2 = e.getX(); 
        y2 = e.getY(); 
        repaint();
        System.out.println(x2 + " " + y2);
    } 

    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        repaint(); 
    }

    /*
     * public static void main(String[] args) {
        MouseBasedRegionSelection m1 = new MouseBasedRegionSelection(); 
        

    }
     */
    

}
