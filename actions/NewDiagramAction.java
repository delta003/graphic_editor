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
public class NewDiagramAction extends AbstractAction {
    public NewDiagramAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("new_diagram"));
        putValue(NAME, "New diagram");
        putValue(SHORT_DESCRIPTION, "New diagram");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project parent = (Project)Engine.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
        Diagram diagram =new Diagram();
        parent.addDiagram(diagram);

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
