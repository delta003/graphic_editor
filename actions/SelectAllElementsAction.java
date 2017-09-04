package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 25-May-15.
 */
public class SelectAllElementsAction extends AbstractAction {
    public SelectAllElementsAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("select_all"));
        putValue(NAME, "Select all elements");
        putValue(SHORT_DESCRIPTION, "Select all elements");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null)
            view.getDiagram().getSelectionModel().addToSelectionList(view.getDiagram().getDiagramModel().getDiagramElements());
    }
}
