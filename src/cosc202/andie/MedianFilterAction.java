package cosc202.andie;

import java.awt.event.*;
import javax.swing.*;


/**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MedianFilter
     * 
     * 
     * 
     * 
     */
public class MedianFilterAction extends ImageAction{


    /**
     * <p>
     * Create a new median-filter action.
     * </p>
     * 
     * @param name The name of the action (ignored if null).
     * @param icon An icon to use to represent the action (ignored if null).
     * @param desc A brief description of the action  (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
     */
    MedianFilterAction(String name, ImageIcon icon, String desc, KeyStroke key) {
        super(name, icon, desc, null);
        putValue(ACCELERATOR_KEY, key);
    }

    /**
     * <p>
     * Callback for when the Median filter action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the MedianFilterAction is triggered.
     * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
     * </p>
     * 
     * @param e The event triggering this callback.
     */

     public void actionPerformed(ActionEvent e){
        int radius = 1;

        SpinnerNumberModel radiusModelMedian = new SpinnerNumberModel(1,1,10,1);
        JSpinner radiusSpinner = new JSpinner(radiusModelMedian);
        int option = JOptionPane.showOptionDialog(null, radiusSpinner, LanguageActions.prefs.getString("radius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        // Check the return value from the dialog box.
        if (option == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (option == JOptionPane.OK_OPTION) {
            radius = radiusModelMedian.getNumber().intValue();
        }

        // Create and apply the filter
        target.getImage().apply(new MedianFilter(radius));
        target.repaint();
        target.getParent().revalidate();



     }



 }