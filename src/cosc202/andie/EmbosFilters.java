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
     * The array of embo filter options
     */
    private final float[][] arrayOfOptions = { { 0, 0, 0, +1, 0, -1, 0, 0, 0 },
            { +1, 0, 0, 0, 0, 0, 0, 0, -1 }, { 0, +1, 0, 0, 0, 0, 0, -1, 0 }, { 0, 0, +1, 0, 0, 0, -1, 0, 0 },
            { 0, 0, 0, -1, 0, +1, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, +1 }, { 0, -1, 0, 0, 0, 0, 0, +1, 0 },
            { 0, 0, -1, 0, 0, 0, +1, 0, 0 }, { (float)-0.5, 0, (float)+0.5, -1, 0, +1, (float)-0.5, 0, (float)+0.5 },
            { (float)-0.5, -1, (float)-0.5, 0, 0, 0, (float)+0.5, +1, (float)+0.5 }
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
     * @param radius The radius of the newly constructed MeanFilter
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

        Kernel kernel = new Kernel(3, 3, optionChosen);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
/*
 * 

    public BufferedImage applyLoop(BufferedImage input) {
        Kernel kernel = new Kernel(3, 3, optionChosen);

       
        


        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);


                if( a < 0 ){
                    System.out.println(a);
                    a = a + 128 >> 24 ;
                    System.out.println("Done A");
                    
                } 
                if( r < 0){
                    r = r + 128 >> 16;
                    System.out.println("Done R");
                } 
                if (g < 0 ){
                    g = g + 128 >> 8;
                    System.out.println("Done G");
                } 
                if ( b < 0){
                    b = b + 128;
                    System.out.println("Done B");
                }


                System.out.println("a: " +  a);
                System.out.println("r: " +  r);
                System.out.println("g: " +  g);
                System.out.println("b: " +  b);
        
    }
     */





}
