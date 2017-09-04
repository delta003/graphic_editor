package gui;

import elements.*;
import engine.Engine;
import model.DeviceType;
import state.DeviceState;
import state.LassoState;
import state.LinkState;
import state.SelectState;
import view.DiagramView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 24-May-15.
 */
public class StatusBar extends JPanel {
    private StatusPane status = new StatusPane("State");
    private StatusPane elementType = new StatusPane("Element type");
    private StatusPane elementName = new StatusPane("Element name");
    private StatusPane position = new StatusPane("Position");
    private StatusPane dimension = new StatusPane("Dimension");

    public StatusBar(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(status);
        add(elementType);
        add(elementName);
        add(position);
        add(dimension);
    }

    private class StatusPane extends JLabel{
        public StatusPane (String text){
            setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            setBackground(Color.GRAY);
            setPreferredSize(new Dimension(190,20));
            setHorizontalAlignment(CENTER);
            setText(text);
        }
    }

    public void setStatus(String status){
        this.status.setText(status);
    }

    public void setElementType(String elementType){
        this.elementType.setText(elementType);
    }

    public void setElementName(String elementName){
        this.elementName.setText(elementName);
    }

    public void setPosition(String position){
        this.position.setText(position);
    }

    public void setDimension(String dimension){
        this.dimension.setText(dimension);
    }

    public void update() {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (diagramView != null) {
            if (diagramView.getStateManager().getCurrentState() instanceof DeviceState) {
                Engine.getInstance().getStatusBar().setStatus("Add Element");
                DeviceType deviceType = diagramView.getStateManager().getDeviceState().getDeviceType();
                if (deviceType == DeviceType.RECTANGLE) setElementType("Rectangle");
                if (deviceType == DeviceType.TRIANGLE) setElementType("Triangle");
                if (deviceType == DeviceType.CIRCLE) setElementType("Circle");
                if (deviceType == DeviceType.STAR) setElementType("Star");
            } else if (diagramView.getStateManager().getCurrentState() instanceof LinkState){
                setStatus("Add Link");
                setElementType("");
            } else if (diagramView.getStateManager().getCurrentState() instanceof SelectState){
                setStatus("Select");
                setElementType("");
            } else if(diagramView.getStateManager().getCurrentState() instanceof LassoState){
                setStatus("Lasso");
                setElementType("");
            }
            int selectedSize = diagramView.getDiagram().getSelectionModel().getSelectionListSize();
            if (selectedSize > 0){
                if (selectedSize == 1){
                    DiagramElement diagramElement = (DiagramElement)diagramView.getDiagram().getSelectionModel().getSelectionList().get(0);
                    if (diagramElement instanceof DiagramDevice) {
                        DiagramDevice diagramDevice = (DiagramDevice) diagramElement;
                        if (diagramDevice instanceof RectangleElement) setElementType("Rectangle");
                        if(diagramDevice instanceof TriangleElement) setElementType("Triangle");
                        if(diagramDevice instanceof CircleElement) setElementType("Circle");
                        if(diagramDevice instanceof StarElement) setElementType("Star");
                        setElementName(diagramDevice.getName());
                        setDimension(diagramDevice.getSize().getWidth() + " x " + diagramDevice.getSize().getHeight());
                    }
                    else if (diagramElement instanceof LinkElement) {
                        LinkElement linkElement = (LinkElement) diagramElement;
                        setElementType("Link");
                        setElementName(linkElement.getName());
                        setDimension("");
                    }
                }
                else {
                    setElementName("");
                    setDimension("");
                }
            } else{
                setElementName("");
                setDimension("");
            }
        }
    }
}
