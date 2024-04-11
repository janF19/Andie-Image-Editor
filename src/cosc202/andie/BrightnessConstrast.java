package cosc202.andie;

import java.awt.image.*;

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

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int rgb = input.getRGB(i, j);

                int newRGB = calculator((rgb >> 16) & 0xFF) | calculator((rgb >> 8) & 0xFF) | calculator(rgb & 0XFF);
                
                input.setRGB(i, j, newRGB);

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
        return (int)newVal;
    }
}
