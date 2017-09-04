package controller;

import actions.ActionManager;
import elements.DiagramElement;
import engine.Engine;
import model.Diagram;
import model.ElementFolder;
import model.Project;
import model.Workspace;
import utils.Utils;
import view.DiagramView;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import javax.swing.tree.TreePath;

/**
 * Created by Marko on 01-Apr-15.
 */
public class WorkspaceTreeController implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getNewLeadSelectionPath();
        ActionManager actions = Engine.getInstance().getActionManager();
        if (path == null) {
            actions.getNewDiagramAction().setEnabled(false);
            actions.getDeleteDiagramAction().setEnabled(false);
            actions.getDeleteProjectAction().setEnabled(false);
            actions.getSearchAction().setEnabled(false);
            actions.getSelectAllElementsAction().setEnabled(false);
            actions.getDeleteElementsAction().setEnabled(false);
            actions.getElementPropertiesAction().setEnabled(false);
            actions.getSaveProjectAction().setEnabled(false);
            actions.getRotateLeftAction().setEnabled(false);
            actions.getRotateRightAction().setEnabled(false);
            return;
        }
        if (path.getLastPathComponent() instanceof Project) {
            actions.getNewDiagramAction().setEnabled(true);
            actions.getDeleteProjectAction().setEnabled(true);
            actions.getDeleteDiagramAction().setEnabled(false);
            actions.getSearchAction().setEnabled(false);
            actions.getSelectAllElementsAction().setEnabled(false);
            actions.getDeleteElementsAction().setEnabled(false);
            actions.getElementPropertiesAction().setEnabled(false);
            actions.getSaveProjectAction().setEnabled(true);
            actions.getRotateLeftAction().setEnabled(false);
            actions.getRotateRightAction().setEnabled(false);
        } else if (path.getLastPathComponent() instanceof Diagram) {
            actions.getNewDiagramAction().setEnabled(false);
            actions.getDeleteProjectAction().setEnabled(false);
            actions.getDeleteDiagramAction().setEnabled(true);
            actions.getSearchAction().setEnabled(true);
            actions.getSelectAllElementsAction().setEnabled(true);
            actions.getDeleteElementsAction().setEnabled(false);
            actions.getElementPropertiesAction().setEnabled(false);
            actions.getSaveProjectAction().setEnabled(false);
            actions.getRotateLeftAction().setEnabled(false);
            actions.getRotateRightAction().setEnabled(false);

            DiagramView view = Utils.getInstance().getDiagramViewWithDiagram((Diagram) path.getLastPathComponent());
            view.showView();
        } else if (path.getLastPathComponent() instanceof Workspace) {
            actions.getNewDiagramAction().setEnabled(false);
            actions.getDeleteDiagramAction().setEnabled(false);
            actions.getDeleteProjectAction().setEnabled(false);
            actions.getSearchAction().setEnabled(false);
            actions.getSelectAllElementsAction().setEnabled(false);
            actions.getDeleteElementsAction().setEnabled(false);
            actions.getElementPropertiesAction().setEnabled(false);
            actions.getSaveProjectAction().setEnabled(false);
            actions.getRotateLeftAction().setEnabled(false);
            actions.getRotateRightAction().setEnabled(false);
        } else if (path.getLastPathComponent() instanceof DiagramElement) {
            actions.getNewDiagramAction().setEnabled(false);
            actions.getDeleteDiagramAction().setEnabled(false);
            actions.getDeleteProjectAction().setEnabled(false);
            actions.getSearchAction().setEnabled(true);
            actions.getSelectAllElementsAction().setEnabled(true);
            actions.getDeleteElementsAction().setEnabled(true);
            actions.getElementPropertiesAction().setEnabled(true);
            actions.getSaveProjectAction().setEnabled(false);
            actions.getRotateLeftAction().setEnabled(true);
            actions.getRotateRightAction().setEnabled(true);

            DiagramElement diagramElement = (DiagramElement) path.getLastPathComponent();
            ElementFolder elementFolder = (ElementFolder)diagramElement.getParent();
            if (elementFolder != null) {
                Diagram diagram = (Diagram)elementFolder.getParent();
                diagram.refreshSelectedElements();
            }
        } else {
            actions.getNewDiagramAction().setEnabled(false);
            actions.getDeleteDiagramAction().setEnabled(false);
            actions.getDeleteProjectAction().setEnabled(false);
            actions.getSearchAction().setEnabled(false);
            actions.getSelectAllElementsAction().setEnabled(false);
            actions.getDeleteElementsAction().setEnabled(false);
            actions.getElementPropertiesAction().setEnabled(false);
            actions.getSaveProjectAction().setEnabled(false);
            actions.getRotateLeftAction().setEnabled(false);
            actions.getRotateRightAction().setEnabled(false);
        }
    }
}
