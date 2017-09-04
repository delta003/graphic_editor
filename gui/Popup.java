package gui;

import actions.ActionManager;
import engine.Engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 11-Apr-15.
 */
public class Popup extends JPopupMenu {
    public enum PopupType {UNDEF, TPROJECT, TDIAGRAM};

    private PopupType type;

    public Popup() {
        super();
        type = PopupType.UNDEF;
    }

    @Override
    public void show(Component caller, int x, int y) {
        removeAll();

        if (type == PopupType.TPROJECT)
            addTreeProjectContent();
        else if (type == PopupType.TDIAGRAM)
            addTreeDiagramContent();

        super.show(caller, x, y);
    }

    private void addTreeDiagramContent() {
        ActionManager actions = Engine.getInstance().getActionManager();

        add(actions.getRenameAction());
        add(actions.getCloseDiagramAction());
        add(actions.getDeleteDiagramAction());
    }

    private void addTreeProjectContent() {
        ActionManager actions = Engine.getInstance().getActionManager();

        add(actions.getRenameAction());
        add(actions.getNewDiagramAction());
        add(actions.getOpenAllDiagramsFromProjectAction());
        add(actions.getCloseAllDiagramsFromProjectAction());
        add(actions.getSaveProjectAction());
        add(actions.getDeleteProjectAction());
    }

    public PopupType getType() {
        return type;
    }

    public void setType(PopupType type) {
        this.type = type;
    }
}
