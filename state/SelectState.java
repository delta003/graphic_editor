package state;

import elements.DiagramElement;
import engine.Engine;
import gui.Popup;
import jdk.internal.org.objectweb.asm.Handle;
import view.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class SelectState extends State{
    private DiagramView diagramView;
    private int elementInMotion = -1;
    private int elementID = -1;
    private boolean selected = false;
    private DiagramView.Handle handleInMotion = null;

    public SelectState(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        diagramView.transformToUserSpace(position);
        if(SwingUtilities.isLeftMouseButton(e)){
            if(e.getClickCount() == 2){
                elementInMotion = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
                if (elementInMotion != -1) {
                    DiagramElement element = diagramView.getDiagram().getDiagramModel().getElementAt(elementInMotion);
                    diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                    diagramView.getDiagram().getSelectionModel().addToSelectionList(element);
                    diagramView.getDiagram().getSelectionModel().updateJTree();
                    Engine.getInstance().getActionManager().getElementPropertiesAction().actionPerformed(null);
                }else{
                    if(!e.isControlDown())
                        diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                    diagramView.getDiagram().getSelectionModel().updateJTree();
                }
            }else {
                handleInMotion = diagramView.getDeviceAndHandleForPoint(position);
                if(handleInMotion == null){
                    elementInMotion = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
                    if(elementInMotion != -1){
                        selected = true;
                        DiagramElement element = diagramView.getDiagram().getDiagramModel().getElementAt(elementInMotion);
                        if(diagramView.getDiagram().getSelectionModel().isElementSelected(element)){
                            elementID = elementInMotion;
                        }else{
                            if(!e.isControlDown())
                                diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                            diagramView.getDiagram().getSelectionModel().addToSelectionList(element);
                            diagramView.getDiagram().getSelectionModel().updateJTree();
                        }
                    }else{
                        selected = false;
                        elementID = -1;
                        diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                        diagramView.getDiagram().getSelectionModel().updateJTree();
                        Engine.getInstance().getStatusBar().update();
                    }
                }
            }
            diagramView.getDiagram().getSelectionModel().updateJTree();
        }
    }

    public void mouseReleased(MouseEvent e) {
        selected = false;
        Point position = e.getPoint();
        diagramView.transformToUserSpace(position);
        elementInMotion = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
        if (elementInMotion != -1) {
            if(elementInMotion == elementID){
                DiagramElement element = diagramView.getDiagram().getDiagramModel().getElementAt(elementInMotion);
                if (diagramView.getDiagram().getSelectionModel().isElementSelected(element)) {
                    if (!e.isControlDown()) {
                        diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                        diagramView.getDiagram().getSelectionModel().addToSelectionList(element);
                    } else {
                        diagramView.getDiagram().getSelectionModel().removeFromSelectionList(element);
                    }
                }else{
                    if(!e.isControlDown())
                        diagramView.getDiagram().getSelectionModel().removeAllFromSelectionList();
                    diagramView.getDiagram().getSelectionModel().addToSelectionList(element);
                    diagramView.getDiagram().getSelectionModel().updateJTree();
                }
                elementID = -1;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        Point2D point = e.getPoint();
        diagramView.transformToUserSpace(point);
        diagramView.setMouseCursor(point);
    }

    public void mouseDragged(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            Point position = e.getPoint();
            diagramView.transformToUserSpace(position);
            handleInMotion = diagramView.getDeviceAndHandleForPoint(position);
            if(handleInMotion != null){
                diagramView.startResizeState();
            }else{
                elementInMotion = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
                if(selected ){
                    if(!e.isControlDown())
                        diagramView.startMoveState();
                    return;
                } else
                    diagramView.startLassoState();
            }
        }
        Engine.getInstance().getStatusBar().update();
    }
}
