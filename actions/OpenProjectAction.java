package actions;

import engine.Engine;
import files.ProjectFile;
import model.Project;
import model.Workspace;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Marko on 01-Apr-15.
 */
public class OpenProjectAction extends AbstractAction {
    public OpenProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("open_project"));
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFile());

        if(jfc.showOpenDialog(Engine.getInstance())==JFileChooser.APPROVE_OPTION) {
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Project p=null;

                try {
                    p = (Project) os.readObject();
                } catch (ClassNotFoundException ee) {
                    JOptionPane.showMessageDialog(Engine.getInstance(),"Erorr in file.");
                }

                Workspace root = (Workspace) Engine.getInstance().getWorkspaceModel().getRoot();

                if(!root.isAvailableProjectName(p.getName(), p)){
                    for (int i = 1; i <= root.getChildCount() + 1; i++)
                        if (root.isAvailableProjectName(p.getName() + "(" + String.valueOf(i) + ")",p)) {
                            p.setName(p.getName() + "(" + String.valueOf(i) + ")");
                            break;
                        }
                }

                Engine.getInstance().getWorkspaceTree().addOpenedProject(p, p.getName());

                for (int i=0;i<p.getDiagramCount();i++){
                    DiagramView view=new DiagramView();
                    view.setDiagram(p.getDiagram(i));
                    view.setVisible(false);
                    Engine.getInstance().getDesktop().add(view);
                }
                os.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        Engine.getInstance().getWorkspaceTree().expandRow(0);
    }
}
