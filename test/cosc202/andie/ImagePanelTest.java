package cosc202.andie;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;



public class ImagePanelTest{
   
    @Test void 
    InitialDummyTest(){

    }

    @Test
    void getZoomInitialValue2(){
        ImagePanel testpanel =  new ImagePanel();
        Assertions.assertEquals(100.0,testpanel.getZoom());
    }
}


