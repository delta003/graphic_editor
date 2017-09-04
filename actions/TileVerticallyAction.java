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
public class TileVerticallyAction extends AbstractAction {
    public TileVerticallyAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("vertically"));
        putValue(NAME, "Tile Vertically");
        putValue(SHORT_DESCRIPTION, "Tile diagrams vertically");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DiagramView> list = Utils.getInstance().getCreationSortedVisibleDiagramViews();
        int count = list.size();
        if (count == 0) return;
        int sizeX = Engine.getInstance().getDesktop().getSize().width;
        int sizeY = Engine.getInstance().getDesktop().getSize().height;
        int addY = sizeY / count;
        int y = 0;
        for (DiagramView d : list) {
            d.setSize(sizeX, addY);
            d.showView(0, y);
            y += addY;
        }
    }
}
