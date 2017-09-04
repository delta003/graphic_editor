package gui;

import actions.ActionManager;
import engine.Engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 01-Apr-15.
 */
public class Toolbar extends JToolBar {

    public Toolbar() {
        super();
        setFloatable(false);

        ActionManager actions = Engine.getInstance().getActionManager();

        addSeparator(new Dimension(10, 0));
        add(actions.getOpenWorkspaceAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getSaveWorkspaceAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getNewProjectAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getOpenProjectAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getSaveProjectAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getDeleteProjectAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getNewDiagramAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getCloseDiagramAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getCloseAllDiagramsAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getDeleteDiagramAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getCascadeAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getTileHorizontallyAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getTileVerticallyAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getPreviousDiagramAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getNextDiagramAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getRotateLeftAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getRotateRightAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getSelectAllElementsAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getElementPropertiesAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getDeleteElementsAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getUndoAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getRedoAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getZoomInAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getZoomOutAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getBestFitZoomAction());
        addSeparator(new Dimension(5, 0));
        add(actions.getLassoZoomAction());
        addSeparator(new Dimension(20, 0));

        add(actions.getSearchAction());
        addSeparator(new Dimension(20, 0));
    }
}
