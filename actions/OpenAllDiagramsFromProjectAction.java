package actions;

import engine.Engine;
import model.Diagram;
import model.Project;
import model.Workspace;
import resources.Loader;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 11-Apr-15.
 */
public class OpenAllDiagramsFromProjectAction extends AbstractAction {
    public OpenAllDiagramsFromProjectAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("openall"));
        putValue(NAME, "Open all diagrams");
        putValue(SHORT_DESCRIPTION, "Open all diagrams");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project p = Utils.getInstance().getSelectedProject();
        if (p == null) return;
        for (int i = 0; i < p.getDiagramCount(); i++) {
            Diagram d = p.getDiagram(i);
            Utils.getInstance().getDiagramViewWithDiagram(d).showView();
        }
    }
}
