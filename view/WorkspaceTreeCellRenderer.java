package view;

import model.Diagram;
import model.ElementFolder;
import model.Project;
import model.Workspace;
import resources.Loader;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

/**
 * Created by Marko on 01-Apr-15.
 */
public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer {

    public WorkspaceTreeCellRenderer() { super(); }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof Diagram) setIcon(Loader.getInstance().loadIcon("tdiagram"));
        else if (value instanceof Project) setIcon(Loader.getInstance().loadIcon("tproject"));
        else if (value instanceof ElementFolder) setIcon(Loader.getInstance().loadIcon("tproject"));
        else if (value instanceof Workspace) setIcon(Loader.getInstance().loadIcon("tworkspace"));
        else setIcon(Loader.getInstance().loadIcon("telement"));

        return this;
    }

}
