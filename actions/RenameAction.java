package actions;

import engine.Engine;
import resources.Loader;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 11-Apr-15.
 */
public class RenameAction extends AbstractAction {
    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("rename"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreePath path = Engine.getInstance().getWorkspaceTree().getSelectionPath();
        if (path != null) {
            Engine.getInstance().getWorkspaceTree().setEditable(true);
            Engine.getInstance().getWorkspaceTree().startEditingAtPath(path);
        }
    }
}
