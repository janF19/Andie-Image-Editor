package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Alter menu.
 * </p>
 * 
 * <p>
 * The Alter menu contains actions that affect how the image is displayed in the
 * application.
 * These actions do not affect the contents of the image itself, just the way it
 * is displayed.
 * 
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class AlterActions {

    /**
     * A list of actions for the Alter menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Alter menu actions.
     * </p>
     */
    public AlterActions() {
        actions = new ArrayList<Action>();
        actions.add(new Rotate90RightAction("Rotate 90 Right", null, "Rotate 90 Degrees Right",Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new Rotate90LeftAction("Rotate 90 Left", null, "Rotate 90 Degrees Left", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new Rotate180Action("Rotate 180", null, "Rotate 180 Degrees", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new ResizeAction("Resize", null, "Resizes dimentions", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new VerticalFlipAction("Flip Vertical", null, "Flips image vertically", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new HorizontalFlipAction("Flip Horizontal", null, "Flips image horizontally", Integer.valueOf(KeyEvent.VK_1)));
    }

    /**
     * <p>
     * Create a menu containing the list of Alter actions.
     * </p>
     * 
     * @return The Alter menu UI element.
     */
    public JMenu createMenu() {
        JMenu alterMenu = new JMenu("Alter");

        for (Action action : actions) {
            alterMenu.add(new JMenuItem(action));
        }

        return alterMenu;
    }
    /**
     * <p>
     * Action to rotate the image 90 degrees left
     * </p>
     */
    public class Rotate90LeftAction extends ImageAction {

        /**
         * <p>
         * Create a new Rotate90LeftAction action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate90LeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Rotate90LeftAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate90LeftAction is triggered.
         * It rotates the image 90 degrees left
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImageRotate image = new ImageRotate();
            ImageOperation rotation = image.new Rotate(-90);
            EditableImage currentImage = target.getImage();
            currentImage.apply(rotation);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate the image 90 degrees right
     * </p>
     */
    public class Rotate90RightAction extends ImageAction {

        /**
         * <p>
         * Create a new Rotate90RightAction
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate90RightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Rotate90RightAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate90RightAction is triggered.
         * It rotates the image 90 degrees right
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImageRotate image = new ImageRotate();
            ImageOperation rotation = image.new Rotate(90);
            EditableImage currentImage = target.getImage();
            currentImage.apply(rotation);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate the image 180 degrees 
     * </p>
     */
    public class Rotate180Action extends ImageAction {

         /**
         * <p>
         * Create a new Rotate180Action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate180Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Rotate180Action action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * It rotates the image 180 degrees
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImageRotate image = new ImageRotate();
            ImageOperation rotation = image.new Rotate(180);
            EditableImage currentImage = target.getImage();
            currentImage.apply(rotation);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to resize the image 
     * @see Resize
     * </p>
     */
    public class ResizeAction extends ImageAction {

        /**
         * <p>
         * Create a new ResizeAction
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * Resizes the action given the input of dimensions width and height by the user 
         * Max dimensions to resize to is 1500
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
         int width = target.getImage().getCurrentImage().getWidth();
            int height = target.getImage().getCurrentImage().getHeight();
            //int width=1;
            //int height=1;
            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(width, 1, 1500, 1);
            
            SpinnerNumberModel radiusModel2 = new SpinnerNumberModel(height, 1, 1500, 2);

            JSpinner radiusSpinner = new JSpinner(radiusModel);
            JSpinner radiusSpinner2 = new JSpinner(radiusModel2);

            int optionWidth = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter Width",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            int optionHeight = JOptionPane.showOptionDialog(null, radiusSpinner2, "Enter Height",
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (optionWidth == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (optionWidth == JOptionPane.OK_OPTION) {
                width = radiusModel.getNumber().intValue();
            }

            if ( optionHeight == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (optionHeight == JOptionPane.OK_OPTION) {
                height = radiusModel2.getNumber().intValue();
            }



            // Create and apply the filter
            target.getImage().apply(new Resize(width,height));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class VerticalFlipAction extends ImageAction {

        /**
         * <p>
         * Create a new vertical flip action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        VerticalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the vertical flip is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenevert the vertical flip action is triggered.
         * It creates an Flip with vertical = true
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImageFlip image = new ImageFlip();
            ImageOperation flipVertical = image.new Flip(true);
            EditableImage currentImage = target.getImage();
            currentImage.apply(flipVertical);
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class HorizontalFlipAction extends ImageAction {

        /**
         * <p>
         * Create a new horizontal flip action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        HorizontalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the horizontal flip is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenevert the horizontal flip action is triggered.
         * It creates an Flip with vertical = false
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            ImageFlip image = new ImageFlip();
            ImageOperation flipHorizontal = image.new Flip(false);
            EditableImage currentImage = target.getImage();
            currentImage.apply(flipHorizontal);
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
