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

    }

    public void mouseEntered(MouseEvent e) { 

    }

    public void mouseExited(MouseEvent e) { 

    }

    public void mousePressed(MouseEvent e) { 

    }

    public void mouseReleased(MouseEvent e) { 

    }
  
}
