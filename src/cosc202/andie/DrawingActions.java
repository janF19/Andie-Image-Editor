package cosc202.andie;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.SwingUtilities;


public class DrawingActions {

    protected ArrayList<Action> actions;
    private CropActions cropActions;

    public DrawingActions() {

        actions = new ArrayList<Action>();

        actions.add(new DrawRectangleAction(LanguageActions.prefs.getString("DrawRectangle"), null, "DrawRectangle",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new DrawLineAction(LanguageActions.prefs.getString("DrawLine"), null, "DrawLine", Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DrawEllipseAction(LanguageActions.prefs.getString("DrawEllipse"), null, "DrawEllipse", Integer.valueOf(KeyEvent.VK_E)));
        cropActions = new CropActions();
    }

    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(LanguageActions.prefs.getString("Draw"));

        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));

        }
        return drawMenu;
    }

   



    public class DrawRectangleAction extends ImageAction{

        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            if (!cropActions.getCroppingSelect()){
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {
                    
        
                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {
                        
        
                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {
                            int x1, y1, x2, y2;
        
                            public void mousePressed(MouseEvent e) {
                                x1 = e.getX();
                                y1 = e.getY();
                            }
        
                            public void mouseReleased(MouseEvent e) {
                                x2 = e.getX();
                                y2 = e.getY();
        
                                // Calculate the width and height of the rectangle
                                int width = Math.abs(x2 - x1);
                                System.out.println("width is " + width);
                                int height = Math.abs(y2 - y1);
        
                                // Determine the top-left corner coordinates
                                int topLeftX = Math.min(x1, x2);
                                int topLeftY = Math.min(y1, y2);
                                                
                                target.getImage().apply(new DrawRectangle(topLeftX, topLeftY, width, height, fillColor, outlineColor));
        
                                // Remove the mouse listener after drawing the rectangle
                                target.removeMouseListener(this);
                            }
                        });
                    }
                }
            } else{
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
    
            
    
            
        }
}


    public class DrawLineAction extends ImageAction {

        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            if (!cropActions.getCroppingSelect()){
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {
                    // Add a mouse listener to allow the user to select the points
                    target.addMouseListener(new MouseAdapter() {
                        int x1, y1, x2, y2;
                        boolean firstClick = true;
                    
                        public void mousePressed(MouseEvent e) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                if (firstClick) {
                                    x1 = e.getX();
                                    y1 = e.getY();
                                    firstClick = false;
                                } else {
                                    x2 = e.getX();
                                    y2 = e.getY();
                                    target.getImage().apply(new DrawLine(x1, y1, x2, y2, outlineColor));
                    
                                    // Remove the mouse listener after drawing the line
                                    target.removeMouseListener(this);
                                }
                            }
                        }
                    });
                }
            }else{
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } 
    }
    

    public class DrawEllipseAction extends ImageAction {

        DrawEllipseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            if (!cropActions.getCroppingSelect()){
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {
                

                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {

                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {
                            int x1, y1, x2, y2;

                            public void mousePressed(MouseEvent e) {
                                x1 = e.getX();
                                y1 = e.getY();
                            }

                            public void mouseReleased(MouseEvent e) {
                                x2 = e.getX();
                                y2 = e.getY();

                                // Calculate the width and height of the rectangle
                                int width = Math.abs(x2 - x1);
                                System.out.println("width is " + width);
                                int height = Math.abs(y2 - y1);

                                // Determine the top-left corner coordinates
                                int topLeftX = Math.min(x1, x2);
                                int topLeftY = Math.min(y1, y2);

                                //drawing functionality
                                target.getImage().apply(new DrawEllipse(topLeftX, topLeftY, width, height, fillColor, outlineColor));
                                
                                
                                // Remove the mouse listener after drawing the rectangle
                                target.removeMouseListener(this);
                            }
                        });

                        
                    }
                }
            } else{
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!", "Warning", JOptionPane.WARNING_MESSAGE);   
            }

        
        }
    }

}


