package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 24-May-15.
 */
public class PLinkAction extends AbstractAction {
    public PLinkAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("link"));
        putValue(SHORT_DESCRIPTION, "Link");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null){
            diagramView.startLinkState();
            Engine.getInstance().getStatusBar().update();
        }
    }
}
