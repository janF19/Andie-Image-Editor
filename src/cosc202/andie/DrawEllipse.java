package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class DrawEllipse implements ImageOperation, java.io.Serializable {
    private int x;
    private int y;
    private int width;
    private int height;

    public DrawEllipse(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BufferedImage apply(BufferedImage input) {

        // I have to pass color as well

        Graphics2D graphics = input.createGraphics();

        graphics.setColor(Color.red);
        graphics.setStroke((new BasicStroke(3)));


        graphics.fillOval(x, y, width, height);
        graphics.drawOval(x, y, width, height);

        graphics.dispose();

        return input;
    }

}
