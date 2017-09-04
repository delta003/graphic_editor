package actions;

import engine.Engine;
import files.WorkspaceFile;
import model.Project;
import model.Workspace;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by Marko on 01-Apr-15.
 */
public class OpenWorkspaceAction extends AbstractAction {
    public OpenWorkspaceAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("open_workspace"));
        putValue(NAME, "Open workspace");
        putValue(SHORT_DESCRIPTION, "Open workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFile());
        Workspace w = (Workspace) Engine.getInstance().getWorkspaceTree().getModel().getRoot();

        for (int i = 0; i < w.getChildCount(); i++) {
            int result = JOptionPane.showConfirmDialog(Engine.getInstance(),"Do you want to save workspace?");
            if (result == JOptionPane.YES_OPTION) {
                Engine.getInstance().getActionManager()
                        .getSaveWorkspaceAction().actionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(Engine.getInstance(),"You will lost unsaved data.");
            }
            break;
        }

        if (jfc.showOpenDialog(Engine.getInstance()) == JFileChooser.APPROVE_OPTION) {
            w.reset();
            try {
                Engine.getInstance().getDesktop().repaint();

                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
                int projectCount;
                projectCount = (Integer) os.readObject();
                for (int ii = 0; ii < projectCount; ii++) {
                    File f = (File) os.readObject();
                    Project p;
                    if (f != null) {
                        ObjectInputStream os1 = new ObjectInputStream(new FileInputStream(f));
                        p = (Project) os1.readObject();
                        Engine.getInstance().getWorkspaceTree().addOpenedProject(p, p.getName());
                        for (int i=0;i<p.getDiagramCount();i++){
                            DiagramView view=new DiagramView();
                            view.setDiagram(p.getDiagram(i));
                            view.setVisible(false);
                            Engine.getInstance().getDesktop().add(view);
                        }
                        os1.close();
                    }
                }
                os.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        Engine.getInstance().getWorkspaceTree().expandRow(0);
    }
}
