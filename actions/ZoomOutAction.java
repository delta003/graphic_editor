package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 06-Jun-15.
 */
public class ZoomOutAction extends AbstractAction {
    public ZoomOutAction(){
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("zoom_out"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null) view.zoomOut();
    }
}
