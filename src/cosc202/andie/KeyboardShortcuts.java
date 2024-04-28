package cosc202.andie;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class KeyboardShortcuts {

    // Create a KeyEvent object with the desired virtual keycode (VK_O)
    public static KeyEvent openKeyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_O, KeyEvent.CHAR_UNDEFINED);
    // Pass the KeyEvent object to the getOpenKeyStroke method
    public static KeyStroke openKeyStroke = KeyboardShortcuts.getOpenKeyStroke(openKeyEvent);

    public static KeyStroke getOpenKeyStroke(KeyEvent key) {
        int modifiers = KeyEvent.CTRL_DOWN_MASK; // Default to Control key on Windows
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            modifiers = KeyEvent.META_DOWN_MASK; // Use Command key on macOS
        }
        int keyCode = key.getKeyCode(); // Extract the keycode from the KeyEvent object
        return KeyStroke.getKeyStroke(keyCode, modifiers);
    }

}
