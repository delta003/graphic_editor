package commands;

import elements.DiagramDevice;
import elements.DiagramElement;
import model.DiagramModel;
import model.DiagramSelectionModel;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Marko on 06-Jun-15.
 */
public class ResizeCommand implements AbstractCommand {
    DiagramModel model;
    boolean first;
    ArrayList<Point2D> newElementsPositions = new ArrayList<>();
    ArrayList<Point2D> oldElementsPositions = new ArrayList<>();
    ArrayList<Double> newElementsScale = new ArrayList<>();
    ArrayList<Double> oldElementsScale = new ArrayList<>();

    DiagramSelectionModel tempSelectionModel=new DiagramSelectionModel();
    ArrayList<DiagramElement> movedElements = new ArrayList<>();

    public ResizeCommand(DiagramModel model, DiagramSelectionModel gsm, ArrayList<Point2D> oldEP,
                                 ArrayList<Point2D> newEP, ArrayList<Double> oldES, ArrayList<Double> newES) {
        this.model = model;
        for(int i = 0; i < gsm.getSelectionListSize(); i++){
            this.movedElements.add(gsm.getElementFromSelectionListAt(i));
        }
        this.tempSelectionModel = gsm;
        this.first = true;
        oldElementsPositions = (ArrayList<Point2D>) oldEP.clone();
        newElementsPositions = (ArrayList<Point2D>) newEP.clone();
        oldElementsScale = (ArrayList<Double>) oldES.clone();
        newElementsScale = (ArrayList<Double>) newES.clone();
    }

    @Override
    public void doCommand() {
        if(first){
            first = false;
            return;
        }
        tempSelectionModel.addToSelectionList((ArrayList<DiagramElement>) movedElements.clone());
        for(int i = 0; i < newElementsPositions.size(); i++){
            DiagramElement element = tempSelectionModel.getElementFromSelectionListAt(i);
            if (element instanceof DiagramDevice){
                DiagramDevice device=(DiagramDevice) element;
                device.setPosition(newElementsPositions.get(i));
                device.setScale(newElementsScale.get(i));
            }
        }
        tempSelectionModel.removeAllFromSelectionList();
    }

    @Override
    public void undoCommand() {
        tempSelectionModel.addToSelectionList((ArrayList<DiagramElement>) movedElements.clone());
        for(int i = 0; i < oldElementsPositions.size(); i++){
            DiagramElement element = tempSelectionModel.getElementFromSelectionListAt(i);
            if (element instanceof DiagramDevice ){
                DiagramDevice device=(DiagramDevice) element;
                device.setPosition(oldElementsPositions.get(i));
                device.setScale(oldElementsScale.get(i));
            }
        }
        tempSelectionModel.removeAllFromSelectionList();
    }
}
