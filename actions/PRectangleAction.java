package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PRectangleAction extends AbstractAction {
    public PRectangleAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("rectangle"));
        putValue(SHORT_DESCRIPTION, "Rectangle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startRectangleState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
