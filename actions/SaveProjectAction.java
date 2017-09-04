package actions;

import engine.Engine;
import files.ProjectFile;
import model.Project;
import resources.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by Marko on 01-Apr-15.
 */
public class SaveProjectAction extends AbstractAction {
    public SaveProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("save"));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFile());

        Project project = Engine.getInstance().getWorkspaceTree().getCurrentProject();
        if (project != null) {
            File projectFile = project.getProjectFile();

            if (project.getProjectFile() == null) {
                if (jfc.showSaveDialog(Engine.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    String o = projectFile.getPath();
                    if(!o.contains(".gep")) o += ".gep";
                    projectFile.delete();
                    projectFile = new File(o);
                } else {
                    return;
                }
            }

            ObjectOutputStream os;
            try {
                os = new ObjectOutputStream(new FileOutputStream(projectFile));
                os.writeObject(project);
                project.setProjectFile(projectFile);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
