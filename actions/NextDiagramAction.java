package actions;

import engine.Engine;
import model.Diagram;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marko on 01-Apr-15.
 */
public class NextDiagramAction extends AbstractAction {
    public NextDiagramAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("next"));
        putValue(NAME, "Next diagram");
        putValue(SHORT_DESCRIPTION, "Next diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DiagramView> list = Utils.getInstance().getCreationSortedVisibleDiagramViews();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).isSelected()) {
                DiagramView next = list.get((i + 1) % list.size());
                Engine.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(next.getDiagram().getPath()));
                next.showView();
                break;
            }
    }
}
