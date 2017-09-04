package view;

import actions.RenameAction;
import elements.DiagramElement;
import engine.Engine;
import model.Diagram;
import model.ElementFolder;
import model.Project;
import model.Workspace;
import utils.Utils;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

/**
 * Created by Marko on 01-Apr-15.
 */
public class WorkspaceTreeEditor extends DefaultTreeCellEditor {
    private Object value;
    private JTextField field;

    public WorkspaceTreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        Object selected = Engine.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
        return (selected instanceof Project || selected instanceof Diagram);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        this.value = value;
        field = new JTextField(value.toString());
        field.addActionListener(this);
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (value instanceof Project) {
            Project p = (Project) value;
            if (((Workspace) p.getParent()).isAvailableProjectName(e.getActionCommand().trim(), p)) {
                p.setName(e.getActionCommand().trim());
                // Rename children
                for(int i = 0 ; i < p.getDiagramCount(); i++) {
                    DiagramView dview = Utils.getInstance().getDiagramViewWithDiagram(p.getDiagram(i));
                    if (dview != null) dview.setTitle(p.getDiagram(i).getLongName());
                }
            } else {
                JOptionPane.showMessageDialog(null, "There is a project with same name! Choose other.");
            }
        } else if (value instanceof Diagram) {
            Diagram d = (Diagram) value;
            if (((Project) d.getParent()).isAvailableDiagramName(e.getActionCommand().trim(), d)) {
                d.setName(e.getActionCommand().trim());
                DiagramView dview = Utils.getInstance().getDiagramViewWithDiagram(d);
                if (dview != null) dview.setTitle(d.getLongName());
            } else {
                JOptionPane.showMessageDialog(null, "There is a diagram with same name! Choose other.");
            }
        } else if (value instanceof DiagramElement) {
            DiagramElement element = (DiagramElement) value;
            ElementFolder elementFolder = (ElementFolder) element.getParent();
            Diagram diagram = (Diagram) elementFolder.getParent();
            if (element.getName().equals(e.getActionCommand())) return;
            if (diagram.getDiagramModel().isAvelableName(e.getActionCommand())){
                element.setName(e.getActionCommand());
                diagram.getDiagramModel().fireUpdatePerformed(element.getRepaintBounds());
            } else {
                JOptionPane.showMessageDialog(null, "There is an element with same name! Choose other.");
            }
        }
        Engine.getInstance().getWorkspaceTree().setEditable(false);
    }
}
