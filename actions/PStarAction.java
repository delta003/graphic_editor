package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PStarAction extends AbstractAction {
    public PStarAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("star"));
        putValue(SHORT_DESCRIPTION, "Star");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startStarState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
