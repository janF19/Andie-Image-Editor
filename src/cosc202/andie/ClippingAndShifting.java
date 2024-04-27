package cosc202.andie;

import java.awt.image.*;
import java.util.Arrays;
import java.awt.*;


public class ClippingAndShifting {

    public ClippingAndShifting() {

    }

    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {

                int rgb = input.getRGB(x, y);
                int r = (rgb & 0x00FF0000) >> 16;
                int g = (rgb & 0x0000FF00) >> 8;
                int b = (rgb & 0x000000FF);

                //Shifting the raw values by 128
                r += 128;
                g += 128;
                b += 128;

                // Clamping values
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                Color c = new Color(r, g, b);
                
                input.setRGB(x, y, c.getRGB());

            }
        }
    return input;
    }
}
