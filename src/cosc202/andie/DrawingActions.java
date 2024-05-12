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

public class DrawingActions {

    int x1;
    int y1;
    int x2;
    int y2;

    protected ArrayList<Action> actions;
    private CropActions cropActions;

    public DrawingActions(CropActions cropActions) {

        actions = new ArrayList<Action>();

        actions.add(new DrawRectangleAction(LanguageActions.prefs.getString("DrawRectangle"), null, "DrawRectangle",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new DrawLineAction(LanguageActions.prefs.getString("DrawLine"), null, "DrawLine",
                Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DrawEllipseAction(LanguageActions.prefs.getString("DrawEllipse"), null, "DrawEllipse",
                Integer.valueOf(KeyEvent.VK_E)));
    }

    public JMenu createMenu() {
        JMenu drawMenu = new JMenu(LanguageActions.prefs.getString("Draw"));

        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));

        }
        return drawMenu;
    }

    public class DrawRectangleAction extends ImageAction {

        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Prompt the user to choose an outline color
            Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
            if (outlineColor != null) {

                // Prompt the user to choose a fill color
                Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                if (fillColor != null) {

                    // Add a mouse listener to allow the user to select a region
                    target.addMouseListener(new MouseAdapter() {
                        int x1, y1, x2, y2;

                        public void mousePressed(MouseEvent e) {
                            x1 = e.getX();
                            y1 = e.getY();
                        }

                        public void mouseReleased(MouseEvent e) {
                            x2 = e.getX();
                            y2 = e.getY();

                            // Calculate the width and height of the rectangle
                            int width = Math.abs(x2 - x1);
                            System.out.println("width is " + width);
                            int height = Math.abs(y2 - y1);

                            // Determine the top-left corner coordinates
                            int topLeftX = Math.min(x1, x2);
                            int topLeftY = Math.min(y1, y2);

                            target.getImage().apply(
                                    new DrawRectangle(topLeftX, topLeftY, width, height, fillColor, outlineColor));

                            // Remove the mouse listener after drawing the rectangle
                            target.removeMouseListener(this);
                        }
                    });
                }
            }

        }
    }

    public class DrawLineAction extends ImageAction {

        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // System.out.println("applying");
            // target.getImage().apply(new DrawLine(x1, y1, x2, y2, outlineColor));
            System.out.println("lol fuck");

            //if (!cropActions.getCroppingSelect()) {
                if(!Andie.toolbar.cropActions.getCroppingSelect()){
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
                                Andie.imagePanel.getImage().undo();
                                target.getImage().apply(new DrawLine(x1, y1, x2, y2, outlineColor));

                            }

                        }
                    });

                }
            } else {
                System.out.println("warning warning");
                JOptionPane.showMessageDialog(null, "You need to crop before drawing!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
            System.out.println("oopsie2");
        }
    }

    public class DrawEllipseAction extends ImageAction {

        DrawEllipseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // Prompt the user to choose an outline color
            Color outlineColor = JColorChooser.showDialog(null, "Choose Outline Color", Color.BLACK);
            if (outlineColor != null) {

                // Prompt the user to choose a fill color
                Color fillColor = JColorChooser.showDialog(null, "Choose Fill Color", Color.WHITE);
                if (fillColor != null) {

                    // Add a mouse listener to allow the user to select a region
                    target.addMouseListener(new MouseAdapter() {
                        int x1, y1, x2, y2;

                        public void mousePressed(MouseEvent e) {
                            x1 = e.getX();
                            y1 = e.getY();
                        }

                        public void mouseReleased(MouseEvent e) {
                            x2 = e.getX();
                            y2 = e.getY();

                            // Calculate the width and height of the rectangle
                            int width = Math.abs(x2 - x1);
                            System.out.println("width is " + width);
                            int height = Math.abs(y2 - y1);

                            // Determine the top-left corner coordinates
                            int topLeftX = Math.min(x1, x2);
                            int topLeftY = Math.min(y1, y2);

                            // drawing functionality
                            target.getImage()
                                    .apply(new DrawEllipse(topLeftX, topLeftY, width, height, fillColor, outlineColor));

                            // Remove the mouse listener after drawing the rectangle
                            target.removeMouseListener(this);
                        }
                    });

                }
            }

        }
    }

}
