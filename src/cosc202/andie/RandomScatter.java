package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Random;


/**
 <p>
 * ImageOperation to apply random scattering effect.
 * </p>
 * 
 * <p>
 * The random scattering effect replaces each pixel in the output image with a random pixel from within some radius of the original location
 * area.
 * </p>
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class RandomScatter implements ImageOperation, java.io.Serializable  {

    /**
     * An int for the radius selected
     */
    private int radius;
    /**
     * A random value to chose in the radius
     */
    private Random random;

    /**
     * Constructor to initialize the RandomScatter object with a given radius
     * 
     * @param radius The raius within which to scatter the pixels randomly
     */
    public RandomScatter(int radius){
        this.radius = radius;
        this.random = new Random();
    }
        
    /** 
     * <p> 
     * Applies the Randomly scattered radius 
     * </p> 
     * 
     * <p> 
     * The user choses the radius to randomly scatter and then pixels are scattered randomly 
     * depending on the radius the user has chosen
     * </p> 
     * 
     * @param input The image to have its pixels scattered randomly around the radius 
     * @return The resulting image after having its pixels randomly scattered
     */
    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();

        // Gets the output for image 
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Nested for loop for all the pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calculate random values given the radius
                int xRandom=x + random.nextInt(2*radius+1)-radius;
                int yRandom=y+ random.nextInt(2*radius+1)-radius;

                // Ensures the values are inbounds
                xRandom = Math.max(0,Math.min(xRandom, width-1));
                yRandom = Math.max(0,Math.min(yRandom, height-1));
                output.setRGB(x,y, input.getRGB(xRandom,yRandom));
            }
        }

        return output;
    }
}