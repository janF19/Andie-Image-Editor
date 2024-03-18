package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import java.text.MessageFormat;

import javax.swing.*;

public class LanguageActions {
    
    /**
     * A list of actions for the View menu.
     */

    protected static ResourceBundle prefs = ResourceBundle.getBundle("MessageBundle", new Locale("en", "US"));
    



    protected static ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageActions() {

        

        actions = new ArrayList<Action>();
        actions.add(new SpanishLanguage(prefs.getString("Spanish"), null, "Spanish", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new EnglishLanguage(prefs.getString("English"), null, "English", Integer.valueOf(KeyEvent.VK_E)));
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu(prefs.getString("Language"));

        for (Action action: actions) {
            languageMenu.add(new JMenuItem(action));
        }

        return languageMenu;
    }

    /**
     * <p>
     * Action to zoom in on an image.
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the image is displayed, not its actual contents.
     * </p>
     */
    public class EnglishLanguage extends ImageAction {

        /**
         * <p>
         * Create a new language action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        EnglishLanguage(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-in action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomInAction is triggered.
         * It increases the zoom level by 10%, to a maximum of 200%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            prefs = ResourceBundle.getBundle("MessageBundle", new Locale("en", "US"));

            Andie.frames.get(0).setVisible(false);
            Andie.frames.get(0).dispose();
            Andie.frames.remove(0);
             Andie.createAndShowGUI();
        }

    }

    


        
        public class SpanishLanguage extends ImageAction {

        /**
         * <p>
         * Create a new zoom-in action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SpanishLanguage(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the zoom-in action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ZoomInAction is triggered.
         * It increases the zoom level by 10%, to a maximum of 200%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {            
      
       prefs = ResourceBundle.getBundle("MessageBundle", new Locale("span", "Spain"));

            Andie.frames.get(0).setVisible(false);
            Andie.frames.get(0).dispose();
            Andie.frames.remove(0);
             Andie.createAndShowGUI();
            

             

            

        }

    }

}
