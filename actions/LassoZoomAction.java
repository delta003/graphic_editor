package actions;

import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 06-Jun-15.
 */
public class LassoZoomAction extends AbstractAction {
    public LassoZoomAction(){
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("lasso_zoom"));
        putValue(NAME, "Lasso zoom");
        putValue(SHORT_DESCRIPTION, "Lasso zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view != null) view.startLassoZoomState();
    }
}
