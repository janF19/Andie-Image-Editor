package cosc202.andie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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

    private int blockSize = 10;

    
    public BlockAveraging(int blockSize){
        this.blockSize= blockSize;
    }
        

    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y += blockSize) { // iterate over the pixels by blocks
            for (int x = 0; x < width; x += blockSize) {
                int redSum = 0;
                int greenSum = 0;
                int blueSum = 0;
                int count = 0;
                for (int blockY = y; blockY < y + blockSize && blockY < height; blockY++) {
                    for (int blockX = x; blockX < x + blockSize && blockX < width; blockX++) {
                        int rgb = input.getRGB(blockX, blockY);
                        redSum += (rgb >> 16) & 0xFF;
                        greenSum += (rgb >> 8) & 0xFF;
                        blueSum += rgb & 0xFF;
                        count++;
                    }
                }
                int avgR = redSum / count;
                int avgG = greenSum / count;
                int avgB = blueSum / count;

                for (int blockY = y; blockY < y + blockSize && blockY < height; blockY++) {
                    for (int blockX = x; blockX < x + blockSize && blockX < width; blockX++) {
                        output.setRGB(blockX, blockY, (avgR << 16) | (avgG << 8) | avgB);
                    }
                }
            }
        }

        return output;
    }
}