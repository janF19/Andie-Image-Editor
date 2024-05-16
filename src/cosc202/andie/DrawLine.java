package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * <p>
 * Draws a line on top of the image using mouse selection 
 * </p>
 * 
 * @author Jan Faller     
 * @version 1.0
 * @see DrawingActions
 */
public class DrawLine implements ImageOperation, java.io.Serializable {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color outline;
    

     /**
     * <p>
     * Creates a DrawRectangle instance using the inputted coordinate, length values
     * and color
     * @param x1 The y coordinate where drawing the line starts 
     * @param y1 The y coordinate where drawing the line starts 
     * @param x2 The x coordinate where drawing the line ends 
     * @param y1 The y coordinate where drawing the line ends 
     * @param outlineColor The outlineColor 
     * </p>
     */
    public DrawLine(int x1, int y1, int x2, int y2, Color outlineColor){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.outline = outlineColor;
    }

     /**
     * <p>
     * Applies the rectangle on the image 
     * @param input the image the rectangle gets drawn on 
     * </p>
     */
    public BufferedImage apply(BufferedImage input) {

        Graphics2D graphics = input.createGraphics();
        graphics.setColor(outline);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(x1, y1, x2, y2);
        graphics.dispose();
        return input;
    }
    
}
