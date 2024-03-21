package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Resize implements ImageOperation, java.io.Serializable{

private int targetWidth;
private int targetHeight;

    public Resize(int width, int height ){
        this.targetWidth=width;
        this.targetHeight=height;
    }
    public BufferedImage apply(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}