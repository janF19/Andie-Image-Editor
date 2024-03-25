package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;


/**
 * <p>
 * ImageOperation to cycle the images colours based on the user choice.
 * </p>
 * 
 * <p>
 * The images produced by this operation are coloured images, with their red,
 * blue and green values cycled and swaped around based on the users choice when prompted.
 * </p>
 * 
 * 
 * @author Maiek Anantawat
 * @version 1.0
 */
public class ColourChannelCycle {

    public class CycleColours implements ImageOperation, java.io.Serializable {

        public int choice;

        /**
         * <p>
         * Creates a new CycleColours operation and initializes and int choice
         * which represents the cycle the user has chosen when prompted.
         * </p>
         */
        CycleColours(int choice) {
            this.choice = choice;
        }

        /**
         * <p>
         * Applies the colour channel cycle to an image.
         * </p>
         * 
         * <p>
         * The cycling of the images colours, uses a nested for loop to get the height
         * and width of the image. Then takes and stores the red, blue, and green values
         * of the image, then cycles the values based on the users choice.
         * </p>
         * 
         * @param input The image to have its colours cycled.
         * @return The resulting image after having its colours cycled.
         */
        public BufferedImage apply(BufferedImage input) {
            return cycleColors(input);
        }

        private BufferedImage cycleColors(BufferedImage input) {
            int width = input.getWidth();
            int height = input.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = input.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0XFF;

                    // Cycle colors based on user's selection
                    switch (choice) {
                        case 0:
                            int temp1 = red;
                            red = green;
                            green = blue;
                            blue = temp1;
                            break;
                        case 1:
                            int temp2 = red;
                            red = blue;
                            blue = green;
                            green = temp2;
                            break;
                    }

                    int newRGB = (red << 16) | (green << 8) | blue;
                    input.setRGB(x, y, newRGB);
                }
            }
            return input;
        }
    }

    /**
     * <p>
     * Adds a prompt for the user, prompting the user to select 
     * how they want to cycle the colours of the image.
     * </p>
     * 
     * <p>
     * Uses an option pane to ask the user which cycle they want to use, then 
     * assigns an int to the option the user has chosen, which then gets 
     * stored in an int to be used as an input to determine which changes to 
     * appply to the image. 
     * </p>
     * 
     * @return The int assigned to the users choice.
     */
    public static int getUserChoice() {
        // Prompt the user for color cycle option
        String[] options = { "Red -> Green -> Blue", "Red -> Blue -> Green" };
        return JOptionPane.showOptionDialog(null, "Choose Color Cycle Option", "Color Cycle Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
}

// BufferedImage output;
// int selectedButton;
// int newRGB;
// BufferedImage cycleImage;

// public BufferedImage apply(BufferedImage input) {
// int width = input.getWidth();
// int height = input.getHeight();
// BufferedImage[] cycleImages = new BufferedImage[4];
// String[] cycleTitles = new String[4];

// cycleImages[0] = input;
// cycleTitles[0] = "Original Image";

// for (int cycle = 1; cycle <= 2; cycle++) {
// BufferedImage cycleImage = new BufferedImage(width, height,
// BufferedImage.TYPE_INT_RGB);
// for (int y = 0; y < height; y++) {
// for (int x = 0; x < width; x++) {
// int rgb = input.getRGB(x, y);
// int red = (rgb >> 16) & 0xFF;
// int green = (rgb >> 8) & 0xFF;
// int blue = rgb & 0XFF;
// int temp;
// switch (cycle) {
// case 1:
// // Cycle Red and Green channels
// temp = red;
// red = blue;
// blue = green;
// green = temp;

// break;
// case 2:
// // Cycle Green and Blue channels
// temp = red;
// red = green;
// green = blue;
// blue = temp;

// break;

// }

// newRGB = (red << 16) | (green << 8) | blue;
// cycleImage.setRGB(x, y, newRGB);

// }
// }
// cycleImages[cycle] = cycleImage;
// cycleTitles[cycle] = "Cycle " + cycle;
// }

// // Display all images in one pane
// displayPreview(cycleImages, cycleTitles);
// output = cycleImages[selectedButton];

// return output;
// }

// private void displayPreview(BufferedImage[] images, String[] titles) {
// JPanel panel = new JPanel(new GridLayout(1, 2));

// // Create buttons for each image with selection functionality
// for (int i = 1; i <= 2; i++) {
// ImageIcon icon = new ImageIcon(images[i].getScaledInstance(300, 400,
// Image.SCALE_DEFAULT));
// JButton button = new JButton(icon);
// int finalI = i;
// button.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// if (finalI != 0) {
// selectedButton = finalI;
// images[0].setData(images[finalI].getData());
// button.setIcon(new ImageIcon(images[0].getScaledInstance(300, 400,
// Image.SCALE_DEFAULT)));
// // Dispose of the preview panel frame
// JFrame previewFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
// previewFrame.dispose();

// }
// }

// });

// button.setBorder(BorderFactory.createTitledBorder(titles[i]));
// button.setHorizontalAlignment(SwingConstants.CENTER);
// panel.add(button);

// }

// JScrollPane scrollPane = new JScrollPane(panel);

// JFrame frame = new JFrame("Image Previews");
// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
// frame.getContentPane().add(scrollPane);
// frame.pack();
// frame.setLocationRelativeTo(null); // Center the frame
// frame.setVisible(true);

// }
// }

// }

/*
 * 
 * package cosc202.andie;
 * 
 * import java.awt.image.*;
 * 
 * import javax.swing.BorderFactory;
 * import javax.swing.ImageIcon;
 * import javax.swing.JButton;
 * import javax.swing.JFrame;
 * import javax.swing.JLabel;
 * import javax.swing.JPanel;
 * import javax.swing.JScrollPane;
 * import javax.swing.SwingConstants;
 * 
 * import java.awt.event.ActionListener;
 * import java.awt.event.MouseAdapter;
 * import java.awt.event.MouseEvent;
 * 
 * import java.awt.*;
 * 
 * 
 * public class ColourChannelCycle {
 * 
 * public class CycleColours implements ImageOperation, java.io.Serializable {
 * 
 * CycleColours() {
 * 
 * }
 * 
 * // public BufferedImage apply(BufferedImage input) {
 * // int width = input.getWidth();
 * // int height = input.getHeight();
 * // for (int y = 0; y < height; y++) {
 * // for (int x = 0; x < width; x++) {
 * // int rgb = input.getRGB(x, y);
 * // int red = (rgb >> 16) & 0xFF;
 * // int green = (rgb >> 8) & 0xFF;
 * // int blue = rgb & 0XFF;
 * 
 * // int temp = red;
 * // red = green;
 * // green = blue;
 * // blue = temp;
 * 
 * // int newRGB = (red << 16) | (green << 8) | blue;
 * // input.setRGB(x, y, newRGB);
 * // }
 * // }
 * 
 * // return input;
 * 
 * // }
 * 
 * public BufferedImage apply(BufferedImage input) {
 * int width = input.getWidth();
 * int height = input.getHeight();
 * BufferedImage output = new BufferedImage(width, height,
 * BufferedImage.TYPE_INT_RGB);
 * 
 * // Apply color channel cycles
 * BufferedImage cycleImage = input;
 * 
 * // Display preview images in one pane
 * //displayPreview();
 * 
 * for (int cycle = 1; cycle <= 3; cycle++) {
 * for (int y = 0; y < height; y++) {
 * for (int x = 0; x < width; x++) {
 * int rgb = input.getRGB(x, y);
 * int red = (rgb >> 16) & 0xFF;
 * int green = (rgb >> 8) & 0xFF;
 * int blue = rgb & 0XFF;
 * int temp;
 * switch (cycle) {
 * case 1:
 * // Cycle Red and Green channels
 * temp = red;
 * red = blue;
 * blue = green;
 * green = temp;
 * break;
 * case 2:
 * // Cycle Green and Blue channels
 * temp = red;
 * red = green;
 * green = blue;
 * blue = temp;
 * break;
 * }
 * 
 * int newRGB = (red << 16) | (green << 8) | blue;
 * cycleImage.setRGB(x, y, newRGB);
 * }
 * }
 * }
 * 
 * return output;
 * }
 * 
 * private void displayPreview(){
 * JPanel panel = new JPanel(new GridLayout(1, 2));
 * // Create labels for each image with selection functionality
 * String[] ionpPaths = {"/j/cosc202/"}
 * for (int i = 1; i < 3; i++) {
 * JButton button = new JButton("Option " + i, icon);
 * button.setHorizontalAlignment(SwingConstants.CENTER);
 * panel.add(button);
 * button.addActionListener(new ActionListener(){
 * 
 * @Override
 * public void actionPerformed(ActionEvent evt) {
 * // do everything here...
 * }
 * });
 * }
 * 
 * JScrollPane scrollPane = new JScrollPane(panel);
 * 
 * JFrame frame = new JFrame("Image Previews");
 * frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 * frame.getContentPane().add(scrollPane);
 * frame.pack();
 * frame.setLocationRelativeTo(null); // Center the frame
 * frame.setVisible(true);
 * }
 * }
 * 
 * }
 * 
 */