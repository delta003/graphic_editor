package engine;

import actions.ActionManager;
import gui.*;
import gui.Menu;
import model.WorkspaceModel;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 01-Apr-15.
 *
 * Singleton class.
 */
public class Engine extends JFrame {
    private static Engine engineInstance;

    private ActionManager actionManager;
    private Toolbar toolbar;
    private gui.Menu menu;
    private Palette palette;
    private WorkspaceModel workspaceModel;
    private WorkspaceTree workspaceTree;
    private JDesktopPane desktop;
    private gui.Popup popup;
    private StatusBar statusBar;

    public static Engine getInstance() {
        if (engineInstance == null) {
            engineInstance = new Engine();
            engineInstance.initialise();
        }
        return engineInstance;
    }

    private Engine() {
    }

    private void initialise(){
        actionManager = new ActionManager();

        initialiseWorkspaceTree();
        initialiseGUI();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialiseGUI(){
        popup = new gui.Popup();

        menu = new gui.Menu();
        setJMenuBar(menu);

        toolbar = new Toolbar();
        toolbar.setBackground(Color.WHITE);
        getContentPane().add(toolbar, BorderLayout.NORTH);

        palette = new Palette();
        palette.setBackground(Color.WHITE);
        getContentPane().add(palette, BorderLayout.EAST);

        statusBar = new StatusBar();
        statusBar.setBackground(Color.WHITE);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        desktop = new JDesktopPane();
        desktop.setBackground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200, 150));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        split.setDividerLocation(200);

        add(split, BorderLayout.CENTER);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Graphic Editor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initialiseWorkspaceTree(){
        workspaceTree = new WorkspaceTree();
        workspaceModel = new WorkspaceModel();
        workspaceTree.setModel(workspaceModel);
        ToolTipManager.sharedInstance().registerComponent(workspaceTree);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public WorkspaceTree getWorkspaceTree() {
        return workspaceTree;
    }

    public WorkspaceModel getWorkspaceModel() {
        return workspaceModel;
    }

    public gui.Popup getPopup() {
        return popup;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public Palette getPalette() {
        return palette;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Menu getMenu() {
        return menu;
    }
}
