package cosc202.andie;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;


import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawingActions {

    protected ArrayList<Action> actions;

    public DrawingActions() {

        actions = new ArrayList<Action>();

        actions.add(new DrawRectangleAction(LanguageActions.prefs.getString("DrawRectangle"), null, "DrawRectangle",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new DrawLineAction(LanguageActions.prefs.getString("DrawLine"), null, "DrawLine", Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DrawEllipseAction(LanguageActions.prefs.getString("DrawEllipse"), null, "DrawEllipse", Integer.valueOf(KeyEvent.VK_E)));
    }

    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(LanguageActions.prefs.getString("Draw"));

        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));

        }
        return drawMenu;
    }

    public class DrawRectangleAction extends ImageAction {

        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // MouseBasedRegionSelection mouseSelector = new
            // MouseBasedRegionSelection(target);

            // g.draw(new Line2D.Double(mouseSelector.getX1(), mouseSelector.getY1(),
            // mouseSelector.getX2(), mouseSelector.getY2()));

            // Get the image from target.getImage()

            System.out.println("Draw rectangle is called");
            EditableImage image = target.getImage();
            

            //Create a Graphics2D object to draw on the image
            Graphics2D g2 = (Graphics2D)image.getCurrentImage().getGraphics();

            // Prompt the user to choose an outline color
        Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
        if (outlineColor != null) {
            // Set the outline color
            g2.setColor(outlineColor);

             // Prompt the user to choose a fill color
             Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
             if (fillColor != null) {
                 // Set the fill color
                 g2.setColor(fillColor);


            //add mouselistener that will return coordinates 
            // MouseBasedRegionSelection selector = new  MouseBasedRegionSelection(target);
            // target.addMouseListener(selector);
            // target.addMouseMotionListener(selector);
            int x = target.regionSelection.getX();
            System.out.println(x + " coordinate attempt");
            //int coordinate = selector.getX1(); 
            //System.out.println("testing coordinate" + coordinate);

            // Draw a rectangle on the image (example)
            // Draw a filled rectangle on the image using provided coordinates
            // int x = selector.getX1(); // Example x-coordinate of the rectangle's upper-left corner
            // int y = selector.getY1(); // Example y-coordinate of the rectangle's upper-left corner
            // int width = selector.getX2() - x; // Example width of the rectangle
            // int height = selector.getY2() - y; // Example height of the rectangle
            // System.out.println("x1 " + x + "y1: "+ y + "width: " + width + "height: " + height);

            // Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
            //g2.fill(rectangle);
            
            // Set the outline color again (in case it was changed by fill)
            //g2.setColor(outlineColor);

            // Draw the outline of the rectangle
            //g2.draw(rectangle);

           }
         }

            // Dispose of the Graphics2D object to release resources
            //Andie.restartAndShowGUI();
            g2.dispose();
        }
    }











    public class DrawRectangleAction2 extends ImageAction {

        DrawRectangleAction2(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            // Get the image from target.getImage()
            EditableImage image = target.getImage();
    
            // Create a Graphics2D object to draw on the image
            Graphics2D g2 = (Graphics2D) image.getCurrentImage().getGraphics();
    
            // Prompt the user to choose an outline color
            Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
            if (outlineColor != null) {
                // Set the outline color
                g2.setColor(outlineColor);
    
                // Prompt the user to choose a fill color
                Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                if (fillColor != null) {
                    // Set the fill color
                    g2.setColor(fillColor);
    
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
                            System.out.println("top left X is " + topLeftX);
                            System.out.println("top right Y is " + topLeftY);
    
                            // Draw a rectangle on the image using provided coordinates
                            g2.fillRect(topLeftX, topLeftY, width, height);
    
                            // Draw the outline of the rectangle
                            g2.drawRect(topLeftX, topLeftY, width, height);
    
                            // Repaint the target to update the image
                            target.repaint();
    
                            // Remove the mouse listener after drawing the rectangle
                            target.removeMouseListener(this);
                        }
                    });
                }
            }
    
            // Dispose of the Graphics2D object to release resources
            g2.dispose();
        }
}






    public class DrawLineAction extends ImageAction {

        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // MouseBasedRegionSelection mouseSelector = new
            // MouseBasedRegionSelection(target);

            // g.draw(new Line2D.Double(mouseSelector.getX1(), mouseSelector.getY1(),
            // mouseSelector.getX2(), mouseSelector.getY2()));

            // Get the image from target.getImage()

            System.out.println("Draw Line is called");

            // get coordinates x1, y1 = mouseBased clicked
            EditableImage image = target.getImage();

            //Create a Graphics2D object to draw on the image
            Graphics2D g2 = (Graphics2D)image.getCurrentImage().getGraphics();
            

            // Prompt the user to choose an outline color
        Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
        if (outlineColor != null) {
            // Set the outline color
            g2.setColor(outlineColor);

            

            // Draw a line on the image (example)
            // Set the stroke width
            g2.setStroke(new BasicStroke(2)); // Adjust the width as needed

            // Draw a line on the image using provided coordinates
            int x1 = 100; // Example coordinate
            int y1 = 100; // Example coordinate
            int x2 = 300; // Example coordinate
            int y2 = 200; // Example coordinate
            Line2D line = new Line2D.Double(x1, y1, x2, y2);
            g2.draw(line);
        }

            // Dispose of the Graphics2D object to release resources
            g2.dispose();
        }
    }

    public class DrawEllipseAction extends ImageAction {

        DrawEllipseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        public void actionPerformed(ActionEvent e) {
            // Get the image from target.getImage()
        EditableImage image = target.getImage();

        //Create a Graphics2D object to draw on the image
        Graphics2D g2 = (Graphics2D)image.getCurrentImage().getGraphics();;

        // Prompt the user to choose an outline color
        Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
        if (outlineColor != null) {
            // Set the outline color
            g2.setColor(outlineColor);

            // Prompt the user to choose a fill color
            Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
            if (fillColor != null) {
                // Set the fill color
                g2.setColor(fillColor);

                // Draw an ellipse on the image using provided coordinates
                int x = 100; // Example x-coordinate of the ellipse's bounding box
                int y = 100; // Example y-coordinate of the ellipse's bounding box
                int width = 200; // Example width of the ellipse's bounding box
                int height = 150; // Example height of the ellipse's bounding box
                Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);
                g2.fill(ellipse); // Fill the ellipse with the chosen fill color

                // Draw the outline of the ellipse
                g2.draw(ellipse);
            }
        }

        // Dispose of the Graphics2D object to release resources
        g2.dispose();
        }
    }

}




// // note 
// how do i call mouseSelector from drawActions if mouseSelector is implemented in andie class 

// implement seriazable for macros
// I need to call from imagePanel to get coordinates

// Draw folder - draw functions for different objects

// implement all in ImgaePanel class - if mouse realesed == true than call function which draws rectangle or ellipsis
// if rectangle pressed st to true and then some check of all objects

// rectangle = true
// triangle = flase


// color by defalt, let choose color - global variable, 

// private color col = blue

// implement even mouseselector in imagepanel