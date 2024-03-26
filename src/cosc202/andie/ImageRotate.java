package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageRotate to rotate the image based on the user choice.
 * </p>
 * 
 * <p>
 * The images produced by this operation are rotated either 90 
 * degrees left, 90 degrees right, or 180 degrees based on the 
 * users choice when prompted.
 * </p>
 * 
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class ImageRotate {
    public class Rotate implements ImageOperation {
        private int degrees;
        /**
         * <p>
         * Creates a new Rotate operation and initializes and int degrees
         * which represents the roatation the user has chosen when prompted.
         * </p>
         */
        public Rotate(int degrees){
            this.degrees = degrees;
        }
        /**
         * <p>
         * Applies the image rotate to an image.
         * </p>
         * 
         * <p>
         * The rotating of the image, uses a nested for loop to change 
         * the height and width of the image.
         * </p>
         * 
         * @param input The image to have its colours cycled.
         * @return The resulting image after being rotated.
         */
        public BufferedImage apply(BufferedImage input){
            int width = input.getWidth();
            int height = input.getHeight();  
            BufferedImage rotatedImage;
            if (degrees ==90 || degrees == -90){
                rotatedImage = new BufferedImage(height, width, input.getType());
            } else{
                rotatedImage = new BufferedImage(width, height, input.getType());    
            }
            for (int y=0; y<height; y++){
                for (int x=0; x<width; x++){
                    int newX;
                    int newY;

                    // the degrees the user wants changes the height and/or width of the image.
                    if (degrees==90){
                        newX = height -y-1;
                        newY=x;
                    } else if (degrees==180){
                        newX = width-x-1;
                        newY=height-y-1;   
                    } else{
                        newX=y;
                        newY=width-x-1;
                    }
                    rotatedImage.setRGB(newX,newY,input.getRGB(x,y));
                }
            }
            return rotatedImage;  
        }
    }
}
