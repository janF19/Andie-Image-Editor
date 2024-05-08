package cosc202.andie;


import org.junit.jupiter.api.Test;

import cosc202.andie.GaussianBlur;
import cosc202.andie.FilterActions;
import java.text.DecimalFormat; 



import org.junit.jupiter.api.Assertions;



public class GaussianFilterTest{
   
    @Test
    void getGaussianCalculation(){
        DecimalFormat fmt = new DecimalFormat();  //make an instance fmt.setMaximumFractionDigits(1); //specify format required 
        fmt.setMaximumFractionDigits(3);
        double variance = (1.0/3.0) * 1;
        int x=0;
        int y=0;
        double result = (1 / (2 * Math.PI * Math.pow(variance, 2)) * Math.exp( - (Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(variance, 2))));

        Assertions.assertEquals("1.432",fmt.format(result));
    }
}

