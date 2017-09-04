package actions;

import engine.Engine;
import model.Diagram;
import model.Project;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 01-Apr-15.
 */
public class DeleteDiagramAction extends AbstractAction {
    public DeleteDiagramAction() {putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
            KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("delete_diagram"));
        putValue(NAME, "Delete diagram");
        putValue(SHORT_DESCRIPTION, "Delete diagram");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Diagram d = Utils.getInstance().getSelectedDiagram();
        if (d != null) ((Project)d.getParent()).removeDiagram(d);
        Engine.getInstance().getWorkspaceTree().setSelectionPath(null);
    }
}
