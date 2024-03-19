package cosc202.andie;

import java.awt.image.*;

public class ImageRotate {
    public class Rotate implements ImageOperation {
        private int degrees;
        public Rotate(int degrees){
            this.degrees = degrees;
        }
        public BufferedImage apply(BufferedImage input){
            int width = input.getWidth();
            int height = input.getHeight();  
            BufferedImage rotatedImage = input;
            if (degrees == 180){
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        rotatedImage.setRGB(width - x - 1, height - y - 1, input.getRGB(x, y));
                    }
                }
            } else{
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (degrees == 90){
                            rotatedImage.setRGB(height-y-1,x,input.getRGB(x, y));
                        }
                        else{
                            rotatedImage.setRGB(y,width-x-1,input.getRGB(x, y));
                        }
                    }
                }    
            }
            return rotatedImage;  
        }
    }
}
