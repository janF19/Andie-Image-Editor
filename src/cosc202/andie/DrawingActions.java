package cosc202.andie;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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

  
    /** A list of actions for the Toolbar. */
    protected ArrayList<Action> actions;
    private boolean rectangleSelect;
    private boolean lineSelect;
    private boolean ellipseSelect;
    private int rectangleSelection=0;
    private int lineSelection=0;
    private int ellipseSelection=0;

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

    public boolean getRectangleSelect() {
        return this.rectangleSelect;
    }

    public void setRectangleSelect(boolean rectangleSelect) {
        this.rectangleSelect = rectangleSelect;
    }

    public boolean getLineSelect() {
        return this.lineSelect;
    }

    public void setLineSelect(boolean lineSelect) {
        this.lineSelect = lineSelect;
    }

    public boolean getEllipseSelect() {
        return this.ellipseSelect;
    }

    public void setEllipseSelect(boolean ellipseSelect) {
        this.ellipseSelect = ellipseSelect;
    }

    /**
     * <p>
     * Action to draw rectangle
     * </p>
     */
    public class DrawRectangleAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawRectangleAction action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the DrawRectangleAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawRectangleAction is triggered.
         * It draws rectangle in selected area with selected outline anf fill colour.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            rectangleSelection++;
            setRectangleSelect(true);
            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect() && getRectangleSelect() && !getEllipseSelect()&& !getLineSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {

                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {

                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {
                            public void mouseReleased(MouseEvent e) {
                                if (rectangleSelection%2!=0){
                                    int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?",
                                            "Confirmation",
                                            JOptionPane.OK_CANCEL_OPTION);

                                    // Check the return value from the dialog box.
                                    if (option == JOptionPane.CANCEL_OPTION) {
                                        return;
                                    } else if (option == JOptionPane.OK_OPTION) {
                                    

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
                                        rectangleSelection=0;

                                    }
                                }
                                setRectangleSelect(false);
                            }
                        });
                    }
                }

            } else {
                // if the cropping tool is already selected then this appears
                JOptionPane.showMessageDialog(null, "You need to select area before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                rectangleSelection=0;
                setRectangleSelect(false);
            }
        }
    }

    /**
     * <p>
     * Action to draw line
     * </p>
     */

    public class DrawLineAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawLineAction action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */

        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the DrawLineAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawLineAction is triggered.
         * It draws line in selected area with selected colour.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            setLineSelect(true);
            lineSelection++;
            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect()&& !getRectangleSelect() && !getEllipseSelect()&& getLineSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {
                    target.addMouseListener(new MouseAdapter() {

                        public void mouseReleased(MouseEvent e) {
                            if (lineSelection%2!=0){
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
                                lineSelection=0;
                            }
                            setLineSelect(false);

                        }
                    });

                }
            } else {
                // warning measure if cropping already selected
                JOptionPane.showMessageDialog(null, "You need to select area before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                lineSelection=0;
                setLineSelect(false);
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

        /**
         * <p>
         * Create a new DrawEllipseAction action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        DrawEllipseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the DrawEllipseAction action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawEllipseAction is triggered.
         * It draws ellipse in selected area with selected outline anf fill colour.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            setEllipseSelect(true);
            ellipseSelection++;
            // Makes sure the cropping tool is not also selected
            if (!Andie.toolbar.cropActions.getCroppingSelect()&& !getRectangleSelect() && getEllipseSelect()&& !getLineSelect()) {
                // Prompt the user to choose an outline color
                Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
                if (outlineColor != null) {

                    // Prompt the user to choose a fill color
                    Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                    if (fillColor != null) {

                        // Add a mouse listener to allow the user to select a region
                        target.addMouseListener(new MouseAdapter() {

                            public void mouseReleased(MouseEvent e) {
                                if (ellipseSelection%2!=0){
                                    int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed?",
                                            "Confirmation",
                                            JOptionPane.OK_CANCEL_OPTION);
                                    // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                                    // null);

                                    // Check the return value from the dialog box.
                                    if (option == JOptionPane.CANCEL_OPTION) {
                                        return;
                                    } else if (option == JOptionPane.OK_OPTION) {
                                    

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
                                        ellipseSelection=0;
                                    }
                                }
                                setEllipseSelect(false);
                            }
                        });

                    }
                }

            } else {
                // warning measure if cropping already selected
                JOptionPane.showMessageDialog(null, "You need to select area before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                ellipseSelection=0;
                setEllipseSelect(false);
            }
        }
    }

}
