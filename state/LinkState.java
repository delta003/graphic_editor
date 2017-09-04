package state;

import commands.AddLinkCommand;
import elements.DiagramDevice;
import elements.DiagramElement;
import elements.InputOutputElement;
import elements.LinkElement;
import engine.Engine;
import model.DeviceType;
import model.Diagram;
import model.DiagramModel;
import model.ElementFolder;
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
public class LinkState extends State {
    private DiagramView diagramView;
    private DiagramElement link;
    private boolean firstPoints;

    public LinkState(DiagramView diagramView) {
        this.diagramView = diagramView;
        thread = new AutoScrollThread(diagramView);
        thread.start();
    }

    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        diagramView.transformToUserSpace(position);
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (link == null) {
                int devicePos = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
                if (devicePos != -1) {
                    DiagramElement diagramElement = diagramView.getDiagram().getDiagramModel().getElementAt(devicePos);
                    if(diagramElement instanceof LinkElement) return;
                    DiagramDevice startDevice = (DiagramDevice) diagramElement;
                    if (startDevice.hasFreeOutput()) {
                        InputOutputElement ouput = startDevice.getFreeOutput();
                        link = LinkElement.createDefault(startDevice, ouput, diagramView.getDiagram().getDiagramModel().getElementCount());
                        diagramView.getDiagram().getDiagramModel().addDiagramElement((LinkElement) link);
                        firstPoints = false;
                    }
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (link != null) {
                diagramView.getDiagram().getDiagramModel().removeDiagramElement(link);
                link = null;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        Point position = e.getPoint();
        diagramView.transformToUserSpace(position);
        if (link == null) return;
        if (e.getButton() == MouseEvent.BUTTON1) {
            int devicePos = diagramView.getDiagram().getDiagramModel().getElementAtPosition(position);
            if (devicePos != -1) {
                DiagramElement diagramElement = diagramView.getDiagram().getDiagramModel().getElementAt(devicePos);
                if(diagramElement instanceof LinkElement) return;
                DiagramDevice endDevice = (DiagramDevice) diagramView.getDiagram().getDiagramModel().getElementAt(devicePos);
                if (endDevice.hasFreeInput() && endDevice != ((LinkElement) link).getStartDevice()) {
                    InputOutputElement input = endDevice.getFreeInput();
                    ((LinkElement) link).setInput(input);
                    ((LinkElement) link).setEndDevice(endDevice);
                    ((LinkElement) link).removeLastPoint();
                    ((LinkElement) link).setPainter(link);
                    diagramView.getDiagram().getDiagramModel().fireUpdatePerformed(link.getRepaintBounds());
                    diagramView.getCommandManager().addCommand(
                            new AddLinkCommand(diagramView.getDiagram(), (LinkElement) link));
                    link = null;
                } else {
                    diagramView.getDiagram().getDiagramModel().removeDiagramElement(link);
                    link = null;
                }
            } else {
                ((LinkElement) link).addPoint(position);
                firstPoints = true;
            }
        }
    }

    private void linkDrawing(MouseEvent e) {
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
        Point mousePoint = e.getPoint();
        diagramView.transformToUserSpace(mousePoint);
        int devicePos = diagramView.getDiagram().getDiagramModel().getElementAtPosition(mousePoint);
        if (devicePos != -1) {
            DiagramElement diagramElement = diagramView.getDiagram().getDiagramModel().getElementAt(devicePos);
            if(diagramElement instanceof DiagramDevice){
                DiagramDevice endDevice = (DiagramDevice) diagramView.getDiagram().getDiagramModel().getElementAt(devicePos);
                if (endDevice.hasFreeInput() && endDevice != ((LinkElement) link).getStartDevice()) {
                    InputOutputElement input = endDevice.getFreeInput();
                    mousePoint.setLocation(input.getPosition().getX()-5, input.getPosition().getY());
                    ((LinkElement) link).solveMinMaxCurr(mousePoint);
                }
            }
        }
        Point2D newPoint;
        if(!firstPoints){
            Point2D thirdLast = ((LinkElement) link).getThirdLastPoint();
            newPoint = newPoint(thirdLast, mousePoint );
        }else{
            Point2D fourthLast = ((LinkElement) link).getPointAt(((LinkElement)link).getPointsSize()-4);
            Point2D thirdLast = ((LinkElement) link).getThirdLastPoint();
            newPoint = newPointAgain(fourthLast, thirdLast, mousePoint);
        }
        ((LinkElement) link).getSecondLastPoint().setLocation(newPoint);
        ((LinkElement) link).solveMinMaxCurr(newPoint);
        ((LinkElement) link).getLastPoint().setLocation(mousePoint);
        ((LinkElement) link).solveMinMaxCurr(mousePoint);
        ((LinkElement) link).setPainter(link);
        diagramView.getDiagram().getDiagramModel().fireUpdatePerformed(link.getRepaintBounds());
    }

    public void mouseDragged(MouseEvent e) {
        if (link!=null) linkDrawing(e);
    }

    public void mouseMoved(MouseEvent e) {
        if (link!=null) linkDrawing(e);
    }

    private Point2D newPoint(Point2D secondLast, Point2D last){
        int newX;
        int newY;
        int width = (int) Math.abs(secondLast.getX() - last.getX());
        int height = (int) Math.abs(secondLast.getY() - last.getY());
        if(width < height){
            newY = (int) secondLast.getY();
            newX = (int) last.getX();
        }else{
            newY = (int) last.getY();
            newX = (int) secondLast.getX();
        }
        return new Point(newX, newY);
    }

    private Point2D newPointAgain(Point2D fourthLast, Point2D thirdLast, Point2D last) {
        int newX;
        int newY;
        boolean horizontal = fourthLast.getX() != thirdLast.getX();
        if(horizontal){
            newX = (int) thirdLast.getX();
            newY = (int) last.getY();
        }else{
            newY = (int) thirdLast.getY();
            newX = (int) last.getX();
        }
        return new Point(newX, newY);
    }

    @Override
    public void exitState(){
        if (link!=null) {
            diagramView.getDiagram().getDiagramModel().removeDiagramElement(link);
            link = null;
        }
    }
}
