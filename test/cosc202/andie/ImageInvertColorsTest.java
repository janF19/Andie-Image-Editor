package cosc202.andie;

import org.junit.jupiter.api.Test;
import java.awt.Color;

import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Assertions;
//This class tests the colour BLUE is inverted properly... which if does will make it yellow
/// it  validates that the BLUE has inverted comppletely to its inverted colour yellow 

public class ImageInvertColorsTest {

    @Test
    void testBlue() {

        ImageInversion.InvertColours c1 = new ImageInversion().new InvertColours();

        int rgbBlue = Color.BLUE.getRGB();
        int blue = rgbBlue & 0XFF;

        int blueT = 255 - blue;
        System.out.println(255 + " minus" + blue);
        System.out.println("equals" + (255 - blue));

        BufferedImage image2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        image2.setRGB(0, 0, Color.BLUE.getRGB());

        System.out.println("blueT: " + blueT);
        // System.out.println("imageT: " + c1.apply(image2).getRGB(0, 0) & 0XFF); AVOID
        // USING THIS LINE IT WILL LEAD TO FAILURE

        Assertions.assertEquals(blueT, c1.apply(image2).getRGB(0, 0) & 0XFF);
        Assertions.assertEquals(blueT, Color.YELLOW.getRGB() & 0XFF);

    }

    @Test
    void testRed() {

        ImageInversion.InvertColours c1 = new ImageInversion().new InvertColours();

        int rgbRed = Color.BLUE.getRGB();
        int red = (rgbRed >> 16) & 0xFF;

        int redT = 255 - red;
        System.out.println(255 + " minus" + red);
        System.out.println("equals" + (255 - red));

        BufferedImage image2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        image2.setRGB(0, 0, Color.BLUE.getRGB());

        System.out.println("redT: " + redT);

        Assertions.assertEquals(redT, (c1.apply(image2).getRGB(0, 0) >> 16) & 0XFF);
        Assertions.assertEquals(redT, (Color.YELLOW.getRGB() >> 16) & 0XFF);

    }

    @Test
    void testGreen() {

        ImageInversion.InvertColours c1 = new ImageInversion().new InvertColours();

        int rgbGreen = Color.BLUE.getRGB();
        int green = (rgbGreen >> 8) & 0xFF;

        int greenT = 255 - green;
        System.out.println(255 + " minus" + green);
        System.out.println("equals" + (255 - green));

        BufferedImage image2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        image2.setRGB(0, 0, Color.BLUE.getRGB());

        System.out.println("greenT: " + greenT);

        System.out.println("colour yellows's green:" + ((Color.YELLOW.getRGB() >> 8) & 0XFF));
        Assertions.assertEquals(greenT, (c1.apply(image2).getRGB(0, 0) >> 8) & 0XFF);
        Assertions.assertEquals(greenT, (Color.YELLOW.getRGB() >> 8) & 0XFF);

    }

}
