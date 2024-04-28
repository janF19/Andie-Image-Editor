package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import cosc202.andie.EditActions.UndoAction;

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
        EditActions editActions = new EditActions();
        ImageIcon undoImage = new ImageIcon("UndoButton.png");
        UndoAction undo = editActions.new UndoAction("Undo", undoImage, "Undo", Integer.valueOf(KeyEvent.VK_M));
        addButton(undo);
    }

    private void addButton(Action action){
        JButton button = new JButton(action);
        button.setText(null);
        add(button);
    }
}
