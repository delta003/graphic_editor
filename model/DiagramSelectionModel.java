package model;

import elements.DiagramDevice;
import elements.DiagramElement;
import elements.LinkElement;
import engine.Engine;
import event.UpdateEvent;
import event.UpdateListener;
import painters.LinkPainter;
import view.DiagramView;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 24-May-15.
 */
public class DiagramSelectionModel extends DefaultSingleSelectionModel {
    private ArrayList<DiagramElement> selectionList = new ArrayList<DiagramElement>();

    transient EventListenerList listenerList = new EventListenerList();
    UpdateEvent updateEvent = null;

    private Object readResolve() {
        listenerList = new EventListenerList();
        return this;
    }

    public void addToSelectionList(DiagramElement element) {
        selectionList.add(element);
        fireUpdatePerformed(element.getRepaintBounds());
        Engine.getInstance().getStatusBar().update();
    }

    public void addToSelectionList(ArrayList<DiagramElement> list) {
        selectionList.addAll(list);
        for(DiagramElement element : selectionList){
            fireUpdatePerformed(element.getRepaintBounds());
        }
        Engine.getInstance().getStatusBar().update();
}

    public int getSelectionListSize() {
        return selectionList.size();
    }

    public DiagramElement getElementFromSelectionListAt(int index) {
        return (DiagramElement)selectionList.get(index);
    }

    public int getIndexByObject(DiagramElement element) {
        return selectionList.indexOf(element);
    }

    public ArrayList<DiagramElement> getSelected() {
        ArrayList<DiagramElement> selected=new ArrayList<DiagramElement>();
        selected.addAll(selectionList);
        return selected;
    }

    public void removeFromSelectionList(DiagramElement element) {
        selectionList.remove(element);
        fireUpdatePerformed(element.getRepaintBounds());
        Engine.getInstance().getStatusBar().update();
    }

    public void removeAllFromSelectionList() {
        for(DiagramElement element : selectionList){
            fireUpdatePerformed(element.getRepaintBounds());
        }
        selectionList.clear();
        Engine.getInstance().getStatusBar().update();
    }

    public ArrayList<DiagramElement>  getSelectionList() {
        return selectionList;
    }

    public Iterator<DiagramElement> getSelectionListIterator(){
        return selectionList.iterator();
    }

    public boolean isElementSelected(DiagramElement element){
        return selectionList.contains(element);

    }

    public void selectElements(Rectangle2D rec,
                               ArrayList<DiagramElement> elements) {
        Iterator<DiagramElement> it = elements.iterator();
        while (it.hasNext()) {
            DiagramElement element = it.next();
            if (element instanceof DiagramDevice) {
                DiagramDevice device = (DiagramDevice) element;
                if (rec.intersects(device.getPosition().getX(), device.getPosition().getY(),
                        device.getSize().getWidth(), device.getSize().getHeight())) {
                    if (!isElementSelected(device))
                        selectionList.add(device);
                }
            } else {
                LinkElement link = (LinkElement) element;
                if (rec.contains(LinkPainter.findRectangle(link)) && !isElementSelected(link)) selectionList.add(link);
            }
        }
        Engine.getInstance().getStatusBar().update();
    }

    public void addUpdateListener(UpdateListener l) {
        listenerList.add(UpdateListener.class, l);
    }

    public void removeUpdateListener(UpdateListener l) {
        listenerList.remove(UpdateListener.class, l);
    }

    public void fireUpdatePerformed(Rectangle r) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == UpdateListener.class) {
                updateEvent = new UpdateEvent(this, r);
                ((UpdateListener) listeners[i + 1])
                        .updatePerformed(updateEvent);
            }
        }
    }

    public void updateJTree(){
        DiagramView dv = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        TreePath[] paths = new TreePath[dv.getDiagram().getSelectionModel().getSelectionListSize()];
        for (int i = 0; i < dv.getDiagram().getSelectionModel().getSelectionListSize(); i++) {
            paths[i] = dv.getPath(dv.getDiagram().getSelectionModel().getSelectionList().get(i));
        }
        Engine.getInstance().getWorkspaceTree().clearSelection();
        Engine.getInstance().getWorkspaceTree().addSelectionPaths(paths);
        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
    }

    public boolean checkCutCopy(){
        for(DiagramElement element : selectionList){
            if(element instanceof LinkElement){
                LinkElement link = (LinkElement) element;
                if(!selectionList.contains(link.getStartDevice()) || !selectionList.contains(link.getEndDevice()))
                    return false;
            }
        }
        return true;
    }

    public boolean hasElement(DiagramElement dd1) {
        if (selectionList.contains(dd1)) return true;
        for(DiagramElement element : selectionList){
            if(element.getName().equals(dd1.getName())) return true;
        }
        return false;
    }
}
