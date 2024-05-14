package cosc202.andie;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class ClippingTest {

    @Test
    void clippingTest() {

        int Negative = -1;
        int negResult = Math.max(0, Math.min(255, Negative));

        int positive = 256;
        int posResult = Math.max(0, Math.min(255, positive));

        System.out.println(negResult);

        Assertions.assertTrue(negResult >= 0 && negResult <= 255);
        Assertions.assertTrue(posResult >= 0 && posResult <= 255);

    }
}
