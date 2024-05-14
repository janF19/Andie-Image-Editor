package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/** 
 * <p> 
 * ImageOperation to increase the vibrance of the images colours 
 * based on the users choice 
 * </p> 
 * 
 * <p> 
 * The images produced by this operation are coloured images, with their 
 * vibrance and staturation altered based on the value selected by the user.
 * </p>
 * 
 * @author Maiek Anantawat 
 * @version 1.0
 */
public class Vibrance implements ImageOperation, java.io.Serializable {

    int vibranceLevel = 0;

    /** 
     * <p> 
     * Creates a new Vibrance operation and initializes an int 
     * vibranceLevel which represents how much the user wants to 
     * change the vibrance of the image by based on the users choice 
     * when prompted 
     * </p> 
     */
    public Vibrance(int vibranceLevel) {
        this.vibranceLevel = vibranceLevel;
    }

    /** 
     * <p> 
     * Applies the vibrance change to the image. 
     * </p> 
     * 
     * <p> 
     * The change in vibrance for the image, use a nested for loop to 
     * get the height and width of the image. It then stores and converts the 
     * images colors from RGB(red, green, blue) to HSB(hue, saturation, brightness), 
     * calculates the current saturation level of the image and uses that to calculate 
     * how much to multiply the vibrance by which then multiplies the saturation by that amount.
     * It then converts the HSB back to RGB and sets the RGB values of the image to the new values. 
     * </p> 
     * 
     * @param input The image to have its vibrance changed 
     * @return The resulting image after having its vibrance changed 
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Iterate through each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the color of the current pixel
                Color color = new Color(input.getRGB(x, y));

                // Convert color to HSB (Hue, Saturation, Brightness) space
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

                // Calculate the current saturation level
                float saturation = hsb[1];

                // Calculate vibrance multiplier based on saturation level
                float vibranceMultiplier = 1.0f + (vibranceLevel / 100.0f) * (1.0f - saturation);

                // Adjust the saturation based on vibrance level
                float newSaturation = Math.min(1.0f, saturation * vibranceMultiplier);

                // Convert back to RGB color space
                int rgb = Color.HSBtoRGB(hsb[0], newSaturation, hsb[2]);

                // Set the new color in the output image
                output.setRGB(x, y, rgb);
            }
        }

        return output;
    }

}
