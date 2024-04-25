package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.EditActions.UndoAction;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(LanguageActions.prefs.getString("Meanfilter"), null, "Apply a mean filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction(LanguageActions.prefs.getString("Softblur"), null, "Apply a soft blur",
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SharpenFilterAction(LanguageActions.prefs.getString("Sharpenfilter"), null,
                "Apply a sharpen filter",
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(
                new MedianFilterAction(LanguageActions.prefs.getString("Medianfilter"), null, "Apply a median filter",
                        Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianBlurAction(LanguageActions.prefs.getString("Gaussianblur"), null,
                "Apply a Gaussian blur filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new EmbosFiltersAction(LanguageActions.prefs.getString("Embos"), null, "Apply an Embos Filter",
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new  SobelFilterAction(LanguageActions.prefs.getString("Sobel"), null, "Apply an Sobel Filter",
        Integer.valueOf(KeyEvent.VK_E)));

    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(LanguageActions.prefs.getString("Filter"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class SoftBlurAction extends ImageAction {
        SoftBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SharpenFilterAction extends ImageAction {

        SharpenFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to apply embos filter to an image
     * 
     * @see EmbosFilters
     * 
     * 
     */
    public class EmbosFiltersAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbosFiltersAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link EmbosFilters}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Create a panel to hold the image and the button
            // JFrame frame = new JFrame("Vertical Layout Example");
            JPanel secondaryMainPanel = new JPanel();
            JPanel MainPanel = new JPanel();

            secondaryMainPanel.setLayout(new BoxLayout(secondaryMainPanel, BoxLayout.Y_AXIS));
            MainPanel.setLayout(new BorderLayout());

            // String images = { }
            // final int[] options= {0,1,2,3,4,5,6,7};
            int photoNumber = 1;

            for (int i = 0; i < 2; i++) {

                JPanel subPanel = new JPanel();
                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

                for (int ii = 0; ii < 4; ii++) {
                    final int index = i * 3 + ii;
                    JPanel subpanel2 = new JPanel();
                    // subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.X_AXIS));
                    ImageIcon icon3 = new ImageIcon("embos" + photoNumber + ".png");
                    // System.out.println(i+ii+1);
                    JLabel imageLabel = new JLabel(icon3);

                    imageLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JOptionPane.showMessageDialog(MainPanel, "Filter Success!");
                            target.getImage().apply(new EmbosFilters(index));
                            target.repaint();
                            target.getParent().revalidate();

                        }

                    });
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
                    JLabel text = new JLabel("Option " + photoNumber);
                    // System.out.println(photoNumber);
                    // System.out.println("i is: " + i + "and ii is: " + ii);
                    // System.out.println();

                    photoNumber++;

                    text.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

                    subpanel2.setLayout(new BorderLayout());
                    subpanel2.add(imageLabel, BorderLayout.CENTER);
                    subPanel.add(Box.createRigidArea(new Dimension(10, 10))); // Add horizontal spacing
                    subpanel2.add(text, BorderLayout.SOUTH); // Align text label to the bottom
                    subPanel.add(subpanel2);
                }

                JButton button = new JButton("Undo");

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        /// JOptionPane.showMessageDialog(null, "Filter Success!");
                        EditActions x = new EditActions();
                        UndoAction x_a = x.new UndoAction(LanguageActions.prefs.getString("Undo"), null, "Undo", null);
                        x_a.actionPerformed(null);
                    }
                });

                JPanel subpanel2 = new JPanel(); // where the original image and undo button is getting added on the
                                                 // left
                // subpanel2.setLayout(new BorderLayout());
                subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

                ImageIcon original = new ImageIcon("hedgehog-test-cute2.png");
                JLabel originalImage = new JLabel(original);
                JLabel text = new JLabel("original");

                originalImage.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
                text.setHorizontalAlignment(SwingConstants.CENTER);
                button.setHorizontalAlignment(SwingConstants.CENTER);

                subpanel2.add(button, BorderLayout.NORTH);
                subpanel2.add(text, BorderLayout.NORTH);
                subpanel2.add(originalImage, BorderLayout.CENTER);

                secondaryMainPanel.add(subPanel);
                MainPanel.add(secondaryMainPanel, BorderLayout.CENTER);
                MainPanel.add(subpanel2, BorderLayout.WEST);

            }

            // Show the JOptionPane with the panel containing the image and the button
            int choice = JOptionPane.showOptionDialog(null, MainPanel, "Embos Filters",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (choice == JOptionPane.OK_OPTION) {

            } else if (choice == JOptionPane.CANCEL_OPTION) {
                System.out.println("User clicked Cancel.");
            } else {
                System.out.println("User closed the dialog without making a choice.");
            }

        }

    }

    public class SobelFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link EmbosFilters}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Create a panel to hold the image and the button
            // JFrame frame = new JFrame("Vertical Layout Example");
            JPanel secondaryMainPanel = new JPanel();
            JPanel MainPanel = new JPanel();

            secondaryMainPanel.setLayout(new BoxLayout(secondaryMainPanel, BoxLayout.Y_AXIS));
            MainPanel.setLayout(new BorderLayout());

           
            int photoNumber = 1;
            String [] option= {"Horizontal", "Vertical"};

            JPanel subPanel = new JPanel();


            for (int i = 0; i < 2; i++) {

                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

                final int index = i + 8;
                JPanel subpanel2 = new JPanel();
                // subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.X_AXIS));
                ImageIcon icon3 = new ImageIcon("sobel" + photoNumber + ".png");
                JLabel imageLabel = new JLabel(icon3);

                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JOptionPane.showMessageDialog(MainPanel, "Filter Success!");
                        target.getImage().apply(new EmbosFilters(index));
                        target.repaint();
                        target.getParent().revalidate();

                    }

                });

                System.out.println(index);

                imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
                JLabel text = new JLabel(option[photoNumber-1]);
                // System.out.println(photoNumber);
                // System.out.println("i is: " + i + "and ii is: " + ii);
                // System.out.println();

                photoNumber++;

                text.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

                // JPanel subPanel = new JPanel();
                subpanel2.setLayout(new BorderLayout());
                subpanel2.add(imageLabel, BorderLayout.CENTER);
                // subpanel2.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical
                // spacing
                subPanel.add(Box.createRigidArea(new Dimension(10, 10))); // Add horizontal spacing
                subpanel2.add(text, BorderLayout.SOUTH); // Align text label to the bottom
                subPanel.add(subpanel2);
            }

            JButton button = new JButton("Undo");

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    /// JOptionPane.showMessageDialog(null, "Filter Success!");
                    EditActions x = new EditActions();
                    UndoAction x_a = x.new UndoAction(LanguageActions.prefs.getString("Undo"), null, "Undo", null);
                    x_a.actionPerformed(null);
                }
            });

            JPanel subpanel2 = new JPanel(); // where the original image and undo button is getting added on the left
            // subpanel2.setLayout(new BorderLayout());
            subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));

            ImageIcon original = new ImageIcon("hedgehog-test-cute2.png");
            JLabel originalImage = new JLabel(original);
            JLabel text = new JLabel("original");

            originalImage.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
            text.setHorizontalAlignment(SwingConstants.CENTER);
            button.setHorizontalAlignment(SwingConstants.CENTER);

           
            subpanel2.add(originalImage, BorderLayout.CENTER);
            subpanel2.add(button, BorderLayout.SOUTH);
            subpanel2.add(text, BorderLayout.SOUTH);

            secondaryMainPanel.add(subPanel);
            MainPanel.add(secondaryMainPanel, BorderLayout.CENTER);
            MainPanel.add(subpanel2, BorderLayout.WEST);

            // Show the JOptionPane with the panel containing the image and the button
            int choice = JOptionPane.showOptionDialog(null, MainPanel, "Sobel Filters",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (choice == JOptionPane.OK_OPTION) {

            } else if (choice == JOptionPane.CANCEL_OPTION) {
                System.out.println("User clicked Cancel.");
            } else {
                System.out.println("User closed the dialog without making a choice.");
            }

        }

    }

}
