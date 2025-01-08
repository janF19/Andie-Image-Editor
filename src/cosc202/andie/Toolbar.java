package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.DrawingActions.DrawEllipseAction;
import cosc202.andie.DrawingActions.DrawLineAction;
import cosc202.andie.DrawingActions.DrawRectangleAction;
import cosc202.andie.EditActions.UndoAction;
import cosc202.andie.FileActions.FileOpenAction;
// import cosc202.andie.FileActions.FileSaveAction;
import cosc202.andie.FileActions.FileExitAction;
import cosc202.andie.CropActions.CropAction;

 /**
 * <p>
 * Actions provided by the Edit Toolbar menu
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be added.
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
   protected DrawingActions drawActions;

    /**
     * <p>
     * Create a most commun toolbar.
     * </p>
     */
    public Toolbar() {
        addButtons();
        // Make it so the toolbar can't be moved
        setFloatable(false);
    }

    private void addButtons(){
        // Add the icon and button for open files
        FileActions fileActions = new FileActions();
        ImageIcon openImage = new ImageIcon("images/OpenButton.png");
        FileOpenAction open = fileActions.new FileOpenAction("Open", openImage, "Open", null);
        addButton(open);

        // Add the icon and button for undo
        EditActions editActions = new EditActions();
        ImageIcon undoImage = new ImageIcon("images/UndoButton.png");
        UndoAction undo = editActions.new UndoAction("Undo", undoImage, "Undo", null);
        addButton(undo);

        // Add the icon and button for exit files
        ImageIcon exitImage = new ImageIcon("images/ExitButton.png");
        FileExitAction exit = fileActions.new FileExitAction("Exit", exitImage, "Exit", null);
        addButton(exit);

        // Add the icon and button for drawing rectangles
        this.cropActions = new CropActions();
        this.drawActions = new DrawingActions();
        ImageIcon RectangleIcon = new ImageIcon("images/Rectangle.png");
        DrawRectangleAction rectangle = drawActions.new DrawRectangleAction("DrawRectagnle", RectangleIcon, "DrawRectangle", Integer.valueOf(KeyEvent.VK_M));
        addButton(rectangle);

        // Add the icon and button for drawing ellipses
        ImageIcon EllipseIcon = new ImageIcon("images/Ellipse.png");
        DrawEllipseAction ellipse = drawActions.new DrawEllipseAction("Ellipse", EllipseIcon, "Draw Ellipse", Integer.valueOf(KeyEvent.VK_M));
        addButton(ellipse);

        // Add the icon and button for drawing line
        ImageIcon LineIcon = new ImageIcon("images/Line.png");
        DrawLineAction line = drawActions.new DrawLineAction("Line", LineIcon, "DrawLine", Integer.valueOf(KeyEvent.VK_M));
        addButton(line);

        // Add the icon and button for cropping
        ImageIcon CropIcon = new ImageIcon("images/CropButton.png");
        CropAction crop = cropActions.new CropAction("Crop", CropIcon, "Draw Crop", Integer.valueOf(KeyEvent.VK_M));
        addButton(crop);
    }

    private void addButton(Action action){
        JButton button = new JButton(action);
        button.setText(null);
        add(button);
    }
}
