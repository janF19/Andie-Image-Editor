package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Vibrance implements ImageOperation {

    int vibranceLevel = 0;

    public Vibrance(int vibranceLevel) {
        this.vibranceLevel = vibranceLevel;
    }

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
