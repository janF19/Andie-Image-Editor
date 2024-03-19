package cosc202.andie;


import java.awt.image.*;


public class ImageInversion {
    public class InvertColours implements ImageOperation, java.io.Serializable {
        InvertColours() {
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


                    red = 255 - red;
                    green = 255-green;
                    blue = 255-blue;

                    int newRGB = (red << 16) | (green << 8) | blue;
                    input.setRGB(x, y, newRGB);
                }
            }
            return input;

        }

    } 
}
