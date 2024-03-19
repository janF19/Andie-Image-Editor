package cosc202.andie;

import java.awt.image.*;

public class ColourChannelCycle  {

    public class CycleColours implements ImageOperation, java.io.Serializable {

        CycleColours() {

        }

        public BufferedImage apply(BufferedImage input) {
            int width = input.getWidth();
            int height = input.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = input.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0XFF;

                    int temp = red;
                    red = green;
                    green = blue;
                    blue = temp;

                    int newRGB = (red << 16) | (green << 8) | blue;
                    input.setRGB(x, y, newRGB);
                }
            }

            return input;

        }

    }

}
