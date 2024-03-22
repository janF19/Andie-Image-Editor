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
