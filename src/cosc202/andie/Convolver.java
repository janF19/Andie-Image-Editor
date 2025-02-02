package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * This class carries out a kernel convolution operation on a BufferedImage
 * instance
 * object. When filtering the edges of an image, it utilises the nearest pixel
 * value
 * to the non-existent pixels outside the edge (i.e. (0, 5) is the nearest valid
 * to (-1,5)).
 * 
 */
public class Convolver {

    /** The rarius of the filter kernel */
    private int radius;
    /** An instance of kernel representing a filter operation */
    private Kernel kernel;
    /** A boolean used to determine if shifting is required for the given filter kernel */
    private boolean shift; 

    /**
     * Constructor method takes as parameter a kernel that represents a filtering
     * operation
     * to be carried out on a BufferedImage object.
     * 
     * @param kernel a Java Kernel instance object representing an image filtering
     *               operation
     */
    public Convolver(Kernel kernel, boolean shift) {
        this.radius = ((kernel.getHeight() - 1) / 2);
        this.kernel = kernel;
        this.shift=shift;
    }

    /**
     * Constructor method takes as parameter a kernel that represents a filtering
     * operation
     * to be carried out on a BufferedImage object.
     * 
     * @param kernel a Java Kernel instance object representing an image filtering
     *               operation
     */
    public Convolver(Kernel kernel) {
        this.radius = ((kernel.getHeight() - 1) / 2);
        this.kernel = kernel;
        this.shift = false;
    }

    /**
     * Method that applies an image filtering operation on an object of type
     * BufferedImage
     * and returns the filtered image. Takes as parameter the image to be filtered,
     * and a
     * BufferedImage object to store the filtered image.
     * 
     * Iterates through image and applies kernel filter. Handles out of bound errors
     * by
     * using the nearest valid pixel.
     * 
     * @param src
     * @param dst
     * @return
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dst) {
        if (dst == null) {
            dst = new BufferedImage(src.getColorModel(), src.copyData(null), src.isAlphaPremultiplied(), null);
        }

        float[] kernArray = new float[kernel.getHeight() * kernel.getWidth()];

        kernArray = kernel.getKernelData(kernArray);

        for (int x = 0; x < src.getWidth(); x++) { // iterates through internel
            for (int y = 0; y < src.getHeight(); y++) {
                double redResult = 0; //stores results from kernel
                double greenResult = 0;
                double blueResult = 0;

                int index = 0; //handles index of 1D kernel array

                //iterates through each value in the kernel
                for (int kx = -radius; kx <= radius; kx++) {
                    for (int ky = -radius; ky <= radius; ky++) {

                        int colour = 0; //value to store pixel integer value to

                        /* 
                         * If statements determine where to take the pixel value from,
                         * depending on the location of the target pixel, and the position of
                         * the kernel pixel. First statement is the most common condition; the kernel
                         * pixel lies within the image. All other statements determine what nearest pixel to
                         * use for invalid pixels 
                         */
                        if (((x + kx) >= 0) && ((x + kx) <= (src.getWidth() - 1))) { // is within centre x
                            if (((y + ky) >= 0) && ((y + ky) <= (src.getHeight() - 1))) { // and within centre y
                                colour = src.getRGB(x + kx, y + ky);
                            } else if ((y + ky) < 0) { // within x but above image
                                colour = src.getRGB(x + kx, 0);
                            } else if ((y + ky) > (src.getHeight() - 1)) { // within x but under image
                                colour = src.getRGB(x + kx, src.getHeight() - 1);
                            }
                        } else if ((x + kx) < 0) { // is on left side
                            if (((y + ky) >= 0) && ((y + ky) <= (src.getHeight() - 1))) {
                                colour = src.getRGB(0, y + ky);
                            } else if ((y + ky) < 0) { // top left corner
                                colour = src.getRGB(0, 0);
                            } else if ((y + ky) > (src.getHeight() - 1)) { // bottom left corner
                                colour = src.getRGB(0, src.getHeight() - 1);
                            }
                        } else if ((x + kx) > (src.getWidth() - 1)) { // is on right side
                            if (((y + ky) >= 0) && ((y + ky) <= (src.getHeight() - 1))) { // is within y
                                colour = src.getRGB(src.getWidth() - 1, y + ky);
                            } else if ((y + ky) < 0) { // top right corner
                                colour = src.getRGB(src.getWidth() - 1, 0);
                            } else if ((y + ky) > (src.getHeight() - 1)) { // bottom right corner
                                colour = src.getRGB(src.getWidth() - 1, src.getHeight() - 1);
                            }
                        }

                        float red = (colour & 0x00ff0000) >> 16; //extracts each colour from pixel colour int
                        float green = (colour & 0x0000ff00) >> 8;
                        float blue = colour & 0x000000ff;
                        red *= kernArray[index]; //applies appropriate kernel value
                        green *= kernArray[index];
                        blue *= kernArray[index];

                        redResult += red; //adds to running sum
                        greenResult += green;
                        blueResult += blue;

                        index++; //moves up index

                    }
                }
               
                if(shift){ //shifts results (primarily for emboss filters)
                redResult += 128;
                greenResult += 128;
                blueResult += 128;
                }

                // Clamping values
                redResult = Math.max(0, Math.min(255, redResult));
                greenResult = Math.max(0, Math.min(255, greenResult));
                blueResult = Math.max(0, Math.min(255, blueResult));

                //creates colour from results
                Color c = new Color((int)redResult,(int) greenResult,(int) blueResult);
                
                //sets output image pixel at (x, y) to new colour value
                dst.setRGB(x, y, c.getRGB());
            }
        }

        return dst;//returns output image
    }

}
