package cosc202.andie;

import java.awt.image.*;
import java.util.Arrays;
import java.awt.*;

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

        Kernel kernel = new Kernel(3, 3, optionChosen);

        // float[][] kernel = new float[3][3];

        // for (int row = 0; row < kernel.length; row++) {
        // for (int column = 0; column < kernel[row].length; column++) {
        // kernel[row][column] = optionChosen[row * 3 + column];
        // //System.out.println(optionChosen[row * 3 + column]);
        // }
        // }

        // float[][] arr = new float[input.getWidth()][input.getHeight()];

        // for (int i = 0; i < input.getHeight(); i++) {
        // for (int j = 0; j < input.getWidth(); j++) {
        // arr[j][i] = input.getRGB(j, i) ;
        // }
        // }
        
        ///-----
        //ConvolveOp convOp = new ConvolveOp(kernel);
        // BufferedImage output = new BufferedImage(input.getColorModel(),
        //         input.copyData(null),
        //         input.isAlphaPremultiplied(), null);
        // convOp.filter(input, output);

        ConvolveOp convOp = new ConvolveOp(new Kernel(3, 3, optionChosen));
        BufferedImage output = convOp.filter(input, null);

        // int result=0;
        // int radius = 1; // kernel radius for 3*3 pixel kernel
        // BufferedImage output = new BufferedImage(input.getColorModel(),
        // input.copyData(null),
        // input.isAlphaPremultiplied(), null);

        // for (int height = 1; height < input.getHeight()-1; height++) {
        // for (int width = 1; width < input.getWidth()-1; width++) {
        // result= 0;

        // for (int dy = -radius; dy <= radius; dy++) {

        // for (int dx = -1; dx <= radius; dx++) {
        // result+= ( kernel[radius + dx][radius + dy]) * ( arr[width + dx][height +
        // dy]);

        // }

        // }
        // int argb= result;
        // int a = (argb & 0xFF000000) >> 24;
        // int r = (argb & 0x00FF0000)>>16 ;
        // int g = (argb & 0x0000FF00) >>8 ;
        // int b = argb & 0x000000FF;
        // int[] colourChannel= {r,g,b,a};

        // // for(int i=0; i<4;i++){
        // // colourChannel[i] = Math.min(255, Math.max(0,colourChannel[i]));
        // // }

        // //clipping

        // // if(result[width][height] < 0){
        // // result[width][height] = 128;
        // // }
        // // else if(result[width][height] > 255) {
        // // result[width][height] += 128;
        // // }
        // //argb= (a<<24) | (r<<16) | (g<<8)| b;
        // Color c= new
        // Color(colourChannel[0],colourChannel[1],colourChannel[2],colourChannel[3]);
        // output.setRGB(width, height, c.getRGB());

        // }

        // }
        for (int y = 0; y < output.getHeight(); y++) {
            for (int x = 0; x < output.getWidth(); x++) {

                int rgb = output.getRGB(x, y);
                int r = (rgb & 0x00FF0000) >> 16;
                int g = (rgb & 0x0000FF00) >> 8;
                int b = (rgb & 0x000000FF);

                r += 128;
                g += 128;
                b += 128;
    
                // Clamping values 
                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));
                     
                
                Color c= new Color(r,g,b);
               // Color c= new Color(colourChannel[0],colourChannel[1],colourChannel[2],colourChannel[3]);
                //c.getRGB
                output.setRGB(x, y,  c.getRGB()  );

            }
        }

        return output;
    }

    // public BufferedImage applyLoop(BufferedImage input) {
    // Kernel kernel = new Kernel(3, 3, optionChosen);

    // for (int y = 0; y < input.getHeight(); ++y) {
    // for (int x = 0; x < input.getWidth(); ++x) {
    // int argb = input.getRGB(x, y);
    // int a = (argb & 0xFF000000) >> 24;
    // int r = (argb & 0x00FF0000) >> 16;
    // int g = (argb & 0x0000FF00) >> 8;
    // int b = (argb & 0x000000FF);

    // if (a < 0) {
    // //System.out.println(a);
    // a = a + 128 >> 24;
    // // System.out.println("Done A");

    // }
    // if (r < 0) {
    // r = r + 128 >> 16;
    // // System.out.println("Done R");
    // }
    // if (g < 0) {
    // g = g + 128 >> 8;
    // // System.out.println("Done G");
    // }
    // if (b < 0) {
    // b = b + 128;
    // //System.out.println("Done B");
    // }

    // // System.out.println("a: " + a);
    // // System.out.println("r: " + r);
    // // System.out.println("g: " + g);
    // // System.out.println("b: " + b);

    // }

    // }

    // }

}