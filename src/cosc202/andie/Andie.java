package cosc202.andie;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.imageio.*;
import java.util.*;

//testing

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggered via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see AlterActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * 
     */
    protected static ImagePanel imagePanel;

    protected static ArrayList<JFrame> frames = new ArrayList<JFrame>();
    

    protected static void createAndShowGUI() {
        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");
        frames.add(frame);

        
        

        Image image; // try catch replaced the throws exception declared in method header3
        try {
            image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
            frame.setIconImage(image);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The main content area is an ImagePanel
        imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        
        //Add region selection to imagePanel
        MouseBasedRegionSelection regionSelection = new MouseBasedRegionSelection();
        imagePanel.add(regionSelection);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        AlterActions alterActions = new AlterActions();
        menuBar.add(alterActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        LanguageActions languageActions = new LanguageActions();
        menuBar.add(languageActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        MacroActions macroActions = new MacroActions();
        menuBar.add(macroActions.createMenu());

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }

    /**
     * <p>
     * Launches the main GUI for the ANDIE program. Restarts the jframe  whilst keeping the edited image from the old jframe
     * </p>
     * 
     * <p>
     * The difference between this and the original createAndShowGui is that it does not create a new image panel but retrieves the old one
     * This is used to update the GUI text with the language option chosen
     * 
     * </p>
     * @see LanguageActions
     */
    protected static void restartAndShowGUI() {
        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");
        frames.add(frame);

        Image image; // try catch replaced the throws exception declared in method header3
        try {
            image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
            frame.setIconImage(image);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The main content area is an ImagePane
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        AlterActions alterActions = new AlterActions();
        menuBar.add(alterActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        LanguageActions languageActions = new LanguageActions();
        menuBar.add(languageActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        MacroActions macroActions = new MacroActions();
        menuBar.add(macroActions.createMenu());

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }
}
