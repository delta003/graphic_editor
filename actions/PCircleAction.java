package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PCircleAction extends AbstractAction {
    public PCircleAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("circle"));
        putValue(SHORT_DESCRIPTION, "Circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startCircleState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
