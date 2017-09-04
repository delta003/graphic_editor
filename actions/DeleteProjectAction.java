package actions;

import engine.Engine;
import model.Diagram;
import model.Project;
import model.Workspace;
import resources.Loader;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 01-Apr-15.
 */
public class DeleteProjectAction extends AbstractAction {
    public DeleteProjectAction() {putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
            KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("delete_project"));
        putValue(NAME, "Delete project");
        putValue(SHORT_DESCRIPTION, "Delete project");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project p = Utils.getInstance().getSelectedProject();
        if (p == null) return;
        while (p.getDiagramCount() > 0) p.removeDiagram(p.getDiagram(0));
        ((Workspace) p.getParent()).removeProject(p);
        Engine.getInstance().getWorkspaceTree().setSelectionPath(null);
    }
}
