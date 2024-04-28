package cosc202.andie;

import java.awt.image.*;


/**
 <p>
 * ImageOperation to apply a Gaussian blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian blur works by calculating a weighted average of the pixels in a surrounding
 * area.
 * </p>
 * 
 * @author Noah Parkes
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter,
     * a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Gaussian Blur filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlur
     */
    GaussianBlur(int radius) {
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
     * 
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * Apply a Gaussian blur filter to an image
     * 
     * @param input The image to apply the Gaussian blur filter to.
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int size = (2*this.radius+1) * (2*this.radius+1);
        float [] array = gaussianArrayFill(size);

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        Convolver convOp = new Convolver(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }

    /**
     * Fill a float array of size n with appropriate calculated values
     * 
     * Array is then normalised using normalise(float[] arr), this 
     * normalised array is then returned
     * 
     * @param size the size of the array
     * @return a filled and normalised gaussian kernel array of float
     */
    private float[] gaussianArrayFill(int size){
        int sideLength = (2 * this.radius) + 1;
        float[] kernArray = new float[size];
        int i = 0;
        for(int y = 0; y < sideLength; y++){
            for(int x = 0; x < sideLength; x++){
                kernArray[i] = gausCalculator(x - this.radius, y - this.radius);
                i++;
            }
        }

        kernArray = normalise(kernArray);

        return kernArray;
    }

    /**
     * Calculates and returns the appropriate float value for an element at (x,y)
     * with (0, 0) being the centre of the kernel
     * 
     * Calculates value using a 2D gaussian equation
     * 
     * 
     * 
     * @param x the x coordinate of the value in the matrix
     * @param y the y coordinate of the value in the matrix
     * @return the calculated float value
     */
    private float gausCalculator(int x, int y){
        
        //calculates variance of 
        double variance = (1.0/3.0) * this.radius;

        //plugs x, y and variance into Gaussian formula
        double result = (1 / (2 * Math.PI * Math.pow(variance, 2)) * Math.exp( - (Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(variance, 2))));
        
        return (float) result; //returns result as a float
    }

    /**
     * Takes a filled but not normalised array of float representing a Gaussian 
     * blur kernel and normalises these values by dividing each element
     * by the sum of the elements
     * 
     * @param arr the not normalised array float
     * @return the normalised array of float
     */
    private float[] normalise(float[] arr){

        float sum = 0;
        //calculates the sum
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        //divide each element, arr[i], by the sum and store back to arr[i]
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i]/sum;
        }

        return arr;
    }

}
