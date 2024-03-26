package cosc202.andie;


import java.awt.image.*;

/**
 * <p>
 * ImageOperation to invert the colours from the original image.
 * </p>
 * 
 * <p>
 * The images produced by this operation are flipped from
 * their original state.
 * </p>
 * 
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class ImageInversion {
    public class InvertColours implements ImageOperation, java.io.Serializable {
        /**
         * <p>
         * Creates a new InvertColours operation.
         * </p>
         */
        InvertColours() {
        }


        /**
         * <p>
         * Applies the invert colour to an image.
         * </p>
         * 
         * <p>
         * The inverting of the images colours, uses a nested for loop to get the height
         * and width of the image. Then takes and stores the red, blue, and green values
         * of the image, then does 255-colour (red, blue, and green) to invert the colours.
         * </p>
         * 
         * @param input The image to have its colours inverted.
         * @return The resulting image after having its colours inverted.
         */
        public BufferedImage apply(BufferedImage input) {
            int width = input.getWidth();
            int height = input.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = input.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0XFF;

                    // the inverting of the colours for red, green, and blue
                    red = 255 - red;
                    green = 255-green;
                    blue = 255-blue;

                    int newRGB = (red << 16) | (green << 8) | blue;
                    input.setRGB(x, y, newRGB);
                }
            }
            return input;

        }

    } 
}
