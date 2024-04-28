package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 <p>
 * ImageOperation to crop an image.
 * </p>
 * 
 * <p>
 * The cropping image replaces the current image with the cropped area the use chose
 * </p>
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class CropImage {
    public static BufferedImage apply(BufferedImage originalImage, int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = x2 - x1;
        int height = y2 - y1;

        BufferedImage croppedImage = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g2d = croppedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, x, y, x + width, y + height, null);
        g2d.dispose();

        return croppedImage;
    }
    
}
