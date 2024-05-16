package cosc202.andie;

import java.awt.image.*;

/**
 * A class to flip images vertically or horizontally to create a mirrored
 * image along the relevant axis.
 * 
 * @author Noah Parkes
 */
public class ImageFlip {

    /**
     * A class to flip images vertically or horizontally to create a mirrored
     * image along the relevant axis.
     * 
     * @author Noah Parkes
     */
    public class Flip implements ImageOperation {
        /**
         * Determines if vertical (true)
         * or horizontal (false) flip
         */
        private boolean vertical;

        /**
         * Constructor that takes a boolean as perameter
         * 
         * @param vertical boolean value. True if flip is to be vertical, false if
         *                 horizontal
         */
        public Flip(boolean vertical) {
            this.vertical = vertical;
        }

        /**
         * Carries out and applies flip operation input image
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
