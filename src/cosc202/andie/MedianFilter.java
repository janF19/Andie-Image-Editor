package cosc202.andie;

import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;


//first attempt to get MedianFilter done

public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MediaFilter
     */

    MedianFilter(int radius) {
        this.radius = radius;

    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     * 
     * 
     *      this is not done, dont know how to do it
     */
    MedianFilter() {
        this.radius = 1;
    }

    public BufferedImage apply(BufferedImage input) {
        // Some work to do here still, just copy from internet for now
        //
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Loop through each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Create arrays to store pixel values in the local neighborhood
                int[] alphaValues = new int[(2 * radius + 1) * (2 * radius + 1)];
                int[] redValues = new int[(2 * radius + 1) * (2 * radius + 1)];
                int[] greenValues = new int[(2 * radius + 1) * (2 * radius + 1)];
                int[] blueValues = new int[(2 * radius + 1) * (2 * radius + 1)];
                int count = 0;

                // Loop through the local neighborhood
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int px = Math.min(Math.max(x + dx, 0), width - 1);
                        int py = Math.min(Math.max(y + dy, 0), height - 1);
                        int argb = input.getRGB(px, py);

                        // Extract alpha, red, green, and blue values
                        int a = (argb >> 24) & 0xFF;
                        int r = (argb >> 16) & 0xFF;
                        int g = (argb >> 8) & 0xFF;
                        int b = argb & 0xFF;

                        // Store pixel values in arrays
                        alphaValues[count] = a;
                        redValues[count] = r;
                        greenValues[count] = g;
                        blueValues[count] = b;
                        count++;
                    }
                }

                // Sort pixel values
                Arrays.sort(alphaValues);
                Arrays.sort(redValues);
                Arrays.sort(greenValues);
                Arrays.sort(blueValues);

                // Get median pixel values
               // int medianAlpha = alphaValues[count / 2];
                int medianRed = redValues[count / 2];
                int medianGreen = greenValues[count / 2];
                int medianBlue = blueValues[count / 2];

                // Set filtered pixel value
                Color filteredColor = new Color(medianRed, medianGreen, medianBlue);
                filteredImage.setRGB(x, y, filteredColor.getRGB());
            }
        }

        return filteredImage;

    }

}
