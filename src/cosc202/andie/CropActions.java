package cosc202.andie;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class CropActions {
    protected ArrayList<Action> actions;
    private boolean croppingSelect = false;
    private int selection=0;

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

    public boolean getCroppingSelect() {
        return this.croppingSelect;
    }

    public void setCroppingSelect(boolean croppingSelect) {
        this.croppingSelect = croppingSelect;
    }

    public class CropAction extends ImageAction {

        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            selection++;                 
            setCroppingSelect(true);
            if (getCroppingSelect()) {
                target.addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        // check if the button has been pressed more than once
                        if (selection%2!=0){
                            int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation",
                                    JOptionPane.OK_CANCEL_OPTION);
                            // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                            // null);

                            // Check the return value from the dialog box.
                            if (option == JOptionPane.CANCEL_OPTION) {
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
            }

        }
    }
}
