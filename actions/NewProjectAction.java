package actions;

import engine.Engine;
import model.Diagram;
import model.Project;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

/**
 * Created by Marko on 01-Apr-15.
 */
public class NewProjectAction extends AbstractAction {
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("new_project"));
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = new Project();
        Engine.getInstance().getWorkspaceTree().addProject(project);
        Engine.getInstance().getWorkspaceTree().clearSelection();
        Diagram diagram = new Diagram();
        project.addDiagram(diagram);

        DiagramView view = new DiagramView();
        view.setDiagram(diagram);
        Engine.getInstance().getDesktop().add(view);
        try {
            view.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }
}
