package actions;

import model.Diagram;
import model.Project;
import resources.Loader;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 11-Apr-15.
 */
public class CloseAllDiagramsFromProjectAction extends AbstractAction {
    public CloseAllDiagramsFromProjectAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("closeall"));
        putValue(NAME, "Close all diagrams");
        putValue(SHORT_DESCRIPTION, "Close all diagrams");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project p = Utils.getInstance().getSelectedProject();
        if (p == null) return;
        for (int i = 0; i < p.getDiagramCount(); i++) {
            Diagram d = p.getDiagram(i);
            Utils.getInstance().getDiagramViewWithDiagram(d).hideView();
        }
    }
}
