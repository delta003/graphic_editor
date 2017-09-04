package model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;

/**
 * Created by Marko on 24-May-15.
 */
public class ElementFolder extends DefaultMutableTreeNode implements Serializable {
    String name;
    DiagramModel model;

    public ElementFolder(String name, DiagramModel model) {
        this.name = name;
        this.model = model;
    }

    @Override
    public String toString() {
        return name;
    }
}
