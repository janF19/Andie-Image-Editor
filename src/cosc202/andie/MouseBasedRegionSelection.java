package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MouseBasedRegionSelection extends JPanel implements MouseListener, MouseMotionListener {

    private int x1 = 0;
    private int x2 = 0;
    private int y1 = 0;
    private int y2 = 0;
    private int clicks = 0;
    private boolean mouseReleased = false;
    private ImagePanel imagePanel;

    public MouseBasedRegionSelection(ImagePanel imagePanel) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.imagePanel = imagePanel;
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

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (clicks == 1) {
            setX1(e.getX()); // starting coordinates
            setY1(e.getY());
        }
        // System.out.println(getX1() + " " + getY1());
    }

    public void mouseReleased(MouseEvent e) {

        if (clicks == 0) {
            clicks++;
        } else if (clicks == 1) {
            clicks = 0;
            this.mouseReleased = true;

            this.x2 = (e.getX());
            this.y2 = (e.getY());
            
            BrightnessConstrastSection b1 = new BrightnessConstrastSection(0, 25, x1, y1, Math.abs(x2), Math.abs(y2));
            System.out.println("reading apply");
            imagePanel.getImage().apply(b1);
            
            // System.out.println(cChange + " " + bChange);
            // imagePanel.repaint();
            imagePanel.getParent().revalidate();
            repaint(); // finishing coordinates
            // System.out.println(getX2() + " " + getY2())
            Graphics g = getGraphics();
            g.setColor(Color.RED);
            g.fillRect(x1, y1, x2, y2);
            g.dispose();
        }
    }

    public void mouseDragged(MouseEvent e) {
        this.x2 = e.getX();
        this.y2 = e.getY();
        repaint();
    }

    // public void paintComponent(Graphics g) {
    //     // Graphics2D g2 = (Graphics2D)(g);
    //     super.paintComponent(g);
    //     g.setColor(Color.BLUE); // Set the color to blue
    //     // System.out.println(g.getColor());
    //     // System.out.println(x1);
    //     // System.out.println(x2);
    //     // System.out.println(y1);
    //     // System.out.println(y2);

    //     // brightening
    //     if (mouseReleased) {
    //         System.out.println("oopsie");
    //         g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    //         this.mouseReleased = false;
    //     }
    //     // BufferedImage original= imagePanel.getImage().getCurrentImage();
    //     // BufferedImage subImageOutput =
    //     // b1.apply(original.getSubimage(x1,x2,Math.abs(x2-x1),Math.abs(x2-x1)));
    //     // //b1 applies the brightness filter on the sub(rectangular)image part of the
    //     // original image

    //     // redraws the original image...replacing the subimage portion with the
    //     // subimageoutput that is brighter
    //     // Graphics2D g2 = original.createGraphics();
    //     // g2.drawImage(subImageOutput, x1, y1, Math.abs(x2-x1), Math.abs(x2-x1), null);

    // }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // BufferedImage image = new BufferedImage(400, 400,
    // BufferedImage.TYPE_INT_ARGB);
    // JFrame frame = new JFrame("Region Selection Test");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setSize(400, 400);
    // frame.getContentPane().add(new MouseBasedRegionSelection(image));
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // });
    // }

}
