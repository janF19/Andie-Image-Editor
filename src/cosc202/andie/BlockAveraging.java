package cosc202.andie;

import java.awt.image.BufferedImage;


/**
 <p>
 * ImageOperation to apply Block averaging effect.
 * </p>
 * 
 * <p>
 * The block averaging effect makes the picture look more pixelated depending on the blockSize
 * area.
 * </p>
 * 
 * @author Parsa Orodes
 * @version 1.0
 */
public class BlockAveraging implements ImageOperation, java.io.Serializable  {

    private int blockSizeY = 10;
    private int blockSizeX = 10;


    
    public BlockAveraging(int blockSizeY, int blockSizeX){
        this.blockSizeY= blockSizeY;
        this.blockSizeX= blockSizeX;

    }
        

    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y += blockSizeY) { // iterate over the pixels by blocks
            for (int x = 0; x < width; x += blockSizeX) {
                int redSum = 0;
                int greenSum = 0;
                int blueSum = 0;
                int count = 0;

                //starting at y and going until BlockSize input, checking not out of bound 
                //same for x 
                for (int blockY = y; blockY < y + blockSizeY && blockY < height; blockY++) {
                    for (int blockX = x; blockX < x + blockSizeX && blockX < width; blockX++) {
                        int rgb = input.getRGB(blockX, blockY);
                        redSum += (rgb >> 16) & 0xFF; //summing each pixel red value from y to end of blocksize Y
                        greenSum += (rgb >> 8) & 0xFF; //same for green  
                        blueSum += rgb & 0xFF;  //same for blue;
                        count++;
                    }
                }
                int avgR = redSum / count; //averaging the sums 
                int avgG = greenSum / count;
                int avgB = blueSum / count;

                //iterating over each block again and setting values to the avg colour values
                for (int blockY = y; blockY < y + blockSizeY && blockY < height; blockY++) {
                    for (int blockX = x; blockX < x + blockSizeX && blockX < width; blockX++) {
                        output.setRGB(blockX, blockY, (avgR << 16) | (avgG << 8) | avgB);
                    }
                }
            }
        }

        return output;
    }
}