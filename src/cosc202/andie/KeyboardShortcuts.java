package cosc202.andie;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

public class KeyboardShortcuts {

    // open shortcut
    public static KeyEvent openKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_O, KeyEvent.CHAR_UNDEFINED);

    public static KeyStroke openKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(openKeyEvent);

    // save shortcut
    public static KeyEvent saveKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_S, KeyEvent.CHAR_UNDEFINED);

    public static KeyStroke saveKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(saveKeyEvent);

    // save as shortcut
    public static KeyEvent saveAsKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.KEY_PRESSED, KeyEvent.VK_F12, KeyEvent.CHAR_UNDEFINED);

    public static KeyStroke saveAsKeyStroke = KeyboardShortcuts.getKeyStroke(saveAsKeyEvent);

    // exit shortcut
    public static KeyEvent exitKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_F4, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke exitKeyStroke = KeyboardShortcuts.getAltKeyStroke(exitKeyEvent); 

    // export shortcut
    public static KeyEvent exportKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_E, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke exportKeyStroke = KeyboardShortcuts.getAltKeyStroke(exportKeyEvent); 

    // undo shortcut
    public static KeyEvent undoKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_Z, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke undoKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(undoKeyEvent); 

    // redo shortcut
    public static KeyEvent redoKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_Y, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke redoKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(redoKeyEvent); 

    // method to set keyboard shortcuts with ctrl/command keys
    public static KeyStroke getCtrlKeyStroke(KeyEvent key) {
        int modifiers = KeyEvent.CTRL_DOWN_MASK;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers = KeyEvent.META_DOWN_MASK;
        }
        int keyCode = key.getKeyCode();
        return KeyStroke.getKeyStroke(keyCode, modifiers);
    }

    // method to set keyboard shortcuts using a single key
    public static KeyStroke getKeyStroke(KeyEvent key) {
        int keyCode = key.getKeyCode();
        return KeyStroke.getKeyStroke(keyCode, 0);
    }

    // method to set keyboard shortcuts with alt/option keys 
    public static KeyStroke getAltKeyStroke(KeyEvent key) {
        int modifiers = KeyEvent.ALT_DOWN_MASK;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers = KeyEvent.ALT_DOWN_MASK; 
        }
        int keyCode = key.getKeyCode();
        return KeyStroke.getKeyStroke(keyCode, modifiers);
    }

}
