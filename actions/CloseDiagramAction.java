package actions;

import engine.Engine;
import model.Diagram;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 01-Apr-15.
 */
public class CloseDiagramAction extends AbstractAction {
    public CloseDiagramAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("close"));
        putValue(NAME, "Close diagram");
        putValue(SHORT_DESCRIPTION, "Close diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Diagram d = Utils.getInstance().getSelectedDiagram();
        DiagramView dview = Utils.getInstance().getDiagramViewWithDiagram(d);
        if (dview != null) {
            Engine.getInstance().getWorkspaceTree().setSelectionPath(null);
            dview.hideView();
        }
    }
}
