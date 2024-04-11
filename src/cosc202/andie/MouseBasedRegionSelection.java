package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseBasedRegionSelection implements MouseListener {

    JFrame frame = new JFrame(); 
    Container pane = frame.getContentPane(); 

    MouseBasedRegionSelection() { 
        pane.addMouseListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(375, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) { 
        int x = e.getClickCount(); 
        System.out.println("You clicked the mouse " + x + " times");
    }

    public void mouseEntered(MouseEvent e) { 
        pane.setBackground(Color.YELLOW);
    }

    public void mouseExited(MouseEvent e) { 
        pane.setBackground(Color.MAGENTA);
    }

    public void mousePressed(MouseEvent e) { 
        System.out.println("You have pressed the mouse");
    }

    public void mouseReleased(MouseEvent e) { 
        System.out.println("You have released the mouse");
    } 

    public static void main(String[] args) {
        new MouseBasedRegionSelection(); 

    }

}
