package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 06-Jun-15.
 */
public class UndoAction extends AbstractAction {
    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("undo"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null) view.getCommandManager().undoCommand();
    }
}
