package commands;

import elements.*;
import model.*;

import java.awt.geom.Point2D;

/**
 * Created by Marko on 06-Jun-15.
 */
public class AddDeviceCommand implements AbstractCommand {
    private DiagramModel model;
    private DiagramSelectionModel selectionModel;
    private Point2D position;
    private DiagramElement device = null;
    private DeviceType stateType;
    private Diagram diagram;

    public AddDeviceCommand(Diagram diagram, Point2D position, DeviceType stateType) {
        this.diagram = diagram;
        this.model = diagram.getDiagramModel();
        this.position = position;
        this.stateType = stateType;
        this.selectionModel = diagram.getSelectionModel();
    }

    @Override
    public void doCommand() {
        int elementNumber = model.getCount();
        if(device == null){
            if(stateType == DeviceType.CIRCLE){
                device = CircleElement.createDefault(position, elementNumber);
                while(!model.isAvelableName(device.getName())){
                    device.setName("Star " + model.getCount() );
                }
            } else if(stateType == DeviceType.TRIANGLE){
                device = TriangleElement.createDefault(position, elementNumber);
                while(!model.isAvelableName(device.getName())){
                    device.setName("Star " + model.getCount() );
                }
            } else if(stateType == DeviceType.RECTANGLE){
                device = RectangleElement.createDefault(position, elementNumber);
                while(!model.isAvelableName(device.getName())){
                    device.setName("Star " + model.getCount() );
                }
            } else if(stateType == DeviceType.STAR){
                device = StarElement.createDefault(position, elementNumber);
                while(!model.isAvelableName(device.getName())){
                    device.setName("Star " + model.getCount() );
                }
            }
        }

        makeElementNode(device,diagram);
        model.addDiagramElement(device);
        selectionModel.removeAllFromSelectionList();
        selectionModel.addToSelectionList(device);
        selectionModel.updateJTree();
    }

    public void undoCommand() {
        selectionModel.removeAllFromSelectionList();
        model.removeFromJTree(device);
        model.removeDiagramElement(device);
        selectionModel.updateJTree();
    }

    public static void makeElementNode(DiagramElement element, Diagram diagram){
        ElementFolder elementFolder = null;
        String elementType = "rectangles";
        if(element instanceof RectangleElement) elementType = "rectangles";
        if(element instanceof TriangleElement) elementType = "triangles";
        if(element instanceof CircleElement) elementType = "circles";
        if(element instanceof StarElement) elementType = "stars";
        if(element instanceof LinkElement) elementType = "links";
        int i;
        for(i = 0; i < diagram.getChildCount(); i++)
            if(diagram.getChildAt(i).toString().equals(elementType)){
                elementFolder = (ElementFolder)diagram.getChildAt(i);
                elementFolder.add(element);
                break;
            }

        if(i!= diagram.getChildCount()) return;

        switch (elementType) {
            case "rectangles":
                diagram.setHasRectangles(true);
                elementFolder = new ElementFolder("rectangles", diagram.getDiagramModel());
                diagram.insert(elementFolder, 0);
                break;
            case "triangles":
                elementFolder = new ElementFolder("triangles", diagram.getDiagramModel());
                if (diagram.isHasRectangles()) diagram.insert(elementFolder, 1);
                else diagram.insert(elementFolder, 0);
                diagram.setHasTriangles(true);
                break;
            case "circles":
                elementFolder = new ElementFolder("circles", diagram.getDiagramModel());
                if (diagram.isHasRectangles() && diagram.isHasTriangles()) diagram.insert(elementFolder, 2);
                else if (diagram.isHasRectangles()) diagram.insert(elementFolder, 1);
                else if (diagram.isHasTriangles()) diagram.insert(elementFolder, 1);
                else diagram.insert(elementFolder, 0);
                diagram.setHasCircles(true);
                break;
            case "stars":
                int number = diagram.getChildCount();
                elementFolder = new ElementFolder("stars", diagram.getDiagramModel());
                if (diagram.isHasLinks()) number--;
                diagram.insert(elementFolder, number);
                break;
        }
        if (elementFolder != null) {
            elementFolder.add(element);
        }
    }
}
