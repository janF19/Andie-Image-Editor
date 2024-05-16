package cosc202.andie;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

/**
 * <p>
 * Actions provided in toolbar.
 * </p>
 * 
 * <p>
 * The tool bar contains actions that allow to draw rectangle, line and ellipse
 * on the image displayed
 * </p>
 * 
 * 
 */
public class DrawingActions {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /** A list of actions for the Toolbar. */
    protected ArrayList<Action> actions;
    // private CropActions cropActions;

    /**
     * <p>
     * Create a set of Draw actions.
     * </p>
     */

    public DrawingActions() {

        actions = new ArrayList<Action>();

        actions.add(new DrawRectangleAction(LanguageActions.prefs.getString("DrawRectangle"), null, "DrawRectangle",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new DrawLineAction(LanguageActions.prefs.getString("DrawLine"), null, "DrawLine",
                Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DrawEllipseAction(LanguageActions.prefs.getString("DrawEllipse"), null, "DrawEllipse",
                Integer.valueOf(KeyEvent.VK_E)));
    }

    /**
     * <p>
     * Create a menu containing the list of Drawing actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(LanguageActions.prefs.getString("Draw"));

        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));

        }
        return drawMenu;
    }

    /**
     * <p>
     * Action to draw rectangle
     * </p>
     */
    public class DrawRectangleAction extends ImageAction {

        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {

                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {

                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {

                            public void mouseReleased(MouseEvent e) {

                                int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?",
                                        "Confirmation",
                                        JOptionPane.OK_CANCEL_OPTION);

                                // Check the return value from the dialog box.
                                if (option == JOptionPane.CANCEL_OPTION) {
                                    return;
                                } else if (option == JOptionPane.OK_OPTION) {
                                    x1 = Andie.imagePanel.getX1();
                                    y1 = Andie.imagePanel.getY1();
                                    x2 = Andie.imagePanel.getX2();
                                    y2 = Andie.imagePanel.getY2();

                                    int width = Andie.imagePanel.getWidth2();
                                    int height = Andie.imagePanel.getHeight2();

                                    // Determine the top-left corner coordinates
                                    int topLeftX = Andie.imagePanel.getLeftX();
                                    int topLeftY = Andie.imagePanel.getLeftY();

                                    Andie.imagePanel.getImage().undo(); // gits rid of the highlighted selected region
                                    target.getImage().apply(
                                            new DrawRectangle(topLeftX, topLeftY, width, height, fillColor,
                                                    outlineColor));

                                    target.removeMouseListener(this);

                                }

                            }
                        });
                    }
                }

            } else {
                // if the cropping tool is already selected then this appears
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * <p>
     * Action to draw line
     * </p>
     */

    public class DrawLineAction extends ImageAction {

        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {
                    target.addMouseListener(new MouseAdapter() {

                        public void mouseReleased(MouseEvent e) {
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
                                target.removeMouseListener(this);
                                Andie.imagePanel.getImage().undo(); // gits rid of the highlighted selected region
                                target.getImage().apply(new DrawLine(x1, y1, x2, y2, outlineColor));

                            }
                            target.removeMouseListener(this);

                        }
                    });

                }
            } else {
                // warning measure if cropping already selected
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
            // System.out.println("oopsie2");
        }
    }

    /**
     * <p>
     * Action to draw ellipse
     * </p>
     */

    public class DrawEllipseAction extends ImageAction {

        DrawEllipseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {

                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {

                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {

                            public void mouseReleased(MouseEvent e) {

                                int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?",
                                        "Confirmation",
                                        JOptionPane.OK_CANCEL_OPTION);
                                // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                                // null);

                                // Check the return value from the dialog box.
                                if (option == JOptionPane.CANCEL_OPTION) {
                                    return;
                                } else if (option == JOptionPane.OK_OPTION) {
                                    x1 = Andie.imagePanel.getX1();
                                    y1 = Andie.imagePanel.getY1();
                                    x2 = Andie.imagePanel.getX2();
                                    y2 = Andie.imagePanel.getY2();

                                    // Calculate the width and height of the rectangle
                                    int width = Andie.imagePanel.getWidth2();
                                    int height = Andie.imagePanel.getHeight2();

                                    // Determine the top-left corner coordinates
                                    int topLeftX = Andie.imagePanel.getLeftX();
                                    int topLeftY = Andie.imagePanel.getLeftY();

                                    // drawing functionality
                                    Andie.imagePanel.getImage().undo(); // gits rid of the highlighted selected region
                                    target.getImage()
                                            .apply(new DrawEllipse(topLeftX, topLeftY, width, height, fillColor,
                                                    outlineColor));

                                    // Remove the mouse listener after drawing the rectangle
                                    target.removeMouseListener(this);
                                }

                            }
                        });

                    }
                }

            } else {
                // warning measure if cropping already selected
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
