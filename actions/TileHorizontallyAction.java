package actions;

import engine.Engine;
import resources.Loader;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Marko on 01-Apr-15.
 */
public class TileHorizontallyAction extends AbstractAction {
    public TileHorizontallyAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("horizontally"));
        putValue(NAME, "Tile Horizontally");
        putValue(SHORT_DESCRIPTION, "Tile diagrams horizontally");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DiagramView> list = Utils.getInstance().getCreationSortedVisibleDiagramViews();
        int count = list.size();
        if (count == 0) return;
        int sizeX = Engine.getInstance().getDesktop().getSize().width;
        int sizeY = Engine.getInstance().getDesktop().getSize().height;
        int addX = sizeX / count;
        int x = 0;
        for (DiagramView d : list) {
            d.setSize(addX, sizeY);
            d.showView(x, 0);
            x += addX;
        }
    }
}
