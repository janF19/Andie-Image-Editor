package cosc202.andie;

import java.awt.image.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;


public class ColourChannelCycle  {

    public class CycleColours implements ImageOperation, java.io.Serializable {

        CycleColours() {

        }

        // public BufferedImage apply(BufferedImage input) {
        //     int width = input.getWidth();
        //     int height = input.getHeight();
        //     for (int y = 0; y < height; y++) {
        //         for (int x = 0; x < width; x++) {
        //             int rgb = input.getRGB(x, y);
        //             int red = (rgb >> 16) & 0xFF;
        //             int green = (rgb >> 8) & 0xFF;
        //             int blue = rgb & 0XFF;

        //             int temp = red;
        //             red = green;
        //             green = blue;
        //             blue = temp;

        //             int newRGB = (red << 16) | (green << 8) | blue;
        //             input.setRGB(x, y, newRGB);
        //         }
        //     }

        //     return input;

        // }

         public BufferedImage apply(BufferedImage input) {
            int width = input.getWidth();
            int height = input.getHeight();
            BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Apply color channel cycles
            BufferedImage[] cycleImages = new BufferedImage[4];
            String[] cycleTitles = new String[4];
            cycleImages[0] = input;
            cycleTitles[0] = "Original Image";

            for (int cycle = 1; cycle <= 3; cycle++) {
                cycleImages[cycle] = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = input.getRGB(x, y);
                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = rgb & 0XFF;

                        int temp;
                        switch (cycle) {
                            case 1:
                                // Cycle Red and Green channels
                                temp = red;
                                red = blue; 
                                blue = green; 
                                green = temp; 
                                break;
                            case 2:
                                // Cycle Green and Blue channels
                                temp = red;
                                red = green; 
                                green = blue;
                                blue = temp; 
                                break;
                        }

                        int newRGB = (red << 16) | (green << 8) | blue;
                        cycleImages[cycle].setRGB(x, y, newRGB);
                    }
                }
                cycleTitles[cycle] = "Cycle " + cycle;
            }

            // Display all images in one pane
            displayPreview(cycleImages, cycleTitles, input);

            return output;
        }

         private void displayPreview(BufferedImage[] images, String[] titles, BufferedImage originalImage) {
            JPanel panel = new JPanel(new GridLayout(1, 2));
            // Create labels for each image with selection functionality
            for (int i = 1; i < 3; i++) {
                ImageIcon icon = new ImageIcon(images[i]);
                JLabel label = new JLabel(icon);
                int finalI = i;
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("dhjks");
                        // Apply changes to the original image based on the selected image
                        if (finalI == 1) {
                            originalImage.setData(images[1].getData());
                        } else if (finalI == 2) {
                            originalImage.setData(images[2].getData());
                        }
                    }
                });
                
                label.setBorder(BorderFactory.createTitledBorder(titles[i]));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(label);
            }

            JScrollPane scrollPane = new JScrollPane(panel);

            JFrame frame = new JFrame("Image Previews");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        }
    }

}
