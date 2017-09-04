package actions;

import engine.Engine;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Marko on 01-Apr-15.
 */
public class CloseAllDiagramsAction extends AbstractAction {
    public CloseAllDiagramsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("closeall"));
        putValue(NAME, "Close all");
        putValue(SHORT_DESCRIPTION, "Close all diagrams");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Utils.getInstance().getSelectedDiagram() != null)
            Engine.getInstance().getWorkspaceTree().setSelectionPath(null);
        ArrayList<DiagramView> list = Utils.getInstance().getVisibleDiagramViews();
        list.forEach(view.DiagramView::hideView);
    }
}
