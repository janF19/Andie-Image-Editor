package cosc202.andie;

import java.awt.image.*;


/**
 * <p>
 * ImageOperation to apply a Resize the image dimensions (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convolution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class EmbosFilters implements ImageOperation, java.io.Serializable {

    /**
     * The array of embo filter options. The last two are sobel filters 
     */
    private final float[][] arrayOfOptions = { { 0, 0, 0, +1, 0, -1, 0, 0, 0 },
            { +1, 0, 0, 0, 0, 0, 0, 0, -1 }, { 0, +1, 0, 0, 0, 0, 0, -1, 0 }, { 0, 0, +1, 0, 0, 0, -1, 0, 0 },
            { 0, 0, 0, -1, 0, +1, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, +1 }, { 0, -1, 0, 0, 0, 0, 0, +1, 0 },
            { 0, 0, -1, 0, 0, 0, +1, 0, 0 },
            { (float) -0.5, 0, (float) +0.5, -1, 0, +1, (float) -0.5, 0, (float) +0.5 },
            { (float) -0.5, -1, (float) -0.5, 0, 0, 0, (float) +0.5, +1, (float) +0.5 }
    };
    private float[] optionChosen;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param emboOption The user choosing which of the embos/sobel filters they
     *                   want to use
     */
    EmbosFilters(int emboOption) {
        this.optionChosen = arrayOfOptions[emboOption];
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {

        //System.out.println(Arrays.toString(optionChosen));

        //Kernel kernel = new Kernel(3, 3, optionChosen);

        Convolver convOp = new Convolver(new Kernel(3, 3, optionChosen),true);
        BufferedImage output = convOp.filter(input, null);

        //ClippingAndShifting f1= new ClippingAndShifting();
        //output= f1.apply(output);

        return output;
        // for (int y = 0; y < output.getHeight(); y++) {
        //     for (int x = 0; x < output.getWidth(); x++) {

        //         int rgb = output.getRGB(x, y);
        //         int r = (rgb & 0x00FF0000) >> 16;
        //         int g = (rgb & 0x0000FF00) >> 8;
        //         int b = (rgb & 0x000000FF);

        //         r += 128;
        //         g += 128;
        //         b += 128;
    
        //         // Clamping values 
        //         r = Math.max(0, Math.min(255, r));
        //         g = Math.max(0, Math.min(255, g));
        //         b = Math.max(0, Math.min(255, b));
                     
                
        //         Color c= new Color(r,g,b);
        //        // Color c= new Color(colourChannel[0],colourChannel[1],colourChannel[2],colourChannel[3]);
        //         //c.getRGB
        //         output.setRGB(x, y,  c.getRGB()  );

        //     }
        // }

        //return output;
    }

    
}