package state;

import commands.ResizeCommand;
import elements.DiagramDevice;
import elements.DiagramElement;
import view.DiagramView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 06-Jun-15.
 */
public class ResizeState extends State {
    private DiagramView.Handle handleInMotion=null;
    private DiagramDevice elementInMotion=null;
    private double scale;
    private DiagramView med;
    private ArrayList<Point2D> newPositions = new ArrayList<>();
    private ArrayList<Point2D> oldPositions = new ArrayList<>();
    private ArrayList<Double> newScale = new ArrayList<>();
    private ArrayList<Double> oldScale = new ArrayList<>();

    public ResizeState(DiagramView md) {
        med = md;
    }

    public void mouseDragged(MouseEvent e) {
        Point2D position = e.getPoint();
        med.transformToUserSpace(position);
        if (handleInMotion==null){
            handleInMotion = med.getDeviceAndHandleForPoint(position);
            elementInMotion = med.getDeviceForHandlePoint(position);
        }
        if (handleInMotion!=null){
            int pom = handleInMotion.ordinal();

            DiagramDevice device = elementInMotion;
            switch(pom){
                //southeast
                case 0:{
                    double razlikaX=position.getX()-(device.getPosition().getX()+device.getSize().getWidth());
                    double razlikaY=position.getY()-(device.getPosition().getY()+device.getSize().getHeight());
                    double newWidth = device.getSize().getWidth()+razlikaX;
                    double newHeight = device.getSize().getHeight()+razlikaY;
                    double scaleX=newWidth/device.getInitSize().getWidth();
                    double scaleY=newHeight/device.getInitSize().getHeight();
                    double newScale = 1;
                    if(scaleX<scaleY)
                        newScale=scaleX;
                    else
                        newScale=scaleY;
                    this.scale = newScale - device.getScale();
                    if(newScale<0.2)
                        device.setScale(0.2);
                    else if(newScale>3)
                        device.setScale(3);
                    else
                        device.setScale(newScale);
                    break;
                }
                //southwest
                case 1:{
                    double razlikaX=position.getX()-(device.getPosition().getX());
                    double razlikaY=position.getY()-(device.getPosition().getY()+device.getSize().getHeight());
                    double newWidth = device.getSize().getWidth()-razlikaX;
                    double newHeight = device.getSize().getHeight()+razlikaY;
                    double scaleX=newWidth/device.getInitSize().getWidth();
                    double scaleY=newHeight/device.getInitSize().getHeight();
                    double newScale;
                    if(scaleX<scaleY)
                        newScale=scaleX;
                    else
                        newScale=scaleY;

                    double tempWidth=device.getSize().getWidth();
                    this.scale = newScale - device.getScale();

                    if(newScale<0.2)
                        device.setScale(0.2);
                    else if(newScale>3)
                        device.setScale(3);
                    else
                        device.setScale(newScale);
                    ((Point) device.getPosition()).translate((int)(tempWidth-device.getSize().getWidth()),0);
                    break;
                }
                //northeast
                case 2:{
                    double razlikaX=position.getX()-(device.getPosition().getX()+device.getSize().getWidth());
                    double razlikaY=position.getY()-(device.getPosition().getY());
                    double newWidth = device.getSize().getWidth()+razlikaX;
                    double newHeight = device.getSize().getHeight()-razlikaY;
                    double scaleX=newWidth/device.getInitSize().getWidth();
                    double scaleY=newHeight/device.getInitSize().getHeight();
                    double newScale;
                    if(scaleX<scaleY)
                        newScale=scaleX;
                    else
                        newScale=scaleY;

                    double tempHeight=device.getSize().getHeight();
                    this.scale = newScale - device.getScale();

                    if(newScale<0.2)
                        device.setScale(0.2);
                    else if(newScale>3)
                        device.setScale(3);
                    else
                        device.setScale(newScale);
                    ((Point) device.getPosition()).translate(0, (int)(tempHeight-device.getSize().getHeight()));
                    break;
                }
                //northwest
                case 3:{
                    double razlikaX=position.getX()-(device.getPosition().getX());
                    double razlikaY=position.getY()-(device.getPosition().getY());
                    double newWidth = device.getSize().getWidth()-razlikaX;
                    double newHeight = device.getSize().getHeight()-razlikaY;
                    double scaleX=newWidth/device.getInitSize().getWidth();
                    double scaleY=newHeight/device.getInitSize().getHeight();
                    double newScale;
                    if(scaleX<scaleY)
                        newScale=scaleX;
                    else
                        newScale=scaleY;

                    double tempHeight=device.getSize().getHeight();
                    double tempWidth=device.getSize().getWidth();
                    this.scale = newScale - device.getScale();

                    if(newScale<0.2)
                        device.setScale(0.2);
                    else if(newScale>3)
                        device.setScale(3);
                    else
                        device.setScale(newScale);

                    ((Point) device.getPosition()).translate((int)(tempWidth-device.getSize().getWidth()),
                            (int)(tempHeight-device.getSize().getHeight()));
                    break;
                }
            }

            med.updatePerformed(null);

            Iterator<DiagramElement> it = med.getDiagram().getSelectionModel().getSelectionListIterator();
            while(it.hasNext()){

                DiagramElement element =  it.next();
                if (element instanceof DiagramDevice ){
                    device=(DiagramDevice) element;
                    if(device.equals(elementInMotion))continue;
                    switch(pom){
                        //southeast
                        case 0:{
                            double newScale = device.getScale() + this.scale;
                            if(newScale<0.2)
                                device.setScale(0.2);
                            else if(newScale>3)
                                device.setScale(3);
                            else
                                device.setScale(newScale);
                            break;
                        }
                        //southwest
                        case 1:{
                            double newScale = device.getScale() + this.scale;
                            double tempWidth=device.getSize().getWidth();


                            if(newScale<0.2)
                                device.setScale(0.2);
                            else if(newScale>3)
                                device.setScale(3);
                            else
                                device.setScale(newScale);
                            ((Point) device.getPosition()).translate((int)(tempWidth-device.getSize().getWidth()),0);

                            break;
                        }
                        //northeast
                        case 2:{
                            double newScale = device.getScale() + this.scale;

                            double tempHeight=device.getSize().getHeight();


                            if(newScale<0.2)
                                device.setScale(0.2);
                            else if(newScale>3)
                                device.setScale(3);
                            else
                                device.setScale(newScale);
                            ((Point) device.getPosition()).translate(0, (int)(tempHeight-device.getSize().getHeight()));

                            break;
                        }
                        //northwest
                        case 3:{
                            double newScale = device.getScale() + this.scale;
                            double tempHeight=device.getSize().getHeight();
                            double tempWidth=device.getSize().getWidth();

                            if(newScale<0.2)
                                device.setScale(0.2);
                            else if(newScale>3)
                                device.setScale(3);
                            else
                                device.setScale(newScale);
                            ((Point) device.getPosition()).translate((int)(tempWidth-device.getSize().getWidth()),
                                    (int)(tempHeight-device.getSize().getHeight()));
                            break;
                        }
                    }
                }
                med.updatePerformed(null);
            }

        }
    }

    public void mouseReleased(MouseEvent e){
        for(int i = 0 ; i < med.getDiagram().getSelectionModel().getSelectionListSize(); i++){
            if(med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i) instanceof DiagramDevice){
                newPositions.add(((DiagramDevice) med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i)).getPosition());
                newScale.add(((DiagramDevice) med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i)).getScale());
            }
        }
        handleInMotion=null;
        med.getCommandManager().addCommand(
                new ResizeCommand(med.getDiagram().getDiagramModel(), med.getDiagram().getSelectionModel(),
                        oldPositions, newPositions, oldScale, newScale));

        newPositions.clear();
        oldPositions.clear();
        newScale.clear();
        oldScale.clear();
        med.startSelectState();
    }

    public void startState() {
        for(int i = 0 ; i < med.getDiagram().getSelectionModel().getSelectionListSize(); i++){
            if(med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i) instanceof DiagramDevice){
                oldPositions.add((Point2D) (((DiagramDevice)
                        med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i)).getPosition()).clone());
                oldScale.add(((DiagramDevice)
                        med.getDiagram().getSelectionModel().getElementFromSelectionListAt(i)).getScale());
            }
        }
    }
}
