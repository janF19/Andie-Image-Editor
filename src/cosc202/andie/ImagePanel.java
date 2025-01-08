package cosc202.andie;

import java.awt.*;
import javax.swing.*;
// import java.util.Timer;
import java.awt.event.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;
    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private int clicks = 0;
    private boolean edited = false;

    //private boolean outOfBound;

    
    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */

    public ImagePanel() {

        image = new EditableImage();
        // this.regionSelection = new MouseBasedRegionSelection(this);

        addMouseListener(this);
        addMouseMotionListener(this);
        scale = 1.0;
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;

    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // System.out.println(image.hasImage());
        // System.out.println("help");
        if (image.hasImage()) {
            // System.out.println("operating");
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);

            repaint();
            revalidate();
            g2.dispose();
        }
    }

    /**
     * 
     * @return The number of clicks by the user
     */
    public int getClicks() {
        return this.clicks;
    }

    /**
     * 
     * @return boolean of if an image region has been selected or not
     */
    public boolean getEdited() {
        return this.edited;
    }

    /**
     * the starting x coordinate when mouse is first clicked for region selection
     * 
     * @param x1 x1 coordinate
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * the starting x coordinate when mouse is first clicked for region selection
     * 
     * @return the stored x1 coordinate
     */
    public int getX1() {
        if(image.getCurrentImage().getWidth()< x1){
            return image.getCurrentImage().getWidth();
        }
        if(x1<0){
            return 0;
        }
        return x1;
    }

    public int getRightX() {
       return Math.max(getX1(), getX2());
      
    }

    public int getRightY() {
        return Math.max(getY1(), getY2());
     }
 

    /**
     * the x coordinate when mouse is released for region selection
     * 
     * @param x2 x2 coordinate
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * gets the stored x coordinate when mouse is released for region selection
     * 
     * @return the stored x2 coordinate
     */
    public int getX2() {
        if(image.getCurrentImage().getWidth()< x2){
            return image.getCurrentImage().getWidth();
        }
        if(x2<0){
            return 0;
        }
        return x2;
    }

    /**
     * sets the starting y coordinate when mouse is first clicked for region
     * selection
     * 
     * @param y1 y1 coordinate
     */
    public void setY1(int y1) {
        
        this.y1 = y1;
    }

    /**
     * gets the starting y coordinate when mouse is first clicked for region
     * selection
     * 
     * @return the stored y1 coordinate
     */
    public int getY1() {
        if(image.getCurrentImage().getHeight()< y1){
            return image.getCurrentImage().getHeight();
        }
        if(y1<0){
            return 0;
        }
        return y1;
    }

    /**
     * sets the y coordinate when mouse is released for region selection
     * 
     * @param y2 y2 coordinate
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * gets the stored y coordinate when mouse is released for region selection
     * 
     * @return the stored y2 coordinate
     */
    public int getY2() {
        if(image.getCurrentImage().getHeight()< y2){
            return image.getCurrentImage().getHeight();
        }
        if(y2<0){
            return 0;
        }
        return y2;
    }

    /**
     * Gets the width for region selection
     * 
     * @return calculated width
     */
    public int getWidth2() {
        return Math.abs(getX2() - getX1());
    }
    

    /**
     * Gets the height for region selection
     * 
     * @return calculated height
     */
    public int getHeight2() {
        return Math.abs(getY2() - getY1());
    }

    /**
     * Gets the left most x coordinate. Used by region selection actions
     * 
     * @return the left most x
     */
    public int getLeftX() {
        return Math.min(getX1(), getX2());
    }

    /**
     * Gets the left most y coordinate. Used by region selection actions
     * 
     * @return the left most y
     */
    public int getLeftY() {
        return Math.min(getY1(), getY2());
    }

    /**
     * The action done when the mouse is clicked
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * The action done when the mouse is entered
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * The action done when mouse is exited
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * The action done when the mouse is moved
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseMoved(MouseEvent e) {
        if (this.clicks == 0 && getImage().check() == true && edited == true && getImage() != null) { // this.edited==true
                                                                                                     // &&
            // System.out.println("undo");
            // imagePanel.getImage().undo();
            System.out.println("undoing region");
            getImage().undo();
            repaint();
            getParent().revalidate();

            this.edited = false;
        }
        // this.edited = true;
        // clicks = 0;
        // regionAction = true;
        try {
            Thread.sleep(200); // Pause for 300 milliseconds (0.3 second)
        } catch (InterruptedException ee) {
            ee.printStackTrace();
        }

    }

    /**
     * The action done when the mouse is pressed
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mousePressed(MouseEvent e) {

        if (clicks == 0) {
            setX1(e.getX()); // starting coordinates
            setY1(e.getY());
            // System.out.println(getX1() + " " + getY1());
            clicks++;
            // paintComponent();
        }
        // System.out.println(getX());

    }

    /**
     * The action done when the mouse is released
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseReleased(MouseEvent e) {

        if (clicks == 1 && image.getCurrentImage() != null) {

            setX2(e.getX());
            this.y2 = (e.getY());
            // this.x2 = (e.getX());
            // this.y2 = (e.getY());
            // BrightnessConstrastSection b1 = new BrightnessConstrastSection(0, 15, x1, y1,
            // Math.abs(x2), Math.abs(y2));
            // int leftX = Math.min(x1, x2);
            // int leftY = Math.min(y1, y2);
            // int rightX = Math.max(x1, x2);
            // int rightY = Math.max(y1, y2);

            BrightnessConstrastSection b1 = new BrightnessConstrastSection(0, 15, getLeftX(), getLeftY(), getRightX(), getRightY());
            //if(!outOfBound){
            getImage().apply(b1);

            getParent().revalidate();
            repaint(); // finishing coordinates
           // }
            clicks = 0;
            this.edited = true;

        }
    }

    /**
     * The action done when the mouse is dragged 
     * 
     * @param e MouseEvent the event that triggers this method
     */
    public void mouseDragged(MouseEvent e) {

    }

}
