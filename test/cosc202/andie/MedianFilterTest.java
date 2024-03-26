package cosc202.andie;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

public class MedianFilterTest {

    @Test
    void getMedianCalculation() {


        int radius = 1;
        int[] alphaValues = new int[(2 * radius + 1) * (2 * radius + 1)];
        int[] redValues = new int[(2 * radius + 1) * (2 * radius + 1)];
        int[] greenValues = new int[(2 * radius + 1) * (2 * radius + 1)];
        int[] blueValues = new int[(2 * radius + 1) * (2 * radius + 1)];
        int count = 0;
        // int width = 5; // width of image
        // int height = 5; // height of image
        
        // int x = 2;
        // int y = 2; // at these current pixels
        
        //int [] argb = [-,15392753,]   //9 elements assuming radius=1 so 3*3
        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                
                // int px = Math.min(Math.max(x + dx, 0), width - 1);
                // int py = Math.min(Math.max(y + dy, 0), height - 1);
                int argb = -15392753; //constant argb on all pixels 

                // Extract alpha, red, green, and blue values
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;



                // Store pixel values in arrays
                alphaValues[count] = a;
                redValues[count] = r;
                greenValues[count] = g;
                blueValues[count] = b;
                count++;

            }
        }

        Arrays.sort(alphaValues);
        Arrays.sort(redValues);
        Arrays.sort(greenValues);
        Arrays.sort(blueValues);

        // Sort pixel values
        Arrays.sort(alphaValues);
        Arrays.sort(redValues);
        Arrays.sort(greenValues);
        Arrays.sort(blueValues);


        int argb = -15392753; //constant argb on all pixels 

                // Extract alpha, red, green, and blue values
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

        // Get median pixel values
        // int medianAlpha = alphaValues[count / 2];
        int medianRed = redValues[count / 2];
        int medianGreen = greenValues[count / 2];
        int medianBlue = blueValues[count / 2];

        //Assertions.assertEquals(21, inc);
        Assertions.assertEquals(21, medianRed);
        Assertions.assertEquals(32, medianGreen);
        Assertions.assertEquals(15, medianBlue);
        Assertions.assertEquals(15, b); //median blue should be equal to blue (since argb constant) so median filter works


    }
}
