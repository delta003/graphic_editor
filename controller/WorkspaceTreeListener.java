package controller;

import engine.Engine;
import gui.*;
import gui.Popup;
import model.Diagram;
import model.Project;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Marko on 11-Apr-15.
 */
public class WorkspaceTreeListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (SwingUtilities.isRightMouseButton(e)) {
            TreePath path = Engine.getInstance().getWorkspaceTree().getPathForLocation(e.getX(), e.getY());
            if (path == null) return;
            Engine.getInstance().getWorkspaceTree().setSelectionPath(path);
            Object lastComponent = path.getLastPathComponent();
            if (lastComponent instanceof Project) {
                Engine.getInstance().getPopup().setType(Popup.PopupType.TPROJECT);
                Engine.getInstance().getPopup().show(e.getComponent(), e.getX(), e.getY());
            } else if (lastComponent instanceof Diagram) {
                Engine.getInstance().getPopup().setType(Popup.PopupType.TDIAGRAM);
                Engine.getInstance().getPopup().show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
}
