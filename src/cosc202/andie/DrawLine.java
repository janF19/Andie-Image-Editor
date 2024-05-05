package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class DrawLine implements ImageOperation, java.io.Serializable {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color outline;
    

    public DrawLine(int x1, int y1, int x2, int y2, Color outlineColor){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.outline = outlineColor;
    }

    public BufferedImage apply(BufferedImage input) {

        Graphics2D graphics = input.createGraphics();
        graphics.setColor(outline);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(x1, y1, x2, y2);
        graphics.dispose();
        return input;
    }
    
}
