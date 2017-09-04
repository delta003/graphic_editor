package gui;

import actions.ActionManager;
import engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Marko on 01-Apr-15.
 */
public class Menu extends JMenuBar {
    public Menu() {
        ActionManager actions = Engine.getInstance().getActionManager();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(actions.getNewProjectAction());
        fileMenu.add(actions.getNewDiagramAction());
        fileMenu.addSeparator();
        fileMenu.add(actions.getOpenWorkspaceAction());
        fileMenu.add(actions.getOpenProjectAction());
        fileMenu.addSeparator();
        fileMenu.add(actions.getSaveWorkspaceAction());
        fileMenu.add(actions.getSaveProjectAction());
        fileMenu.addSeparator();
        fileMenu.add(actions.getDeleteProjectAction());
        fileMenu.add(actions.getDeleteDiagramAction());
        add(fileMenu);

        JMenu windowMenu = new JMenu("Window");
        windowMenu.add(actions.getCascadeAction());
        windowMenu.add(actions.getTileHorizontallyAction());
        windowMenu.add(actions.getTileVerticallyAction());
        windowMenu.addSeparator();
        windowMenu.add(actions.getNextDiagramAction());
        windowMenu.add(actions.getPreviousDiagramAction());
        windowMenu.addSeparator();
        windowMenu.add(actions.getCloseDiagramAction());
        windowMenu.add(actions.getCloseAllDiagramsAction());
        add(windowMenu);

        JMenu elementsMenu = new JMenu("Elements");
        elementsMenu.add(actions.getSearchAction());
        elementsMenu.add(actions.getSelectAllElementsAction());
        elementsMenu.addSeparator();
        elementsMenu.add(actions.getElementPropertiesAction());
        elementsMenu.addSeparator();
        elementsMenu.add(actions.getDeleteElementsAction());
        add(elementsMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(actions.getAboutAction());
        add(helpMenu);
    }
}
