package cosc202.andie;
import java.awt.image.*;
import java.awt.*;

/**
 * This class carries out a kernel convolution operation on a BufferedImage instance
 * object. When filtering the edges of an image, it utilises the nearest pixel value
 * to the non-existent pixels outside the edge (i.e. (0, 5) is nearest valid to (-1,5)).
 * 
 */
public class Convolver {

/** The rarius of the filter kernel */
private int radius;
/** An instance of kernel representing a filter operation */
private Kernel kernel;

    /** 
     * Constructor method takes as parameter a kernel that represents a filtering operation
     * to be carried out on a BufferedImage object.
     * @param kernel a Java Kernel instance object representing an image filtering operation
     */
    public Convolver(Kernel kernel){
        this.radius = ((kernel.getHeight() - 1)/2);
        this.kernel = kernel;
    }

    /**
     * Method that applies an image filtering operation on an object of type BufferedImage
     * and returns the filtered image. Takes as parameter the image to be filtered, and a
     * BufferedImage object to store the filtered image.
     * 
     * Iterates over the inner part of the image ("inner" based on the radius of the kernel)
     * then implements a separate method of filter application on edge pixels.
     * 
     * @param src
     * @param dst
     * @return
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dst){
        float[][] kernArray = new float[kernel.getHeight()][kernel.getWidth()];
        float[] kern = new kernel[kernel.getHeight()*kernel.getWidth()];
        kern = kernel.getKernelData(kern);
        int index = 0;

        for(int i = 0; i < kernArray.length; i++){
            for(int j = 0; j < kernArray[i].length; j++){
                kernArray[i][j] =  kern[index];
                index++;
            }
        }

        for(int x = radius; x < (src.getWidth() - radius); x++){
            for(int y = radius; y < (src.getHeight() - radius); y++){
                Color targetColor = new Color(src.getRGB(x,y));
                int red = targetColor.getRed();
                int green = targetColor.getGreen();
                int blue = targetColor.getBlue();

                double kRGB = 0;

                for(int i = 0; i < kernel.getWidth(); i++){
                    for(int j = 0; j < kernel.getHeight(); j++){
                        int pixelX = ((x - radius) + i);
                        int pixelY = ((y - radius) + j);

                        int pixelRGB = src.getRGB(pixelX, pixelY);

                        kRGB += pixelRGB*kernArray[i][j];


                    }
                }
                
            }
        } 
        
        return dst;
    }

}
