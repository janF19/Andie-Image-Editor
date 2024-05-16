package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/** 
 * <p>
 * Image operation that adjusts the brightness and contrast of a section of an image.
 * <p>
 * 
 * <p>
 * This class is intended to be used in conjunction with mouse based region selection,
 * which passes the constructor the coordinates of the rectangular region to be adjusted.
 * <p>
 * 
 * <p>
 * The images produced by this operation had each (red, green and blue) colour channel
 * of their pixels adjusted using a mathematical formula (in calculator() method) 
 * utilising the given constrast and brightness factors. This factor represents a
 * a percentage change in contrast and brightness respectively, from -100% to +100%.
 * </p>
 */
public class BrightnessConstrastSection implements ImageOperation{
    
    private double contrastFactor;
    private double brightnessFactor;
    private int x1;
    private int y1;
    private int width; 
    private int height;


    /**
     * <p>
     * Create a new BrightnessContrast operation.
     * </p>
     */
    public BrightnessConstrastSection(int contrastFactor, int brightnessFactor,int x1,int y1, int width,int height ){
        this.contrastFactor = contrastFactor;
        this.brightnessFactor = brightnessFactor;
        this.x1=x1;
        this.y1=y1;
        this.width=width;
        this.height= height; 
    }

    /**
     * <p>
     * Apply brightness and contrast adjustment operation to an image.
     * </p>
     * 
     * <p>
     * Applies a mathematical equation to RGB values of each pixel using
     * values of contrastFactor and brightnessFactor
     * </p>
     * 
     * @param input The image to be adjusted
     * @return The resulting adjusted image.
     */
    public BufferedImage apply(BufferedImage input) { 
        
        for(int x = x1; x < this.width; x++){
            for(int y = y1; y < this.height; y++){
                int rgb = input.getRGB(x, y);
                
                int red = calculator((rgb & 0x00ff0000) >> 16);
                int green = calculator((rgb & 0x0000ff00) >> 8);
                int blue = calculator(rgb & 0x000000ff);

                Color newColour = new Color(red, green, blue);
                
                input.setRGB(x, y, newColour.getRGB());

            }
        }


        return input;
    }

    /**
     * <p>
     * Calculate integer value using brightness-contrast formula.
     * <p>
     * 
     * <p>
     * Takes an integer value representing the red, green, or blue value
     * of a pixel, applies a mathematical formula to this value based on the
     * contrastFactor and brightnessFactor values.
     * <p>
     * 
     * @param pixelVal integer value represending the red, green, or blue value of a pixel
     * @return the new calculated integer value of that colour channel.
     */
    private int calculator(int pixelVal){
        double newVal = (1 + (contrastFactor/100))*((double)pixelVal - 127.5)+ 127.5 * (1 + (brightnessFactor/100));
        newVal = Math.max(0, Math.min(255, newVal));
        return (int)newVal;
    }
}
