package cosc202.andie;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * <p>
 * Actions provided by the Crop.
 * </p>
 * 
 * <p>
 * The Crop menu contains actions that affect how the image is displayed in the
 * application.
 * These actions do not affect the contents of the image itself, just the way it
 * is displayed.
 * 
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class CropActions {
    /**
     * A list of actions for the Crop menu.
     */
    protected ArrayList<Action> actions;
    /**
     * A boolean for to check if cropping is selected
     */
    private boolean croppingSelect = false;
    /**
     * An int to count how many times crop has been pressed
     */
    private int selection=0;

    /**
     * <p>
     * Create a set of Crop menu actions.
     * </p>
     */
    public CropActions() {
        actions = new ArrayList<Action>();
        actions.add(new CropAction(LanguageActions.prefs.getString("CropImage"), null, "CropImage",
                Integer.valueOf(KeyEvent.VK_L)));
    }

    /**
     * <p>
     * Create a menu containing the list of Crop actions.
     * </p>
     * 
     * @return The crop menu UI element.
     */
    public JMenu createMenu() {
        JMenu cropMenu = new JMenu(LanguageActions.prefs.getString("Crop"));

        for (Action action : actions) {
            cropMenu.add(new JMenuItem(action));
        }

        return cropMenu;
    }

    /**
     * <p>
     * A getter for if cropping is selected
     * </p>
     */
    public boolean getCroppingSelect() {
        return this.croppingSelect;
    }

    /**
     * <p>
     * A setter for if cropping is selected
     * </p>
     */
    public void setCroppingSelect(boolean croppingSelect) {
        this.croppingSelect = croppingSelect;
    }

    /**
     * <p>
     * Action to crop the image
     * </p>
     */
    public class CropAction extends ImageAction {

        /**
         * <p>
         * Create a new CropAction action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the CropAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the CropAction is triggered.
         * It crops the image
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            selection++;                 
            setCroppingSelect(true);
            if (getCroppingSelect() && !Andie.toolbar.drawActions.getLineSelect() && !Andie.toolbar.drawActions.getRectangleSelect() && !Andie.toolbar.drawActions.getEllipseSelect()) {
                target.addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        // check if the button has been pressed even or odd amounts of times
                        if (selection%2!=0){
                            int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
                                    JOptionPane.OK_CANCEL_OPTION);
                            // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                            // null);

                            // Check the return value from the dialog box.
                            if (option == JOptionPane.CANCEL_OPTION) {
                            target.removeMouseListener(this);
                                return;
                            } else if (option == JOptionPane.OK_OPTION) {
                                int x1 = Andie.imagePanel.getX1();
                                int y1 = Andie.imagePanel.getY1();
                                int x2 = Andie.imagePanel.getX2();
                                int y2 = Andie.imagePanel.getY2();
                                
                                int width = Math.abs(x2 - x1);
                                //System.out.println("width is " + width);
                                int height = Math.abs(y2 - y1);
                                int topLeftX = Math.min(x1, x2);
                                int topLeftY = Math.min(y1, y2);
                            target.getImage().undo();
                                target.getImage().apply(new CropImage(topLeftX, topLeftY, topLeftX + width, topLeftY + height));
                                target.repaint();
                                target.removeMouseListener(this);
                                selection=0;
                            }
                        }
                        // make it so you can draw shapes
                        setCroppingSelect(false);
                    }
                });
            }  else {
                // warning measure if cropping already selected
                JOptionPane.showMessageDialog(null, "You need to select area before cropping!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                selection=0;
                setCroppingSelect(false);
            }

        }
    }
}
