package actions;

import engine.Engine;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Marko on 01-Apr-15.
 */
public class PreviousDiagramAction extends AbstractAction {
    public PreviousDiagramAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("previous"));
        putValue(NAME, "Previus diagram");
        putValue(SHORT_DESCRIPTION, "Previous diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DiagramView> list = Utils.getInstance().getCreationSortedVisibleDiagramViews();
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).isSelected()) {
                DiagramView prev = list.get((i - 1 + list.size()) % list.size());
                Engine.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(prev.getDiagram().getPath()));
                prev.showView();
                break;
            }
    }
}
