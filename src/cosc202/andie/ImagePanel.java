package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel  implements MouseListener, MouseMotionListener  {
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    private Timer t1 = new Timer();


    public MouseBasedRegionSelection regionSelection;  

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;
 private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private int clicks = 0;
    private boolean edited = false;
    private ImagePanel imagePanel;

    //private Timer t1 = new Timer();

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */


    //  public MouseBasedRegionSelection(ImagePanel imagePanel) {
    //     addMouseListener(this);
    //     addMouseMotionListener(this);
    //     this.imagePanel = imagePanel;
    // }
    
    public ImagePanel() {
        image = new EditableImage();
        //this.regionSelection = new MouseBasedRegionSelection(this);

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
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
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
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public  Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
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
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            
            // if(regionSelection.getClicks()==1 && regionSelection.getEdited()==true ){
            //     System.out.println("drawing blue");
            // this.regionSelection.paintComponent(g);
            // g2.dispose();
            // g.dispose();
            // }
           
            repaint();
            revalidate();
            g2.dispose();
        }
    }


   

    public int getClicks() {
        return this.clicks;
    }

    public boolean getEdited() {
        return this.edited;
    }

    // set x1
    public void setX1(int x1) {
        this.x1 = x1;
    }

    // get x1
    public int getX1() {
        return x1;
    }

    // set x2
    public void setX2(int x2) {
        this.x2 = x2;
    }

    // get x2
    public int getX2() {
        return x2;
    }

    // same for Ys
    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY1() {
        return y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY2() {
        return y2;
    }

    /*
     * 
     * JFrame frame = new JFrame();
     * Container pane = frame.getContentPane();
     * 
     * MouseBasedRegionSelection() {
     * pane.addMouseListener(this);
     * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     * frame.setSize(375, 450);
     * frame.setLocationRelativeTo(null);
     * frame.add(this);
     * frame.setVisible(true);
     * }
     * 
     */

    public void mouseClicked(MouseEvent e) {
        this.x1 = 0;
        this.x2 = 0;
        this.y1 = 0;
        this.y2 = 0;
        // System.out.println("help");
        // System.out.println(getImage().check());
       
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        if (this.clicks == 0 && getImage().check() == true && edited == true) { // this.edited==true &&
             System.out.println("undo");
            // imagePanel.getImage().undo();
            getImage().undo();
           repaint();
            getParent().revalidate();


            this.edited = false;
        }
    }

    public void mousePressed(MouseEvent e) {

        if (clicks == 0) {
            setX1(e.getX()); // starting coordinates
            setY1(e.getY());
            System.out.println(getX1() + " " + getY1());
            clicks++;
           // paintComponent();
        }
        //System.out.println(getX());

    }

    public void mouseReleased(MouseEvent e) {
        // if (clicks == 0) {

        // clicks++;
        

        if (clicks == 1) {

            // if (imagePanel.getImage().check() == true && edited == true) {
            //     System.out.println("undo2");
            //     imagePanel.getImage().undo();
            //     imagePanel.repaint();
            //     imagePanel.getParent().revalidate();
    
            //     this.edited = false;
            // }
            
            this.x2 = (e.getX());
            this.y2 = (e.getY());

            BrightnessConstrastSection b1 = new BrightnessConstrastSection(0, 15, x1, y1, Math.abs(x2), Math.abs(y2));
            System.out.println("reading apply");
           getImage().apply(b1);
            // System.out.println(cChange + " " + bChange);
            // imagePanel.repaint();
            getParent().revalidate();
            repaint(); // finishing coordinates
           // b1.dispose();

            this.edited = true;
            clicks = 0;

        }
    }

    public void mouseDragged(MouseEvent e) {

        this.x2 = e.getX();
        this.y2 = e.getY();
        repaint();

    }



}
