package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PHandCursorAction extends AbstractAction {
    public PHandCursorAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("hand"));
        putValue(SHORT_DESCRIPTION, "Selection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startSelectState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
