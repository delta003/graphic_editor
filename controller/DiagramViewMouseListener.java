package controller;

import view.DiagramView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Marko on 25-May-15.
 */
public class DiagramViewMouseListener extends MouseAdapter {
    @Override
    public void mouseExited(MouseEvent e) {
        ((DiagramView.Framework)e.getSource()).stop();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((DiagramView.Framework)e.getSource()).start();
    }
}
