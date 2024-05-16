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
public class CropImage implements ImageOperation{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public CropImage(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    public BufferedImage apply(BufferedImage input) {
        int x= Math.min(x1,x2);
        int y= Math.min(y1,y2);
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);

        return input.getSubimage(x,y,width,height);
    }
    
}

