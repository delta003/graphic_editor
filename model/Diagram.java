package model;

import state.State;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.Serializable;
import java.util.Enumeration;

/**
 * Created by Marko on 01-Apr-15.
 */
public class Diagram extends DefaultMutableTreeNode implements Serializable {
    private String name;

    private DiagramModel diagramModel;
    private DiagramSelectionModel selectionModel;

    private State currentState;
    private DeviceType deviceType;
    private boolean hasRectangles, hasCircles, hasTriangles, hasStars, hasLinks;

    public Diagram(String name) {
        this.name = name;
    }

    public Diagram() {
        this("Diagram");
        diagramModel = new DiagramModel(name);
        currentState = new State();
        hasRectangles = false;
        hasCircles = false;
        hasTriangles = false;
        hasStars = false;
        hasLinks = false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        if (getParent() == null) return name;
        else return ((Project)getParent()).getName() + " - " + name;
    }

    public DiagramModel getDiagramModel() {
        return diagramModel;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public boolean isHasRectangles() {
        return hasRectangles;
    }

    public void setHasRectangles(boolean hasRectangles) {
        this.hasRectangles = hasRectangles;
    }

    public boolean isHasCircles() {
        return hasCircles;
    }

    public void setHasCircles(boolean hasCircles) {
        this.hasCircles = hasCircles;
    }

    public boolean isHasTriangles() {
        return hasTriangles;
    }

    public void setHasTriangles(boolean hasTriangles) {
        this.hasTriangles = hasTriangles;
    }

    public boolean isHasStars() {
        return hasStars;
    }

    public void setHasStars(boolean hasStars) {
        this.hasStars = hasStars;
    }

    public boolean isHasLinks() {
        return hasLinks;
    }

    public void setHasLinks(boolean hasLinks) {
        this.hasLinks = hasLinks;
    }

    public DiagramSelectionModel getSelectionModel() {
        if(selectionModel == null)
            selectionModel = new DiagramSelectionModel();
        return selectionModel;
    }

    public void refreshSelectedElements(){
        diagramModel.refreshElementsSelection(this);
    }
}
