package model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * Created by Marko on 01-Apr-15.
 */
public class WorkspaceModel extends DefaultTreeModel {

    public WorkspaceModel() {
        super(new Workspace());
    }

    public void addProject(Project project){
        ((Workspace)getRoot()).addProject(project);
    }

    public void addOpenedProject(Project project, String name){
        ((Workspace)getRoot()).add(project);
        project.setName(name);
    }
}
