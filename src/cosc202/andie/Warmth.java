package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Warmth implements ImageOperation {

    private int warmthLevel; // Adjust warmth level as needed

    public Warmth(int warmthLevel) {
        this.warmthLevel = warmthLevel;
    }

    public BufferedImage apply(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Adjust the RGB values of each pixel to add warmth
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));

                int red = color.getRed() + warmthLevel;
                int green = color.getGreen();
                int blue = color.getBlue();

                // Ensure the color values stay within 0-255 range
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
