package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class DrawRectangle implements ImageOperation, java.io.Serializable {
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;


    public DrawRectangle(int topX, int topY, int width, int height){
        this.topLeftX = topX;
        this.topLeftY = topY;
        this.width = width;
        this.height = height;
    }

    public BufferedImage apply(BufferedImage input) {

       
        

        Graphics2D graphics = input.createGraphics();
        
        graphics.setColor(Color.red);
        graphics.setStroke((new BasicStroke(3)));
        graphics.fillRect(topLeftX, topLeftY, width, height);
        graphics.drawRect(topLeftX, topLeftY, width, height);
        graphics.dispose();
        

        

        

        return input;
    }


    


    
}
