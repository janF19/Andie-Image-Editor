package cosc202.andie;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

/**
 * <p> 
 * Class used to store and create keyboard shortcuts for the features and 
 * functions implemented in A Non-Destructive Image Editor (ANDIE). 
 * </p> 
 * 
 * <p> 
 * The KeyboardShortcuts class contains methods that allow the user to set 
 * keyboard shortcuts for a given action, which work for both Windows, MacOs and
 * Linux based operating systems based on pre established common conventions like 
 * Ctrl - c to copy for Windows and Linux and Command - c for MacOs. 
 * </p> 
 */
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
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_Q, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke exitKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(exitKeyEvent); 

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
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_Z, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke redoKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(redoKeyEvent); 

    // rotate to the right shortcut 
    public static KeyEvent rotateRightKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke rotateRightKeyStroke = KeyboardShortcuts.getCtrlAltKeyStroke(rotateRightKeyEvent); 


    // rotate to the left shortcut 
    public static KeyEvent rotateLeftKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke rotateLeftKeyStroke = KeyboardShortcuts.getCtrlAltKeyStroke(rotateLeftKeyEvent);

    
    // rotate the image 180 degrees shortcut 
    public static KeyEvent rotate180KeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke rotate180KeyStroke = KeyboardShortcuts.getCtrlAltKeyStroke(rotate180KeyEvent); 


    // resize image shortcut 
    public static KeyEvent resizeKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_F8, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke resizeKeyStroke = KeyboardShortcuts.getAltKeyStroke(resizeKeyEvent); 


    // filp vertically shortcut 
    public static KeyEvent verticalFlipKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_V, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke verticalFlipKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(verticalFlipKeyEvent); 


    // filp horizontal shortcut 
    public static KeyEvent horizontalFlipKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_H, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke horizontalFlipKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(horizontalFlipKeyEvent); 


    // zoom in shortcut 
    // does not work 

    public static KeyEvent zoomInKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK , KeyEvent.VK_EQUALS, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke zoomInKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(zoomInKeyEvent); 



    // zoom out shortcut 
    public static KeyEvent zoomOuKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_MINUS, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke zoomOutKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(zoomOuKeyEvent); 


    // zoom full shortcut 
    public static KeyEvent zoomFullKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_F, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke zoomFullKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(zoomFullKeyEvent); 


    // change to english languague shortcut 
    public static KeyEvent engLanguagueKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_E, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke engLanguageKeyStroke = KeyboardShortcuts.getAltShiftKeyStroke(engLanguagueKeyEvent); 


    // change to spanish language shortcut 
    public static KeyEvent spanishLanguageKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_S, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke spanishLanguagueKeyStroke = KeyboardShortcuts.getAltShiftKeyStroke(spanishLanguageKeyEvent); 
    


    // mean filter shortcut 
    public static KeyEvent meanKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_M, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke meanKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(meanKeyEvent); 


    // softblur shortcut 
    public static KeyEvent softBlurKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_B, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke softBluKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(softBlurKeyEvent); 


    // sharpen shortcut 
    public static KeyEvent sharpenKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_S, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke sharpenKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(sharpenKeyEvent); 


    // median shortcut 
    public static KeyEvent medianKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_M, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke medianKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(medianKeyEvent); 


    // gaussian blur shortcut 
    public static KeyEvent gaussianKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_G, KeyEvent.CHAR_UNDEFINED); 
            
    public static KeyStroke guassainKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(gaussianKeyEvent); 


    // embos shortcut 
    public static KeyEvent embosKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_E, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke embosKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(embosKeyEvent); 


    // sobel shortcut 
    public static KeyEvent sobelKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_S, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke sobelKeyStroke = KeyboardShortcuts.getShiftKeyStroke(sobelKeyEvent);
    
    
    // block avarage shortcut 
    public static KeyEvent blockAverageKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke blockAverageKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(blockAverageKeyEvent); 


    // random scatter shortcut 
    public static KeyEvent randomScatterKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke randomScatterKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(randomScatterKeyEvent); 


    // grey scale shortcut
    public static KeyEvent greyScaleKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_G, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke greyScaleKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(greyScaleKeyEvent); 


    // color channel shortcut 
    public static KeyEvent colorChannelKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_C, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke colorChannelKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(colorChannelKeyEvent); 


    // invert shortcut 
    public static KeyEvent invertKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_I, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke invertKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(invertKeyEvent); 


    // brightness/contrast shortcut 
    public static KeyEvent brightnessKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_B, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke brightnessKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(brightnessKeyEvent);


    // record macro shortcut 
    public static KeyEvent recordKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED);

    public static KeyStroke recordKeyStroke = KeyboardShortcuts.getShiftKeyStroke(recordKeyEvent); 


    // stop macro shortcut 
    public static KeyEvent stopKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_E, KeyEvent.CHAR_UNDEFINED); 
    
    public static KeyStroke stopKeyStroke = KeyboardShortcuts.getShiftKeyStroke(stopKeyEvent); 


    // load macro shortcut 
    public static KeyEvent loadKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_L, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke loadKeyStroke = KeyboardShortcuts.getShiftKeyStroke(loadKeyEvent);


    // warmth shortcut 
    public static KeyEvent warmthKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_W, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke warmthKeyStroke = KeyboardShortcuts.getCtrlKeyStroke(warmthKeyEvent); 


    // vibrance shortcut 
    public static KeyEvent vibranceKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 
            KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_V, KeyEvent.CHAR_UNDEFINED); 

    public static KeyStroke vibranceKeyStroke = KeyboardShortcuts.getCtrlShiftKeyStroke(vibranceKeyEvent);     


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





    // method to set keyboard shortcuts with ctrl/command and alt/option keys 
    public static KeyStroke getCtrlAltKeyStroke(KeyEvent key) { 
        int keyCode = key.getKeyCode(); 
        int modifiers = KeyEvent.ALT_DOWN_MASK;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers = KeyEvent.ALT_DOWN_MASK; 
        }
        int modifiers2 = KeyEvent.CTRL_DOWN_MASK;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers2 = KeyEvent.META_DOWN_MASK;
        }
        return KeyStroke.getKeyStroke(keyCode, modifiers2 | modifiers); 
    }




    // method to set keyboard shortcuts with ctrl/command and shift keys 
    public static KeyStroke getCtrlShiftKeyStroke(KeyEvent key) { 
        int keyCode = key.getKeyCode(); 
        int modifiers = KeyEvent.SHIFT_DOWN_MASK; 
        int modifiers2 = KeyEvent.CTRL_DOWN_MASK;
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers2 = KeyEvent.META_DOWN_MASK;
        }
        return KeyStroke.getKeyStroke(keyCode, modifiers2 | modifiers); 
    }




    // method to set keyboard shortcuts with alt/option and shift keys 
    public static KeyStroke getAltShiftKeyStroke(KeyEvent key) { 
        int keyCode = key.getKeyCode(); 
        int modifiers = KeyEvent.ALT_DOWN_MASK;
        int modifiers2 = KeyEvent.SHIFT_DOWN_MASK; 
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers = KeyEvent.ALT_DOWN_MASK; 
        }
        return KeyStroke.getKeyStroke(keyCode, modifiers2 | modifiers); 
    }

    


    // method to set keyboard shortcuts with shift keys 
    public static KeyStroke getShiftKeyStroke(KeyEvent key) { 
        int keyCode = key.getKeyCode(); 
        int modifiers = KeyEvent.SHIFT_DOWN_MASK; 
        return KeyStroke.getKeyStroke(keyCode, modifiers); 
    }

}
