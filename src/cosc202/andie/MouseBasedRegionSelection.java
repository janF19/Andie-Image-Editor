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
    private ImagePanel imagePanel; 

    public MouseBasedRegionSelection(ImagePanel imagePanel) { 
        addMouseListener(this);
        addMouseMotionListener(this);
        this.imagePanel = imagePanel; 
    }

    public void setX1(int x1) { 
        this.x1 = x1; 
    }

    public int getX1() { 
        return x1; 
    }

    public void setX2(int x2) { 
        this.x2 = x2; 
    } 

    public int getX2() { 
        return x2; 
    }

    public void setY1(int y1) {  
        this.y1 = y1; 
    }

    public int getY1()  { 
        return y1; 
    }

    public void setY2(int y2) { 
        this.y2 = y2; 
    }

    public int getY2() { 
        return y2; 
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
    

    public void mouseClicked(MouseEvent e) { 
       
    }

    public void mouseEntered(MouseEvent e) { 
  
    }

    public void mouseExited(MouseEvent e) { 

    }

    public void mouseMoved(MouseEvent e) { 

    }

    public void mousePressed(MouseEvent e) { 
        setX1(e.getX());
        setY1(e.getY());
        System.out.println(getX1() + " " + getY1());
    }

    public void mouseReleased(MouseEvent e) { 
        setX2(e.getX());
        setY2(e.getY());
        repaint();
        System.out.println(getX2() + " " + getY2());
    } 

    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        repaint(); 
    }

    public void paintComponent(Graphics g) {
        // Graphics2D g2 = (Graphics2D)(g);
        super.paintComponent(g);
        g.setColor(Color.BLUE); // Set the color to blue
        System.out.println(g.getColor());
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(y1);
        System.out.println(y2);
        g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        // g.drawRect(Math.min(30, 50), Math.min(40, 60), Math.abs(50 - 30), Math.abs(60 - 40));

    }


    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
    //         JFrame frame = new JFrame("Region Selection Test");
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //         frame.setSize(400, 400);
    //         frame.getContentPane().add(new MouseBasedRegionSelection(image));
    //         frame.setLocationRelativeTo(null);
    //         frame.setVisible(true);
    //     });
    // }
    

}
