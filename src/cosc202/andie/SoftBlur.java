package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * The Softblur filter works opposite to a sharpen filter using a convolution kernel by reducing differences between neighbouring values of the kernel.
 * </p>
 * @author Parsa Orodes
 * @version 1.0
 */

public class SoftBlur implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Construct a Softblur filter
     * </p>
     * 
     * <p>
     * The size of the filter is set/constant using convolution kernel.
     * Does the opposite effect of a sharpen filter
     * </p>
     * 
     * 
     */
    SoftBlur() {

    }

    /**
         * <p>
         * Overides the abtract apply from ImageOperation method for the buffered image input. 
         * Applies the softblur filter onto the image  
         * </p>
         * 
         * @param input the name of the BufferedImage the filter will apply to 
         */
    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        System.out.println("uwu");
        float[] array = { 0, 1 / 8.0f, 0,
                    1 / 8.0f, 1 / 2.0f, 1 / 8.0f,
                     0, 1 / 8.0f, 0 };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        Convolver convOp = new Convolver(kernel,false);

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        // And we're done
        return output;
    }
}