package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * The Sharpen filter works using a convolution kernel whereby making enhanceing the differences between neighbouring values.
 * </p>
 * @author Parsa Orodes
 * @version 1.0
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Construct a Sharpen filter 
     * </p>
     * 
     * <p>
     * The size of the filter is set/constant using convolution kernel.
     *  Does the opposite effect of a softblur filter   
     * </p>
     * 
     * 
     */
    SharpenFilter() {
        
    }

    /**
         * <p>
         * Overides the abtract apply from ImageOperation method for the buffered image input. 
         * Applies the sharpen filter onto the image  
         * </p>
         * 
         * @param input the name of the BufferedImage the filter will apply to 
         */
    public BufferedImage apply(BufferedImage input) {
        // The values for the kernel as a 9-element array
        float[] array = { 0, -(1 / 2.0f), 0,
               -(1 / 2.0f), 3, -(1 / 2.0f),
                0, -(1 / 2.0f), 0 };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        Convolver convOp = new Convolver(kernel, false);

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        ///accounting for negative values 
        // Clipping f1= new Clipping();
        // output= f1.apply(output);

        return output;

        // And we're done
    }
}
