package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/** 
 * <p> 
 * ImageOperation to adjust the warmth level of the image based on the user choice 
 * </p>
 * 
 * <p> 
 * The images produced by this operation are coloured images, with their 
 * red colour value altered based on the users choice 
 * </p> 
 * 
 * 
 * @author Maiek Anantawat
 * @version 1.0 
 */ 
public class Warmth implements ImageOperation {

    private int warmthLevel; 

    /**
     * <p> 
     * Creates a new Warmth operation and initializes an int warmthLevel
     * which represents the warmthLevel the user has chosen when prompted 
     * </p> 
     * 
     * @param warmthLevel How much the user wants to adjust the warmth level by. 
     */
    public Warmth(int warmthLevel) {
        this.warmthLevel = warmthLevel;
    }

    /** 
     * <p> 
     * Applies the Warmth filter to an image 
     * </p> 
     * 
     * <p> 
     * The warmth adjustment of the image colour, uses a nested for-loop to get the height 
     * and width of the image. Then it takes and stores the red, blue, and green values of 
     * the image, then adjusts the red value of the image based on the user choice. 
     * </p> 
     * 
     * @param image The image to have its warmth level changed 
     * @return The resulting image after having its warmth level changed
     */
    public BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = color.getRed() + warmthLevel;
                int green = color.getGreen();
                int blue = color.getBlue();

                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));

                Color newColor = new Color(red, green, blue);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

}
