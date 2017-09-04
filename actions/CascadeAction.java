package actions;

import model.Diagram;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Marko on 01-Apr-15.
 */
public class CascadeAction extends AbstractAction {
    public CascadeAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("cascade"));
        putValue(NAME, "Cascade");
        putValue(SHORT_DESCRIPTION, "Show diagrams as cascade");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DiagramView> diagrams = Utils.getInstance().getCreationSortedVisibleDiagramViews();
        int x = 0, y = 0;
        for (DiagramView d : diagrams) {
            d.setSize(DiagramView.defaultSizeX, DiagramView.defaultSizeY);
            d.showView(x, y);
            x += DiagramView.xOffset;
            y += DiagramView.yOffset;
        }
    }
}
