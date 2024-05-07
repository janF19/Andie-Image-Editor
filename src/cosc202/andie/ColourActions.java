package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;





/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
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
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * 
     * 
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(LanguageActions.prefs.getString("Greyscale"), null, "Convert to greyscale",
                KeyboardShortcuts.greyScaleKeyStroke));

        actions.add(new CycleColourChannelAction(LanguageActions.prefs.getString("ColourCycleChannel"), null,
                "Cycles current colour", KeyboardShortcuts.colorChannelKeyStroke));
        actions.add(new InvertColourAction(LanguageActions.prefs.getString("Invert"), null,
                "Inverts the Colours of the image", KeyboardShortcuts.invertKeyStroke));

        // actions.add(new RegionSelectionAction("Region Selection", null, "Select a region", null));
        actions.add(new BrightConAction("Brightness/Contrast", null, "Adjust Brightness and Contrast", KeyboardShortcuts.brightnessKeyStroke));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(LanguageActions.prefs.getString("Colour"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to cycle the colours of an image based on the users choice.
     * </p>
     * 
     * @see ColourChannelCycle
     */
    public class CycleColourChannelAction extends ImageAction {

        /**
         * <p>
         * Create a new colour-channel-cycle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CycleColourChannelAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the colour-channel-cycle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ColourChannelCycleAction is triggered.
         * It cycles the colours of the image based on the users choice.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int choice = ColourChannelCycle.getUserChoice();
            target.getImage().apply(new ColourChannelCycle().new CycleColours(choice));
            target.repaint();
            target.getParent().revalidate();

        }
    }

    public class InvertColourAction extends ImageAction {

        InvertColourAction(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageInversion().new InvertColours());
            target.repaint();
            target.getParent().revalidate();

        }
    }

    // /**
    //  * Action to enable mouse-based region selection.
    //  */
    // public class RegionSelectionAction extends ImageAction {

    //     RegionSelectionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
    //         super(name, icon, desc, mnemonic);
    //     }

    //     public void actionPerformed(ActionEvent e) {
    //         MouseBasedRegionSelection regionSelection = new MouseBasedRegionSelection(target);
    //         target.addMouseListener(regionSelection);
    //         target.addMouseMotionListener(regionSelection);
    //     }

    // }

    public class BrightConAction extends ImageAction{

        int bChange;
        int cChange;

        BrightConAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
            this.bChange = 0;
            this.cChange = 0;
        }

        public void actionPerformed(ActionEvent e){
            
            JFrame optionFrame = new JFrame("Brightness/Contrast");

            JSlider bSlider = new JSlider(-100, 100);
            bSlider.setMajorTickSpacing(25);
            bSlider.setMinorTickSpacing(5);
            bSlider.setPaintTicks(true);

            JSlider cSlider = new JSlider(-100, 100);
            cSlider.setMajorTickSpacing(20);
            cSlider.setMinorTickSpacing(5);
            cSlider.setPaintTicks(true);

            JButton applyButton = new JButton("Apply");
            
            JButton cancelButton = new JButton("Cancel");

            JPanel panel = new JPanel();
            panel.add(new JLabel("Brightness Change (%)"));
            panel.add(bSlider);
            panel.add(new JLabel("Contrast Change (%)"));
            panel.add(cSlider);
            panel.add(applyButton);
            panel.add(cancelButton);
            

            // ChangeListener that is notified every time the value in the jslider is
            // updated by the user
            bSlider.addChangeListener(new ChangeListener() {
                @Override
                // is called when state changes, and updates image shown behind the
                // SpinnerNumberModel
                public void stateChanged(ChangeEvent e) {
                    if (bSlider.getValueIsAdjusting())
                        return;
                    // if this is the first time number is altered, change to show it has been
                    // altered and then apply filter

                    bChange = bSlider.getValue();
                    cChange = cSlider.getValue();
                }
            });

            // ChangeListener that is notified every time the value in the jslider is
            // updated by the user
            cSlider.addChangeListener(new ChangeListener() {
                @Override
                // is called when state changes, and updates image shown behind the
                // SpinnerNumberModel
                public void stateChanged(ChangeEvent e) {
                    if (cSlider.getValueIsAdjusting())
                        return;
                    // if this is the first time number is altered, change to show it has been
                    // altered and then apply filter
                    // if number has already changed, undo last operation and then apply filter

                    bChange = bSlider.getValue();
                    cChange = cSlider.getValue();
                }

            

            });

            applyButton.addChangeListener(new ChangeListener() {
                @Override
                // is called when state changes, and updates image shown behind the
                // SpinnerNumberModel
                public void stateChanged(ChangeEvent e) {
                    
                    target.getImage().apply(new BrightnessConstrast(cChange, bChange));

                }
            });

            cancelButton.addChangeListener(new ChangeListener() {
                @Override
                // is called when state changes, and updates image shown behind the
                // SpinnerNumberModel
                public void stateChanged(ChangeEvent e) {
                    
                    return;

                }
            });
        }

    }

}
