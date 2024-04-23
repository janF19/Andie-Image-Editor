package cosc202.andie;


import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

public class MacroActions {
    /**
     * A list of actions for the Macro menu.
     * 
     * 
     * make new macro class and in editable image when record is 
     * clicked create new ops. when stopped in editableImage boolean stop and save macro
        copy fileActions and AlterActions, change stuff in editableImage
     */
    protected ArrayList<Action> actions;

    public MacroActions(){
        actions = new ArrayList<Action>();
        actions.add(new RecordMacroAction(LanguageActions.prefs.getString("RecordMacro"), null, "Record Macro",Integer.valueOf(KeyEvent.VK_1)));

    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
     public JMenu createMenu() {
        JMenu macroMenu = new JMenu(LanguageActions.prefs.getString("Macro"));

        for (Action action : actions) {
            macroMenu.add(new JMenuItem(action));
        }

        return macroMenu;
    }

    public class RecordMacroAction extends ImageAction{

        /**
         * <p>
         * Create a new RecordMacroAction action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RecordMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        /**
         * some comment to be added
         */

        public void actionPerformed(ActionEvent e){
            
        }
    }





    
}
