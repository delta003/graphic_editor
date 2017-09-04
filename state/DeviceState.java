package state;

import commands.AddDeviceCommand;
import elements.*;
import engine.Engine;
import model.*;
import utils.AutoScrollThread;
import utils.Direction;
import view.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class DeviceState extends State {
    private DiagramView diagramView;
    private DeviceType deviceType;

    public DeviceState(DiagramView diagramView) {
        super();
        this.diagramView = diagramView;
        thread = new AutoScrollThread(diagramView);
        thread.start();
    }

    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        diagramView.transformToUserSpace(position);
        if (e.getButton() == MouseEvent.BUTTON1){
            if (diagramView.getDiagram().getDiagramModel().getElementAtPosition(position) == -1) {
                diagramView.getCommandManager().addCommand(
                        new AddDeviceCommand(diagramView.getDiagram(), position, deviceType));
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getPoint().getX() <= 10) {
            thread.setScroll(false);
            thread.setDirection(Direction.Left);
            thread.setScroll(true);
        } else if (e.getPoint().getY() >= diagramView.getFramework().getSize().getHeight() - 10) {
            thread.setScroll(false);
            thread.setDirection(Direction.Up);
            thread.setScroll(true);
        } else if (e.getPoint().getX() >= diagramView.getFramework().getSize().getWidth() - 10) {
            thread.setScroll(false);
            thread.setDirection(Direction.Right);
            thread.setScroll(true);
        } else if (e.getPoint().getY() <= 10) {
            thread.setScroll(false);
            thread.setDirection(Direction.Down);
            thread.setScroll(true);
        } else {
            thread.setScroll(false);
        }
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
