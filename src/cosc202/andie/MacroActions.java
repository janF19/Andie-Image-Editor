package cosc202.andie;


import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;



public class MacroActions implements java.io.Serializable {
    
    /**
     * A list of actions for the Macro menu.
     * 
     * 
     * make new macro class and in editable image when record is 
     * clicked create new ops. when stopped in editableImage boolean stop and save macro
        copy fileActions and AlterActions, change stuff in editableImage

        class save macro will be implemented within stop macro
        
        add class load macro - dialog to find ops. file and apply stuff in it to image


        left to do 24.4.
        make method to load macro from file and perform actions
        check whether current macro record does not store opearation record macro itself

     */
    protected ArrayList<Action> actions;
    

    

    public MacroActions(){
        actions = new ArrayList<Action>();
        actions.add(new RecordMacroAction(LanguageActions.prefs.getString("record"), null, "Record Macro",KeyboardShortcuts.recordKeyStroke));
        actions.add(new StopRecordMacroAction(LanguageActions.prefs.getString("stop"), null, "Stop Macro",KeyboardShortcuts.stopKeyStroke));
        actions.add(new LoadMacroAction(LanguageActions.prefs.getString("load"), null, "Load Macro",KeyboardShortcuts.loadKeyStroke));
        
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
        RecordMacroAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }

        /**
         * some comment to be added
         */

        public void actionPerformed(ActionEvent e){
            
            
            target.getImage().macroState = true;
           
            
            
        }
    }

    public class StopRecordMacroAction extends ImageAction{
        /**
         * <p>
         * Create a new StopRecordMacroAction action.
         * trigger saving dialog automatically after clicked on stop
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StopRecordMacroAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }
        /**
         * some comment to be added
         */

         public void actionPerformed(ActionEvent e){
            
            

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if(result == JFileChooser.APPROVE_OPTION){
                try{
                    String macroFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveMacro(macroFilepath);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    if (ex instanceof IllegalArgumentException) {
                        JOptionPane.showMessageDialog(null, "Error Saving macro: No file to save", "Save Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            /** 
            try{
                target.getImage().saveMacro();
                System.out.println("Save executed");
            } catch (Exception ex) {
                if (ex instanceof NullPointerException) {
                    JOptionPane.showMessageDialog(null, "Error Saving macro: No macro to save", "Save Error",
                            JOptionPane.ERROR_MESSAGE);
                }  
*/
            }

         }

         public class LoadMacroAction extends ImageAction {
             /**
         * <p>
         * Create a new StopRecordMacroAction action.
         * trigger saving dialog automatically after clicked on stop
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        
        LoadMacroAction(String name, ImageIcon icon, String desc, KeyStroke key){
            super(name, icon, desc, null);
            putValue(ACCELERATOR_KEY, key);
        }


            public void actionPerformed (ActionEvent e) {
                //find file
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(target);

                if(result == JFileChooser.APPROVE_OPTION){
                    try {
                        String macroFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        //need to implement macroOpen in EditableImage, something like open
                        target.getImage().macroOpen(macroFilepath);

                    } catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error Opening image: Wrong format", "Open",  JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
         }

    }





    

