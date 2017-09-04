package gui;

import controller.WorkspaceTreeController;
import controller.WorkspaceTreeListener;
import model.Project;
import model.WorkspaceModel;
import view.WorkspaceTreeCellRenderer;
import view.WorkspaceTreeEditor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Created by Marko on 01-Apr-15.
 */
public class WorkspaceTree extends JTree {

    public WorkspaceTree() {

        addTreeSelectionListener(new WorkspaceTreeController());
        setCellEditor(new WorkspaceTreeEditor(this, new DefaultTreeCellRenderer()));
        setCellRenderer(new WorkspaceTreeCellRenderer());
        setEditable(false);
        addMouseListener(new WorkspaceTreeListener());
    }

    public void addProject(Project project){
        ((WorkspaceModel)getModel()).addProject(project);
    }

    public void addOpenedProject(Project project, String name){
        ((WorkspaceModel)getModel()).addOpenedProject(project, name);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public Project getCurrentProject() {
        TreePath path = getSelectionPath();
        for(int i=0; i<path.getPathCount(); i++){
            if (path.getPathComponent(i) instanceof Project){
                return (Project)path.getPathComponent(i);
            }
        }
        return null;
    }
}
