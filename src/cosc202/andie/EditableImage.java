package cosc202.andie;

import java.util.*;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JOptionPane;

/**
 * <p>
 * An image with a set of operations applied to it.
 * </p>
 * 
 * <p>
 * The EditableImage represents an image with a series of operations applied to
 * it.
 * It is fairly core to the ANDIE program, being the central data structure.
 * The operations are applied to a copy of the original image so that they can
 * be undone.
 * THis is what is meant by "A Non-Destructive Image Editor" - you can always
 * undo back to the original image.
 * </p>
 * 
 * <p>
 * Internally the EditableImage has two {@link BufferedImage}s - the original
 * image
 * and the result of applying the current set of operations to it.
 * The operations themselves are stored on a {@link Stack}, with a second
 * {@link Stack}
 * being used to allow undone operations to be redone.
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
class EditableImage implements java.io.Serializable {

    /** The original image. This should never be altered by ANDIE. */
    private BufferedImage original;
    /**
     * The current image, the result of applying {@link ops} to {@link original}.
     */
    private BufferedImage current;
    /** The sequence of operations currently applied to the image. */
    private Stack<ImageOperation> ops;
    /** A memory of 'undone' operations to support 'redo'. */
    private Stack<ImageOperation> redoOps;

    /** Stack of operations for macro */
    private Stack<ImageOperation> macro;
    /** The file where the original image is stored/ */
    private String imageFilename;
    /** The file where the operation sequence is stored. */
    private String opsFilename;

    private String opsMacroFile;

    private MacroActions macroAction;

    public boolean macroState;

    // private int error;

    /**
     * <p>
     * Create a new EditableImage.
     * </p>
     * 
     * <p>
     * A new EditableImage has no image (it is a null reference), and an empty stack
     * of operations.
     * </p>
     */
    public EditableImage() {
        original = null;
        current = null;
        ops = new Stack<ImageOperation>();
        redoOps = new Stack<ImageOperation>();
        macro = new Stack<ImageOperation>();
        imageFilename = null;
        opsFilename = null;

        opsMacroFile = null;
        this.macroAction = new MacroActions();

        macroState = false;

    }

    /**
     * <p>
     * Check if there is an image loaded.
     * </p>
     * 
     * @return True if there is an image, false otherwise.
     */
    public boolean hasImage() {
        return current != null;
    }

    /**
     * <p>
     * Make a 'deep' copy of a BufferedImage.
     * </p>
     * 
     * <p>
     * Object instances in Java are accessed via references, which means that
     * assignment does
     * not copy an object, it merely makes another reference to the original.
     * In order to make an independent copy, the {@code clone()} method is generally
     * used.
     * {@link BufferedImage} does not implement {@link Cloneable} interface, and so
     * the
     * {@code clone()} method is not accessible.
     * </p>
     * 
     * <p>
     * This method makes a cloned copy of a BufferedImage.
     * This requires knowledge of some details about the internals of the
     * BufferedImage,
     * but essentially comes down to making a new BufferedImage made up of copies of
     * the internal parts of the input.
     * </p>
     * 
     * <p>
     * This code is taken from StackOverflow:
     * <a href=
     * "https://stackoverflow.com/a/3514297">https://stackoverflow.com/a/3514297</a>
     * in response to
     * <a href=
     * "https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage</a>.
     * Code by Klark used under the CC BY-SA 2.5 license.
     * </p>
     * 
     * <p>
     * This method (only) is released under
     * <a href="https://creativecommons.org/licenses/by-sa/2.5/">CC BY-SA 2.5</a>
     * </p>
     * 
     * @param bi The BufferedImage to copy.
     * @return A deep copy of the input.
     */
    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * <p>
     * Open an image from a file.
     * </p>
     * 
     * <p>
     * Opens an image from the specified file.
     * Also tries to open a set of operations from the file with <code>.ops</code>
     * added.
     * So if you open <code>some/path/to/image.png</code>, this method will also try
     * to
     * read the operations from <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param filePath The file to open the image from.
     * @throws Exception If something goes wrong.
     */
    public void open(String filePath) throws Exception {
        imageFilename = filePath;
        opsFilename = imageFilename + ".ops";
        File imageFile = new File(imageFilename);
        original = ImageIO.read(imageFile);
        current = deepCopy(original);

        // Add region selection to imagePanel
        // MouseBasedRegionSelection regionSelection = new
        // MouseBasedRegionSelection(current);

        try {
            FileInputStream fileIn = new FileInputStream(this.opsFilename);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            // Silence the Java compiler warning about type casting.
            // Understanding the cause of the warning is way beyond
            // the scope of COSC202, but if you're interested, it has
            // to do with "type erasure" in Java: the compiler cannot
            // produce code that fails at this point in all cases in
            // which there is actually a type mismatch for one of the
            // elements within the Stack, i.e., a non-ImageOperation.
            @SuppressWarnings("unchecked")
            Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();
            ops = opsFromFile;
            redoOps.clear();
            objIn.close();
            fileIn.close();
        } catch (Exception ex) {
            // Could be no file or something else. Carry on for now.
            ops.clear();
            redoOps.clear();
        }
        this.refresh();
    }

    public void macroOpen(String filePath) throws Exception {
        // this.error=0;
        opsMacroFile = filePath;

        // int errors = 0;

        try {
            FileInputStream fileInMacro = new FileInputStream(this.opsMacroFile);
            ObjectInputStream objInMacro = new ObjectInputStream(fileInMacro);

            // Silence the Java compiler warning about type casting.
            // Understanding the cause of the warning is way beyond
            // the scope of COSC202, but if you're interested, it has
            // to do with "type erasure" in Java: the compiler cannot
            // produce code that fails at this point in all cases in
            // which there is actually a type mismatch for one of the
            // elements within the Stack, i.e., a non-ImageOperation.
            @SuppressWarnings("unchecked")
            Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objInMacro.readObject();
            this.macro = opsFromFile;

            for (ImageOperation op : macro) {
                String classi = String.valueOf(op.getClass());

                if (classi.equals("class cosc202.andie.CropImage") || classi.equals("cosc202.andie.DrawLine") || 
                classi.equals("cosc202.andie.DrawEllipse") || classi.equals("cosc202.andie.DrawRectangle") ) {
                    System.out.println("dying out here");
                    BufferedImage b =  getCurrentImage();
                    try{
                      op.apply(b);

                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, LanguageActions.prefs.getString("InvalidMacroIndex"),
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                    }
                    
                }
            }

            objInMacro.close();
            fileInMacro.close();
            System.out.println("macro was stored to stack and will be applied");

            if (!macro.isEmpty()) {
                System.out.println("Macro contains operations. Will be applied.");
                refreshAfterMacro();
            } else {
                System.out.println("Macro is empty.");
            }

        } catch (Exception ex) {

            // this.error = 1;

            System.out.println("Something went wrong, exception " + ex);
            // macro.clear();

            // if (error == 1) {
            // if (String.valueOf(ex).equals("java.awt.image.RasterFormatException: (x +
            // width) is outside of Raster")
            // || String.valueOf(ex)
            // .equals("java.lang.ArrayIndexOutOfBoundsException: Coordinate out of
            // bounds!")) {
            // JOptionPane.showMessageDialog(null,
            // LanguageActions.prefs.getString("InvalidMacroIndex"),
            // "Warning",
            // JOptionPane.WARNING_MESSAGE);
            // // return;
            // }
            // }

            // warning measure if cropping already selected

            JOptionPane.showMessageDialog(null, "Invalid Macro! " + ex,
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

        }

    }

    /**
     * <p>
     * Save an image to file.
     * </p>
     * 
     * <p>
     * Saves an image to the file it was opened from, or the most recent file saved
     * as.
     * Also saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save
     * the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @throws Exception If something goes wrong.
     */
    public void save() throws Exception {
        if (this.opsFilename == null) {
            this.opsFilename = this.imageFilename + ".ops";
        }

        String extension = imageFilename.substring(1 + imageFilename.lastIndexOf(".")).toLowerCase();

        File imageFile = new File(imageFilename);

        ImageIO.write(original, extension, imageFile);
        // Write operations file

        FileOutputStream fileOut = new FileOutputStream(this.opsFilename);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(this.ops);
        objOut.close();
        fileOut.close();
    }

    public void saveMacro(String macroFileName) throws Exception {

        this.opsMacroFile = macroFileName;

        if (this.opsMacroFile == null) {
            this.opsMacroFile = this.imageFilename + ".ops";
        }
        FileOutputStream fileOut = new FileOutputStream(this.opsMacroFile);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(this.macro);
        objOut.close();
        fileOut.close();

    }

    /**
     * <p>
     * Save an image to a specified file.
     * </p>
     * 
     * <p>
     * Saves an image to the file provided as a parameter.
     * Also saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save
     * the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param imageFilename The file location to save the image to.
     * @throws Exception If something goes wrong.
     */
    public void saveAs(String imageFilename) throws Exception {

        String extension = imageFilename.substring(imageFilename.lastIndexOf('.') + 1).toLowerCase();

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png") &&
                !extension.equals("bmp") && !extension.equals("gif") && !extension.equals("wbmp")) {

            JOptionPane.showMessageDialog(null,
                    "Error saving image: Incompatible file format. Suggested formats: png or jpg or jpeg", "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {

            this.imageFilename = imageFilename;

            this.opsFilename = imageFilename.substring(0, imageFilename.length() - 4) + ".ops";
            save();
        }
    }

    /**
     * <p>
     * Exports an image to a specified location.
     * </p>
     * 
     * <p>
     * Exports an image to the file provided as a parameter.
     * </p>
     * 
     * @param exportFilename The file location to save the image to.
     * @throws Exception If something goes wrong.
     */
    public void exportImage(String exportFilename) throws Exception {
        // Write image file based on file extension
        String extension = exportFilename.substring(exportFilename.lastIndexOf('.') + 1).toLowerCase();

        // If no extension is provided or the extension is not recognized, default to
        // JPEG

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png") &&
                !extension.equals("bmp") && !extension.equals("gif") && !extension.equals("wbmp")) {
            // extension = "jpg"; // Default to JPEG
            // exportFilename += ".jpg"; // Add .jpg extension to the filename
            // ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error exporting image: " + "Null or incorrect file format. Suggested formats: png or jpg or jpeg",
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        File imageFile = new File(exportFilename);
        ImageIO.write(current, extension, imageFile);
        JOptionPane.showMessageDialog(null, "Image exported successfully.", "Export Successful",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * <p>
     * Apply an {@link ImageOperation} to this image.
     * </p>
     * 
     * @param op The operation to apply.
     */
    public void apply(ImageOperation op) {
        // macro implementation
        if (macroState == true) { // macros should never recrod a brightness contrast mouse selection region
            if (!String.valueOf(op.getClass()).equals("class cosc202.andie.BrightnessConstrastSection")) {
                macro.add(op);
                System.out.println("operation added: " + op.getClass());
            }
        }
        current = op.apply(current);
        ops.add(op);

    }

    /**
     * <p>
     * Undo the last {@link ImageOperation} applied to the image.
     * </p>
     */
    public void undo() {
        if (!ops.isEmpty()) {
            ImageOperation popped = ops.pop();
            System.out.println("undoing: " + popped);

            redoOps.push(popped);
            refresh();
        }
        // System.out.println("class popped: " + popped.getClass());

        // redoOps.push(ops.pop());
    }

    public boolean check() {
        // System.out.println("ok then" + ops.peek().getClass());
        if (!ops.empty()) {
            if (ops.peek().getClass() == BrightnessConstrastSection.class) {
                return true;
            }

        }
        return false;
    }

    /**
     * <p>
     * Reapply the most recently {@link undo}ne {@link ImageOperation} to the image.
     * </p>
     */
    public void redo() {
        apply(redoOps.pop());
    }

    /**
     * <p>
     * Get the current image after the operations have been applied.
     * </p>
     * 
     * @return The result of applying all of the current operations to the
     *         {@link original} image.
     */
    public BufferedImage getCurrentImage() {
        return current;
    }

    /**
     * <p>
     * Reapply the current list of operations to the original.
     * </p>
     * 
     * <p>
     * While the latest version of the image is stored in {@link current}, this
     * method makes a fresh copy of the original and applies the operations to it in
     * sequence.
     * This is useful when undoing changes to the image, or in any other case where
     * {@link current}
     * cannot be easily incrementally updated.
     * </p>
     */
    private void refresh() {
        current = deepCopy(original);
        for (ImageOperation op : ops) {
            current = op.apply(current);
        }
    }

    private void refreshAfterMacro() {
        current = deepCopy(original);
        for (ImageOperation op : this.macro) {
            System.out.println("refreshed and applied");
            /// ImageOperation op =
            System.out.println("class " + op.getClass());
            
            ops.push(op);
            refresh();
            // revalidate();
            // current = op.apply(current);
        }
    }

    public Graphics2D createGraphics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGraphics'");
    }

}
