package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 <p>
 * ImageOperation to crop an image.
 * </p>
 * 
 * <p>
 * The cropping image replaces the current image with the cropped area the use chose
 * </p>
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class CropImage implements ImageOperation, java.io.Serializable{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

     /**
     * <p>
     * contructs a cropping to be applied to the image
     * </p>
     * 
     * @param x1  the x coordinate when the mouse is first pressed when selecting a cropping area
     * @param y1 the y coordinate when the mouse is first pressed when selecting a cropping area
     *  @param x2 the x coordinate when the mouse is released when selecting a cropping area
     * @param y2 the y coordinate when the mouse is released when selecting a cropping area
     */
    public CropImage(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }

     /**
     * <p>
     * applies the cropping to the image
     * </p>
     * @param input the image the cropping is done to 
     */
    public BufferedImage apply(BufferedImage input) {
        int x= Math.min(x1,x2);
        int y= Math.min(y1,y2);
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);

        return input.getSubimage(x,y,width,height);
    }
    
}

