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

    private int radius;
    private Random random;

    
    public RandomScatter(int radius){
        this.radius = radius;
        this.random = new Random();
    }
        

    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xRandom=x + random.nextInt(2*radius+1)-radius;
                int yRandom=y+ random.nextInt(2*radius+1)-radius;
                xRandom = Math.max(0,Math.min(xRandom, width-1));
                yRandom = Math.max(0,Math.min(yRandom, height-1));
                output.setRGB(x,y, input.getRGB(xRandom,yRandom));
            }
        }

        return output;
    }
}