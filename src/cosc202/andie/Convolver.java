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
        if(src == null){
            return null;
        }
        float[] kernArray = new float[kernel.getHeight() * kernel.getWidth()];
        kernArray = kernel.getKernelData(kernArray);
        for(int x = radius; x < (src.getWidth() - radius); x++){ //iterates through internel
            for(int y = radius; y < (src.getHeight() - radius); y++){
                double redResult = 0;
                double greenResult = 0;
                double blueResult = 0;

                int index = 0;

                    for(int kx = -radius; kx <= radius; kx++){
                        for(int ky = -radius; ky <= radius; ky++){
                    
                        int colour = 0;
                        if(((x + kx) >= 0) && ((x + kx) <= src.getWidth())){ //inside and top/bottom (within x bound)
                            if(((y + ky) >= 0) && ((y + ky) <= src.getHeight())){ //within x and y bounds
                                colour = src.getRGB(x + kx, y + ky);
                            }else if((y + ky) < 0){ //within x bounds but above img
                                colour = src.getRGB(x + kx, 0);    
                            }else{  //within x bounds but below img
                                colour = src.getRGB(x + kx, src.getHeight());
                            }
                        }else if((x + kx) < 0){ //left side
                            if(((y + ky) >= 0) && ((y + ky) <= src.getHeight())){ //left side in y bound
                                colour = src.getRGB(0 , y + ky);
                            }else if((y + ky) < 0){ //top left corner
                                colour = src.getRGB(0 , 0);
                            }else{ //bottom left corner
                                colour = src.getRGB(0 , src.getHeight());
                            }
                        }else{ //right side
                            if(((y + ky) >= 0) && ((y + ky) <= src.getHeight())){ //right side in y bound
                                colour = src.getRGB(src.getWidth() , y + ky);
                            }else if((y + ky) < 0){ //top right corner
                                colour = src.getRGB(src.getWidth() , 0);
                            }else{ //bottom right corner
                                colour = src.getRGB(src.getWidth() , src.getHeight());
                            }
                        }
                        
                        //colour *= kernArray[index];
                        float red =   (colour & 0x00ff0000) >> 16;
                        float green = (colour & 0x0000ff00) >> 8;
                        float blue =   colour & 0x000000ff;
                        red *= kernArray[index];
                        green *= kernArray[index];
                        blue *= kernArray[index];

                        redResult += red;
                        greenResult += green;
                        blueResult += blue;

                        index++;

                        }
                    }

                    int newColour = (int)redResult;
                    newColour = (newColour << 8) + (int)greenResult;
                    newColour = (newColour << 8) + (int)blueResult;

                    int oldVal = src.getRGB(x, y); //for debugging purposes
                    dst.setRGB(x, y, (int)newColour);
            }
        }

        return dst;
    }

}
