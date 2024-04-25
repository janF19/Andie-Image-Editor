package cosc202.andie;
import java.awt.image.*;
import java.awt.*;

public class Convolver2 {
    
    /** The rarius of the filter kernel */
    private int radius;

    /** An instance of kernel representing a filter operation */
    private Kernel kernel;

    /** 
     * Constructor method takes as parameter a kernel that represents a filtering operation
     * to be carried out on a BufferedImage object.
     * @param kernel a Java Kernel instance object representing an image filtering operation
     */
    public Convolver2(Kernel kernel){
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
        float[] kernArray = new float[kernel.getHeight() * kernel.getWidth()];
        kernArray = kernel.getKernelData(kernArray);
        for(int x = radius; x < (src.getWidth() - radius); x++){ //iterates through internel
            for(int y = radius; y < (src.getHeight() - radius); y++){
                double result = 0;
                int index = 0;

                    for(int kx = -radius; kx <= radius; kx++){
                        for(int ky = -radius; ky <= radius; ky++){

                        int colour = src.getRGB(x + kx,y + ky);

                        //colour *= kernArray[index];
                        float red =   (colour & 0x00ff0000) >> 16;
                        float green = (colour & 0x0000ff00) >> 8;
                        float blue =   colour & 0x000000ff;
                        red *= kernArray[index];
                        green *= kernArray[index];
                        blue *= kernArray[index];

                        colour = (int)red;
                        colour = (colour << 8) + (int)green;
                        colour = (colour << 8) + (int)blue;

                        result += colour;
                        index++;

                        }
                    }
                    int oldVal = src.getRGB(x, y);
                    dst.setRGB(x, y, (int)result);
            }
        }



        return dst;
    }

}
