package model;

import engine.Engine;
import view.DiagramView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Marko on 01-Apr-15.
 */
public class Workspace extends DefaultMutableTreeNode {

    public Workspace() {
        super();
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
    }

    @Override
    public void remove(MutableTreeNode aChild) {
        super.remove((Project) aChild);
    }

    public void addProject(Project project) {
        project.setName("Project - " + getChildCount());
        add(project);

        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
        Engine.getInstance().getWorkspaceTree().expandPath(new TreePath(getPath()));
    }

    public void removeProject(Project project) {
        remove(project);

        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
    }

    public boolean isAvailableProjectName(String name, Project project) {
        for (int i = 0; i < getProjectsCount(); i++)
            if (getProject(i) != project && getProject(i).getName().equalsIgnoreCase(name)) return false;
        return true;
    }

    public Project getProject(int index) {
        return (Project)getChildAt(index);
    }

    public int getProjectIndex(Project project) {
        return getIndex(project);
    }

    public int getProjectsCount() {
        return getChildCount();
    }

    public String toString() {
        return "Workspace";
    }

    public void reset() {
        removeAllChildren();
        Engine.getInstance().getDesktop().removeAll();
        DiagramView.setOpenFrameCount(0);
    }
}
