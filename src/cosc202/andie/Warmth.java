package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Warmth implements ImageOperation{

    private double warmthLevel; // Adjust warmth level as needed

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
                int rgb = image.getRGB(x, y);
                
                int red = calculator((rgb & 0x00ff0000) >> 16);
                int green = (rgb & 0x0000ff00) >> 8;
                int blue = rgb & 0x000000ff;

                Color newColour = new Color(red, green, blue);
                
                result.setRGB(x, y, newColour.getRGB());
            }
        }

        return result;
    }

    private int calculator(int redVal){
        double newVal = redVal * (1 + warmthLevel/250);
        newVal = Math.max(0, Math.min(255, newVal));
        return (int)newVal;
    }

}
