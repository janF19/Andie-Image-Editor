package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;


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


public class GaussianBlurAction extends ImageAction{


    /**
     * <p>
     * Create a new Gaussian blur action.
     * </p>
     * 
     * @param name The name of the action (ignored if null).
     * @param icon An icon to use to represent the action (ignored if null).
     * @param desc A brief description of the action  (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
     */
    GaussianBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the convert-to-grey action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the GaussianBlurAction is triggered.
     * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
     * </p>
     * 
     * @param e The event triggering this callback.
     */

     public void actionPerformed(ActionEvent e){
        int radius = 1;

        SpinnerNumberModel radiusModelGaussian = new SpinnerNumberModel(1,1,10,1);
        JSpinner radiusSpinner = new JSpinner(radiusModelGaussian);
        int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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