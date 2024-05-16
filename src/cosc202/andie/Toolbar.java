package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.DrawingActions.DrawEllipseAction;
import cosc202.andie.DrawingActions.DrawLineAction;
import cosc202.andie.DrawingActions.DrawRectangleAction;
import cosc202.andie.EditActions.UndoAction;
import cosc202.andie.FileActions.FileOpenAction;
import cosc202.andie.FileActions.FileSaveAction;
import cosc202.andie.FileActions.FileExitAction;
import cosc202.andie.CropActions.CropAction;

 /**
 * <p>
 * Actions provided by the Toolbar menu
 * </p>
 * 
 * <p>
 * The tool bar has a few common features.
 * It also includes the drawings and crop feature.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Damian Fraser
 * @version 1.0
 */
public class Toolbar extends JToolBar{
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> toolbar;
   // private boolean isCropped=false;
   protected CropActions cropActions;

    /**
     * <p>
     * Create a most commun toolbar.
     * </p>
     */
    public Toolbar() {
        // add the buttons
        addButtons();
        // make sure the toolbar can't move around
        setFloatable(false);
    }

    private void addButtons(){
        // Add the image for the open icon
        FileActions fileActions = new FileActions();
        ImageIcon openImage = new ImageIcon("OpenButton.png");
        FileOpenAction open = fileActions.new FileOpenAction("Open", openImage, "Open", null);
        addButton(open);

        // Add the image for the edit icon
        EditActions editActions = new EditActions();
        ImageIcon undoImage = new ImageIcon("UndoButton.png");
        UndoAction undo = editActions.new UndoAction("Undo", undoImage, "Undo", null);
        addButton(undo);

        // Add the image for the exit icon
        ImageIcon exitImage = new ImageIcon("ExitButton.png");
        FileExitAction exit = fileActions.new FileExitAction("Exit", exitImage, "Exit", null);
        addButton(exit);

        // Add the image for the rectangle icon
        this.cropActions = new CropActions();
        DrawingActions drawingActions = new DrawingActions();
        ImageIcon RectangleIcon = new ImageIcon("Rectangle.png");
        DrawRectangleAction rectangle = drawingActions.new DrawRectangleAction("DrawRectagnle", RectangleIcon, "DrawRectangle", Integer.valueOf(KeyEvent.VK_M));
        addButton(rectangle);

        // Add the image for the ellipse icon
        ImageIcon EllipseIcon = new ImageIcon("Ellipse.png");
        DrawEllipseAction ellipse = drawingActions.new DrawEllipseAction("Ellipse", EllipseIcon, "Draw Ellipse", Integer.valueOf(KeyEvent.VK_M));
        addButton(ellipse);

        // Add the image for the line icon
        ImageIcon LineIcon = new ImageIcon("Line.png");
        DrawLineAction line = drawingActions.new DrawLineAction("Line", LineIcon, "DrawLine", Integer.valueOf(KeyEvent.VK_M));
        addButton(line);

        // Add the image for the crop icon
        ImageIcon CropIcon = new ImageIcon("CropButton.png");
        CropAction crop = cropActions.new CropAction("Crop", CropIcon, "Draw Crop", Integer.valueOf(KeyEvent.VK_M));
        addButton(crop);
    }

    private void addButton(Action action){
        // add the buttons
        JButton button = new JButton(action);
        button.setText(null);
        add(button);
    }
}
