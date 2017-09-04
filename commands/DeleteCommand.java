package commands;

import elements.DiagramDevice;
import elements.DiagramElement;
import elements.LinkElement;
import engine.Engine;
import view.DiagramView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 06-Jun-15.
 */
public class DeleteCommand implements AbstractCommand {
    private DiagramView diagramView;
    private ArrayList<DiagramElement> deletedElements;

    public DeleteCommand(DiagramView diagramView) {
        this.diagramView = diagramView;
        this.deletedElements =
                ((ArrayList<DiagramElement>)diagramView.getDiagram().getSelectionModel().getSelected().clone());
        if (!diagramView.getDiagram().getSelectionModel().getSelectionList().isEmpty()){

            for (int i = 0; i < diagramView.getDiagram().getDiagramModel().getElementCount(); i++) {
                if (diagramView.getDiagram().getDiagramModel().getElementAt(i) instanceof LinkElement) {
                    DiagramElement dd1 =
                            ((LinkElement)diagramView.getDiagram().getDiagramModel().getElementAt(i)).getStartDevice();
                    DiagramElement dd2 =
                            ((LinkElement)diagramView.getDiagram().getDiagramModel().getElementAt(i)).getEndDevice();
                    if (diagramView.getDiagram().getSelectionModel().hasElement(dd1) ||
                            diagramView.getDiagram().getSelectionModel().hasElement(dd2)){
                        deletedElements.add(diagramView.getDiagram().getDiagramModel().getElementAt(i));
                    }
                }
            }
        }
    }

    @Override
    public void doCommand() {
        for (DiagramElement element : this.deletedElements) {
            if (element instanceof LinkElement) {
                LinkElement link = (LinkElement) element;
                link.getInput().setFree(true);
                link.getOutput().setFree(true);
            }
            this.diagramView.getDiagram().getSelectionModel().removeFromSelectionList(element);
            this.diagramView.getDiagram().getDiagramModel().removeFromJTree(element);
            this.diagramView.getDiagram().getDiagramModel().removeDiagramElement(element);
            SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
        }
    }

    @Override
    public void undoCommand() {
        for (DiagramElement element : this.deletedElements) {
            if (element instanceof LinkElement) {
                LinkElement link = (LinkElement) element;
                link.getInput().setFree(false);
                link.getOutput().setFree(false);
            }
            if (element instanceof LinkElement)
                AddLinkCommand.makeElementNode((LinkElement) element, diagramView.getDiagram());
            else
                AddDeviceCommand.makeElementNode((DiagramDevice) element, diagramView.getDiagram());
            this.diagramView.getDiagram().getDiagramModel().addDiagramElement(element);
        }
    }
}
