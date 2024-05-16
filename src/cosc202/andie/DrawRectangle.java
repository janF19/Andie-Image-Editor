package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawRectangle implements ImageOperation, java.io.Serializable {
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;
    private Color outline;
    private Color fill;


    public DrawRectangle(int topX, int topY, int width, int height, Color outlineColor, Color fillColor){
        this.topLeftX = topX;
        this.topLeftY = topY;
        this.width = width;
        this.height = height;
        this.fill = fillColor;
        this.outline = outlineColor;
    }

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
