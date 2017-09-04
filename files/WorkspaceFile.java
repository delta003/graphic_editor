package files;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Marko on 25-May-15.
 */
public class WorkspaceFile extends FileFilter {

    @Override
    public String getDescription() {
        return "Graphic editor workspace (*.gew)";
    }

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() || f.getName().toLowerCase().endsWith(".gew") || !f.getName().contains("."));
    }
}
