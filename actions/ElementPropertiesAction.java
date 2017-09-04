package actions;

import elements.DiagramDevice;
import elements.DiagramElement;
import elements.LinkElement;
import engine.Engine;
import gui.Properties;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 25-May-15.
 */
public class ElementPropertiesAction extends AbstractAction {
    public ElementPropertiesAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("properties"));
        putValue(NAME, "Properties");
        putValue(SHORT_DESCRIPTION, "Element properties");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        DiagramElement element = view.getDiagram().getSelectionModel().getElementFromSelectionListAt(0);
        Properties ed = new Properties(element);
        ed.setModal(true);
        ed.setVisible(true);
        if(!ed.isOk()) return;
        if(!ed.getNewName().equals(element.getName()))
            if(view.getDiagram().getDiagramModel().isAvelableName(ed.getNewName()))
                element.setName(ed.getNewName());
            else
                JOptionPane.showMessageDialog(null,"Name exists, not changed");

        element.setDescription(ed.getDescription());
        if(element instanceof DiagramDevice){
            element.setFillColor(ed.getColor());
            if(((DiagramDevice) element).getInputNo() != ed.getInputNo()){
                view.getDiagram().getDiagramModel().deleteLinkForDevice((DiagramDevice)element);
                ((DiagramDevice) element).setInputNo(ed.getInputNo());
            }
        }
        else if(element instanceof LinkElement)
            if(ed.getColor() != Color.WHITE) element.setStrokeColor(ed.getColor());
        view.getDiagram().getDiagramModel().fireUpdatePerformed(element.getRepaintBounds());
        SwingUtilities.updateComponentTreeUI(Engine.getInstance().getWorkspaceTree());
    }
}
