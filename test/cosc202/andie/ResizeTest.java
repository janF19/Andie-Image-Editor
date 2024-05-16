package cosc202.andie;

import org.junit.jupiter.api.Test;

// import cosc202.andie.*;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Assertions;

public class ResizeTest {

    @Test
    void resizeTest() {

        int width = 10;
        int height = 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        Resize r1= new Resize(5,3);
        BufferedImage changed = r1.apply(image);
        Assertions.assertTrue(changed.getWidth()==5);
        Assertions.assertTrue(changed.getHeight()==3);


    }
}
