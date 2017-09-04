package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 06-Jun-15.
 */
public class BestFitZoomAction extends AbstractAction {
    public BestFitZoomAction(){
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("best_zoom"));
        putValue(NAME, "Best fit zoom");
        putValue(SHORT_DESCRIPTION, "Best fit zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null) view.bestFitZoom();
    }
}
