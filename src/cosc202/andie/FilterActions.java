package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.EditActions.UndoAction;

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
                KeyboardShortcuts.meanKeyStroke));
        actions.add(new SoftBlurAction(LanguageActions.prefs.getString("Softblur"), null, "Apply a soft blur",
                KeyboardShortcuts.softBluKeyStroke));
        actions.add(new SharpenFilterAction(LanguageActions.prefs.getString("Sharpenfilter"), null,
                "Apply a sharpen filter",
                KeyboardShortcuts.sharpenKeyStroke));
        actions.add(
                new MedianFilterAction(LanguageActions.prefs.getString("Medianfilter"), null, "Apply a median filter",
                        KeyboardShortcuts.medianKeyStroke));
        actions.add(new GaussianBlurAction(LanguageActions.prefs.getString("Gaussianblur"), null,
                "Apply a Gaussian blur filter",
                KeyboardShortcuts.guassainKeyStroke));
        actions.add(new EmbosFiltersAction(LanguageActions.prefs.getString("Embos"), null, "Apply an Embos Filter",
                KeyboardShortcuts.embosKeyStroke));
        actions.add(new SobelFilterAction(LanguageActions.prefs.getString("Sobel"), null, "Apply an Sobel Filter",
                KeyboardShortcuts.sobelKeyStroke));
        actions.add(
                new BlockAveragingAction(LanguageActions.prefs.getString("BlockAverage"), null, "Apply an Sobel Filter",
                        KeyboardShortcuts.blockAverageKeyStroke));
        actions.add(new RandomScatterAction(LanguageActions.prefs.getString("RandomScatter"), null,
                "Apply an Random Scatter",
                KeyboardShortcuts.randomScatterKeyStroke));

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
        MeanFilterAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
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

    /**
     * <p>
     * Action done to apply a softblur filter on the image
     * 
     * @see SoftBlur
     */
    public class SoftBlurAction extends ImageAction {

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
        SoftBlurAction(String name, ImageIcon icon,
                String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the softblur filter action is triggered
         * </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * It applies a softblur kernel on the image
         * {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // System.out.println("soft filter is being called");

            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action done to apply a sharpen filter on the image
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

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
        SharpenFilterAction(String name, ImageIcon icon,
                String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the Sharpen filter action is triggered
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It applies a sharpen fitler kernel on the image
         * {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
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
        EmbosFiltersAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the embos filter action is triggered
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbosFilterAction is triggered.
         * It applies a chosen embos filter kernel on the image out of the 6 options
         * displayed
         * on the option pane
         * {@link EmbosFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            /// System.out.println("embos is being clicked");

            // Create a panel to hold the image and the button
            // JFrame frame = new JFrame("Vertical Layout Example");
            JPanel secondaryMainPanel = new JPanel();
            JPanel MainPanel = new JPanel();

            secondaryMainPanel.setLayout(new BoxLayout(secondaryMainPanel, BoxLayout.Y_AXIS));
            MainPanel.setLayout(new BorderLayout());

            int photoNumber = 1;

            for (int i = 0; i < 2; i++) {

                JPanel subPanel = new JPanel();
                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
                // System.out.println("loop: " + i);

                for (int ii = 0; ii < 4; ii++) {
                    final int index = i * 3 + ii;
                    // System.out.println("i is : " + i + " and ii is: " + ii + "index: " + index);
                    JPanel subpanel2 = new JPanel();
                    // subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.X_AXIS));
                    ImageIcon icon3 = new ImageIcon("embos" + photoNumber + ".jpg");
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
                    // System.out.println("uwu");
                    photoNumber = photoNumber + 1;
                    // System.out.println("photoNumber:" + photoNumber);

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

    /**
     * <p>
     * Action done to apply a sobel filter on the image
     * 
     * @see EmbosFilters - contains Sobel Filters as well
     */
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
        SobelFilterAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the sobel filter action is triggered
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelFilterAction is triggered.
         * It applies a chosen sobel filter kernel on the image out of the 2 options
         * displayed
         * on the option pane
         * {@link EmbosFilter}.
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
            String[] option = { "Horizontal", "Vertical" };

            JPanel subPanel = new JPanel();

            for (int i = 0; i < 2; i++) {

                subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

                final int index = i + 8;
                JPanel subpanel2 = new JPanel();
                // subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.X_AXIS));
                ImageIcon icon3 = new ImageIcon("sobel" + photoNumber + ".jpg");
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
                JLabel text = new JLabel(option[photoNumber - 1]);
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

    /**
     * <p>
     * Action done to apply a blockaveraging effect on the image
     * 
     * @see BlockAveraging
     */
    public class BlockAveragingAction extends ImageAction {

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
        BlockAveragingAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when blockaveraging is triggered
         * </p>
         * 
         * <p>
         * This method is called whenever the BlockAveragingAction is triggered.
         * It prompts the user pick the height and width of each block and the applies
         * the block averaging
         * effect on the iamge
         * {@link BlockAveraging}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int blockSizeY = 1;
            int blockSizeX = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 20, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int optionY = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter Block Height",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            int optionX = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter Block Width",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (optionY == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (optionY == JOptionPane.OK_OPTION) {
                blockSizeY = radiusModel.getNumber().intValue();
            }

            if (optionX == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (optionX == JOptionPane.OK_OPTION) {
                blockSizeX = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new BlockAveraging(blockSizeY, blockSizeX));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * <p>
     * Action done to apply a random scatter on the image
     * 
     * @see RandomScatter
     */
    public class RandomScatterAction extends ImageAction {

        /**
         * <p>
         * Create a new random scatter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RandomScatterAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the random scatter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RandomScatterAction is triggered.
         * It prompts the user for a scatter radius, then applies the random scatter
         * with the given radius on the image
         * {@link RandomScatter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 15, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter Radius size",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new RandomScatter(radius));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * <p>
     * Action to blur an image with a Gaussian blur filter.
     * </p>
     * 
     * @see GaussianBlur
     * 
     * @author Noah Parkes
     * 
     * 
     */

    public class GaussianBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new Gaussian blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the gaussian blur action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianBlurAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = 1;

            // creates a dialogue box prompting user to input a desired radius
            SpinnerNumberModel radiusModelGaussian = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModelGaussian);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModelGaussian.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();

        }

    }

}
