package actions;

import commands.DeleteCommand;
import elements.DiagramElement;
import elements.LinkElement;
import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 25-May-15.
 */
public class DeleteElementsAction extends AbstractAction {
    public DeleteElementsAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("delete"));
        putValue(NAME, "Delete elements");
        putValue(SHORT_DESCRIPTION, "Delete elements");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view= (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null) view.getCommandManager().addCommand(new DeleteCommand(view));
    }
}
