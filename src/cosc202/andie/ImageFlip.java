package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * A class to flip images vertically or horizontally to create a mirrored
 * image along the relevant axis.
 * </p>
 * 
 * @author Noah Parkes
 */
public class ImageFlip {

    /**
     * <p>
     * An image operation to conduct an image flip along the horizontal or
     * vertical axis
     * </p>
     * 
     * @author Noah Parkes
     * @version 1.0
     */
    public class Flip implements ImageOperation {
        /**
         * <p>
         * Determines if vertical (true)
         * or horizontal (false) flip
         * </p>
         */
        private boolean vertical;

        /**
         * <p>
         * Constructor that takes a boolean as perameter
         * </p>
         * 
         * @param vertical boolean value. True if flip is to be vertical, false if
         *                 horizontal
         */
        public Flip(boolean vertical) {
            this.vertical = vertical;
        }

        /**
         * <p>
         * Carries out and applies flip operation input image 
         * </p>
         * 
         * @param input image to be flipped
         * @return now-flipped image
         */
        public BufferedImage apply(BufferedImage input) {
            int width = input.getWidth();
            int height = input.getHeight();
            BufferedImage flippedImage = new BufferedImage(width, height, input.getType());

            if (vertical) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int newY = height - y - 1; // sets new y value to be the mirror coordinate of y
                                                   // must also subtract 1, as image indexes from 0
                        flippedImage.setRGB(x, newY, input.getRGB(x, y));
                    }
                }
            } else {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int newX = width - x - 1;
                        flippedImage.setRGB(newX, y, input.getRGB(x, y));
                    }
                }
            }

            return flippedImage;
        }
    }
}
