package commands;

import elements.DiagramDevice;
import elements.DiagramElement;
import elements.LinkElement;
import model.DiagramModel;
import model.DiagramSelectionModel;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 06-Jun-15.
 */
public class MoveDeviceCommand implements AbstractCommand {
    ArrayList<DiagramElement> movedElements=new ArrayList<DiagramElement>();
    DiagramSelectionModel tempSelectionModel=new DiagramSelectionModel();

    boolean firstAction;

    double deltaX;
    double deltaY;

    public MoveDeviceCommand(DiagramModel model, DiagramSelectionModel gsm,double x,double y) {
        for(int i = 0; i < gsm.getSelectionListSize(); i++){
            DiagramElement element =  gsm.getElementFromSelectionListAt(i);
            if (element instanceof DiagramDevice || element instanceof LinkElement){
                movedElements.add(gsm.getElementFromSelectionListAt(i));
            }
        }

        this.tempSelectionModel=gsm;
        firstAction=true;
        deltaX=x;
        deltaY=y;
    }

    @Override
    public void doCommand() {
        if(firstAction){
            firstAction=false;
        }
        else{
            tempSelectionModel.addToSelectionList((ArrayList<DiagramElement>) movedElements.clone());
            for (DiagramElement element : movedElements) {
                if (element instanceof DiagramDevice) {
                    DiagramDevice device = (DiagramDevice) element;
                    Point2D newPosition = (Point2D) device.getPosition().clone();
                    newPosition.setLocation(newPosition.getX() + deltaX, newPosition.getY() + deltaY);
                    device.setPosition(newPosition);
                } else if (element instanceof LinkElement) {
                    LinkElement link = (LinkElement) element;
                    Iterator<Point2D> it1 = link.getPointsIterator();
                    while (it1.hasNext()) {
                        Point2D tren = it1.next();
                        tren.setLocation(tren.getX() + deltaX, tren.getY() + deltaY);
                    }
                }
            }
            tempSelectionModel.removeAllFromSelectionList();
        }
    }

    @Override
    public void undoCommand() {
        tempSelectionModel.addToSelectionList((ArrayList<DiagramElement>) movedElements.clone());
        for (DiagramElement element : movedElements) {
            if (element instanceof DiagramDevice) {
                DiagramDevice device = (DiagramDevice) element;
                Point2D newPosition = (Point2D) device.getPosition().clone();
                newPosition.setLocation(newPosition.getX() - deltaX, newPosition.getY() - deltaY);
                device.setPosition(newPosition);

            } else if (element instanceof LinkElement) {
                LinkElement link = (LinkElement) element;
                Iterator<Point2D> it1 = link.getPointsIterator();
                while (it1.hasNext()) {
                    Point2D tren = it1.next();
                    tren.setLocation(tren.getX() - deltaX, tren.getY() - deltaY);
                }
            }
        }
        tempSelectionModel.removeAllFromSelectionList();
    }
}
