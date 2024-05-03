package cosc202.andie;

import java.util.*;
import java.awt.event.*;

import javax.swing.*;


/**
     * <p>
     * Applies language bundles to text on the jframe - allows options for different languages
     * </p>
     * 
     * <p>
     * Currently implements Spanish (Spain) and English (US)
     * </p>
     * 
     * 
     * @author Parsa Orodes
     * @version 1.0
     * 
     */

public class LanguageActions {
    
    /**
     * A list of actions for the Language menu.
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
        actions.add(new SpanishLanguage(prefs.getString("Spanish"), null, "Spanish", KeyboardShortcuts.spanishLanguagueKeyStroke));
        actions.add(new EnglishLanguage(prefs.getString("English"), null, "English", KeyboardShortcuts.engLanguageKeyStroke));
    }

    /**
     * <p>
     * Create a menu containing the list of Language actions.
     * </p>
     * 
     * @return The Language menu UI element.
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
     * Action to change language of the text to English. Whens starting out the GUI the default is English. 
     * </p>
     * 
     * <p>
     * Note that this action only affects the way the text of the menu bar and not the image displayed.
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
        EnglishLanguage(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

       /**
         * <p>
         * Callback for when the English language action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EnglishLanguage is triggered.
         * It changes all the UI text to English using the corresponding message bundle. 
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            prefs = ResourceBundle.getBundle("MessageBundle", new Locale("en", "US"));

            Andie.frames.get(0).setVisible(false);
            Andie.frames.get(0).dispose();
            Andie.frames.remove(0);  //disposes the old frame with the previous language
            Andie.restartAndShowGUI(); //calls the restart gui that still has edited image but updated gui text with English
        }

    }

    


        /**
     * <p>
     * Action to change language of the text to Spanish.
     * 
     * <p>
     * Note that this action only affects the way the text of the menu bar and not the image displayed.
     * </p>
     */
        public class SpanishLanguage extends ImageAction {

        /**
         * <p>
         * Create a new Spanish language action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SpanishLanguage(String name, ImageIcon icon, String desc, KeyStroke key) {
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * <p>
         * Callback for when the Spanish Language action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SpanishLanguage is triggered.
         * It changes all the UI text to Spanish using the corresponding message bundle. 
         * 
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {            
      
       prefs = ResourceBundle.getBundle("MessageBundle", new Locale("span", "Spain"));

            Andie.frames.get(0).setVisible(false);
            Andie.frames.get(0).dispose();
            Andie.frames.remove(0);  //disposes the old frame with the previous language
            Andie.restartAndShowGUI(); //calls the restart gui that still has edited image but updated gui text with Spanish
            

             

            

        }

    }

}
