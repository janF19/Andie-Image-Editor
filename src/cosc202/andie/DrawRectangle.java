package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * Draws a rectangle on top of the image
 * </p>
 * 
 * @author Jan Faller
 * @version 1.0
 * @see DrawingActions
 */
public class DrawRectangle implements ImageOperation, java.io.Serializable {
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;
    private Color outline;
    private Color fill;

    /**
     * <p>
     * Creates a DrawRectangle instance using the inputted coordinate, length values
     * and color
     *  </p>
     * @param topX         The top x coordinate for the rectangle
     * @param topY         The top y coordinate for the rectangle
     * @param width        The width of the rectangle
     * @param height       The height of the rectangle
     * @param outlineColor The outlineColor
     * @param fillColor    The fillColor
     *                    
     */
    public DrawRectangle(int topX, int topY, int width, int height, Color outlineColor, Color fillColor) {
        this.topLeftX = topX;
        this.topLeftY = topY;
        this.width = width;
        this.height = height;
        this.fill = fillColor;
        this.outline = outlineColor;
    }

    /**
     * <p>
     * Applies the rectangle on the image
     *   </p>
     * @param input the image the rectangle gets drawn on
     *            
     */
    public BufferedImage apply(BufferedImage input) {

        Graphics2D graphics = input.createGraphics();

        graphics.setColor(fill);
        graphics.setStroke((new BasicStroke(3)));
        graphics.fillRect(topLeftX, topLeftY, width, height);
        graphics.setColor(outline);
        graphics.drawRect(topLeftX, topLeftY, width, height);
        graphics.dispose();

        return input;
    }

}
