package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>
 * Draws an ellipse on top of the image using mouse selection
 * </p>
 * 
 * @author Jan Faller
 * @version 1.0
 * @see DrawingActions
 */
public class DrawEllipse implements ImageOperation, java.io.Serializable {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color outline;
    private Color fill;

    /**
     * <p>
     * Creates a DrawEllipse instance using the inputted coordinate, length values
     * and color
     * @param x The x coordinate for the ellipse 
     * @param y The y coordinate for the ellipse 
     * @param width The width of the ellipse
     * @param height The height of the ellipse
     * @param outlineColor The outlineColor 
     * @param fillColor The fillColor
     * </p>
     */
    public DrawEllipse(int x, int y, int width, int height, Color outlineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fillColor;
        this.outline = outlineColor;

    }

    /**
     * <p>
     * Applies the ellipse on the image 
     * @param input the image the ellipse gets drawn on 
     * </p>
     */
    public BufferedImage apply(BufferedImage input) {

        // I have to pass color as well

        Graphics2D graphics = input.createGraphics();

        graphics.setColor(fill);
        graphics.setStroke((new BasicStroke(3)));

        graphics.fillOval(x, y, width, height);
        graphics.setColor(outline);
        graphics.drawOval(x, y, width, height);

        graphics.dispose();

        return input;
    }

}
