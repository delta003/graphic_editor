package commands;

import elements.DiagramDevice;
import elements.DiagramElement;
import view.DiagramView;

import java.util.ArrayList;

/**
 * Created by Marko on 06-Jun-15.
 */
public class RotateCommand implements AbstractCommand {
    public enum Direction { LEFT, RIGHT }

    private DiagramView diagramView;
    private ArrayList<DiagramElement> rotatedElements;
    private Direction direction;

    public RotateCommand(DiagramView diagramView , Direction direction) {
        this.direction = direction;
        this.diagramView = diagramView;
        this.rotatedElements = ((ArrayList<DiagramElement>)diagramView.getDiagram().getSelectionModel().getSelected().clone());
    }

    @Override
    public void doCommand() {
        DiagramDevice device;
        for(DiagramElement element : rotatedElements){
            if(element instanceof DiagramDevice){
                device = (DiagramDevice) element;
                if(direction == Direction.RIGHT)
                    device.setRotation(device.getRotation() + Math.PI/2);
                else
                    device.setRotation(device.getRotation() - Math.PI/2);
            }
            diagramView.getDiagram().getDiagramModel().fireUpdatePerformed(element.getRepaintBounds());
        }
    }

    @Override
    public void undoCommand() {
        DiagramDevice device;
        for(DiagramElement element : rotatedElements){
            if(element instanceof DiagramDevice){
                device = (DiagramDevice) element;
                if(direction == Direction.RIGHT)
                    device.setRotation(device.getRotation() - Math.PI/2);
                else
                    device.setRotation(device.getRotation() + Math.PI/2);
            }
            diagramView.getDiagram().getDiagramModel().fireUpdatePerformed(element.getRepaintBounds());
        }
    }
}
