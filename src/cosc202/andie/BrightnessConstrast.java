package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

public class BrightnessConstrast implements ImageOperation{
    
    private double contrastFactor;
    private double brightnessFactor;

    /**
     * <p>
     * Create a new BrightnessContrast operation.
     * </p>
     */
    public BrightnessConstrast(int contrastFactor, int brightnessFactor){
        this.contrastFactor = contrastFactor;
        this.brightnessFactor = brightnessFactor;

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
        int height = input.getHeight();
        int width = input.getWidth();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
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
