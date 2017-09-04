package model;

import engine.Engine;
import event.UpdateEvent;
import event.UpdateListener;
import utils.Utils;
import view.DiagramView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.Serializable;

/**
 * Created by Marko on 01-Apr-15.
 */
public class Project extends DefaultMutableTreeNode implements Serializable {
    private String name;
    private File projectFile;

    public Project(String name) {
        super();
        this.name = name;
    }

    public Project() {
        this("Project");
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
    }

    @Override
    public void remove(MutableTreeNode aChild) {
        super.remove((Diagram) aChild);
    }

    public void addDiagram(Diagram diagram) {
        diagram.setName( "Diagram - " + getChildCount());
        add(diagram);

        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
        Engine.getInstance().getWorkspaceTree().expandPath(new TreePath(getPath()));
    }

    public void removeDiagram(Diagram diagram) {
        DiagramView dview = Utils.getInstance().getDiagramViewWithDiagram(diagram);
        if (dview != null) dview.hideView();
        Engine.getInstance().getDesktop().remove(dview);

        remove(diagram);

        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
    }

    public String toString(){
        return name;
    }

    public Diagram getDiagram(int index) {
        return (Diagram)getChildAt(index);
    }

    public int getDiagramIndex(Diagram diagram) {
        return getIndex(diagram);
    }

    public int getDiagramCount() {
        return getChildCount();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailableDiagramName(String name, Diagram d) {
        for (int i = 0; i < getDiagramCount(); i++)
            if (getDiagram(i) != d && getDiagram(i).getName().equalsIgnoreCase(name)) return false;
        return true;
    }

    public File getProjectFile() {
        return projectFile;
    }


    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }
}
