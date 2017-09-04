package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PTriangleAction extends AbstractAction {
    public PTriangleAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("triangle"));
        putValue(SHORT_DESCRIPTION, "Triangle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startTriangleState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
