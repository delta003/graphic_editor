package actions;

import engine.Engine;
import files.WorkspaceFile;
import model.Project;
import model.Workspace;
import resources.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by Marko on 01-Apr-15.
 */
public class SaveWorkspaceAction extends AbstractAction {
    public SaveWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("save_all"));
        putValue(NAME, "Save workspace");
        putValue(SHORT_DESCRIPTION, "Save workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFile());
        if (jfc.showSaveDialog(Engine.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File workspaceFile = jfc.getSelectedFile();
            String o = workspaceFile.getPath();
            String n;
            if(!o.contains(".gew"))
                o+=".gew";

            n = o.substring(0, o.length()-4);
            n.trim();

            workspaceFile.delete();
            workspaceFile = new File(o);
            ObjectOutputStream os;
            try {
                Workspace w = (Workspace) Engine.getInstance().getWorkspaceTree().getModel().getRoot();
                os = new ObjectOutputStream(new FileOutputStream(workspaceFile));
                os.writeObject(w.getChildCount());
                for (int i = 0; i < w.getChildCount(); i++) {
                    saveProject((Project) w.getChildAt(i),n);
                    os.writeObject(((Project) w.getChildAt(i)).getProjectFile());
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void saveProject(Project project, String path){
        String o;
        File dir = new File(path);
        o = path + "\\" + project.getName()+".gpf";
        o.trim();
        File projectFile = new File(o);

        ObjectOutputStream os;
        try {

            if(dir.exists())
                dir.delete();
            dir.mkdir();

            if(projectFile.exists())
                projectFile.delete();
            projectFile.createNewFile();
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
