package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import cosc202.andie.DrawingActions.DrawEllipseAction;
import cosc202.andie.DrawingActions.DrawLineAction;
import cosc202.andie.DrawingActions.DrawRectangleAction2;
import cosc202.andie.EditActions.UndoAction;
import cosc202.andie.FileActions.FileOpenAction;
import cosc202.andie.FileActions.FileSaveAction;
import cosc202.andie.FileActions.FileExitAction;

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

    /**
     * <p>
     * Create a most commun toolbar.
     * </p>
     */
    public Toolbar() {
        addButtons();
    }
    private void addButtons(){
        FileActions fileActions = new FileActions();
        ImageIcon openImage = new ImageIcon("OpenButton.png");
        FileOpenAction open = fileActions.new FileOpenAction("Open", openImage, "Open", null);
        addButton(open);

        ImageIcon saveImage = new ImageIcon("SaveButton.jpg");
        FileSaveAction save = fileActions.new FileSaveAction("Save", saveImage, "Save", null);
        addButton(save);

        ImageIcon exitImage = new ImageIcon("ExitButton.png");
        FileExitAction exit = fileActions.new FileExitAction("Exit", exitImage, "Exit", null);
        addButton(exit);

        EditActions editActions = new EditActions();
        ImageIcon undoImage = new ImageIcon("UndoButton.png");
        UndoAction undo = editActions.new UndoAction("Undo", undoImage, "Undo", null);
        addButton(undo);

        DrawingActions drawingActions = new DrawingActions();
        ImageIcon RectangleIcon = new ImageIcon("Rectangle.png");
        DrawRectangleAction2 rectangle = drawingActions.new DrawRectangleAction2("DrawRectagnle", RectangleIcon, "DrawRectangle", Integer.valueOf(KeyEvent.VK_M));
        addButton(rectangle);

        ImageIcon LineIcon = new ImageIcon("Line.png");
        DrawLineAction line = drawingActions.new DrawLineAction("Line", LineIcon, "DrawLine", Integer.valueOf(KeyEvent.VK_M));
        addButton(line);

        ImageIcon EllipseIcon = new ImageIcon("Ellipse.png");
        DrawEllipseAction ellipse = drawingActions.new DrawEllipseAction("Ellipse", EllipseIcon, "Draw Ellipse", Integer.valueOf(KeyEvent.VK_M));
        addButton(ellipse);
    }

    private void addButton(Action action){
        JButton button = new JButton(action);
        button.setText(null);
        add(button);
    }
}
