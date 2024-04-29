package cosc202.andie;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;




public class DrawingActions {

    protected ArrayList<Action> actions;

    public DrawingActions() {

        actions = new ArrayList<Action>();

        actions.add(new DrawRectangleAction(LanguageActions.prefs.getString("DrawRectangle"), null, "DrawRectangle",Integer.valueOf(KeyEvent.VK_R)));
    }

    public JMenu createMenu(){
        JMenu drawMenu = new JMenu(LanguageActions.prefs.getString("Draw"));

        for(Action action : actions){
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

            // Create a Graphics2D object to draw on the image
            Graphics2D g2 = image.createGraphics();

            // Set the drawing color
            g2.setColor(Color.RED);

            // Draw a rectangle on the image (example)
            g2.fillRect(100, 100, 300, 200); // (x, y, width, height)

           
            // Dispose of the Graphics2D object to release resources
            g2.dispose();
        }
    }

}
