package files;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Marko on 25-May-15.
 */
public class ProjectFile extends FileFilter {

    @Override
    public String getDescription() {
        return "Graphic editor project (*.gep)";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() || f.getName().toLowerCase().endsWith(".gep") || !f.getName().contains("."));
    }
}
