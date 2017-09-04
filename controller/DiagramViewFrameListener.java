package controller;

import engine.Engine;
import model.DeviceType;
import model.Diagram;
import state.DeviceState;
import state.LinkState;
import state.SelectState;
import view.DiagramView;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.tree.TreePath;

/**
 * Created by Marko on 25-May-15.
 */
public class DiagramViewFrameListener extends InternalFrameAdapter {

    @Override
    public void internalFrameActivated(InternalFrameEvent arg0) {
        Diagram diagram = ((DiagramView)arg0.getInternalFrame()).getDiagram();
        if (diagram != null) {
            Engine.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(diagram.getPath()));
        }
        Engine.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(diagram.getPath()));

        if (diagram.getCurrentState() instanceof DeviceState){
            if(diagram.getDeviceType() == DeviceType.RECTANGLE)
                Engine.getInstance().getPalette().getRectangle().setSelected(true);
            if(diagram.getDeviceType() == DeviceType.TRIANGLE)
                Engine.getInstance().getPalette().getTriangle().setSelected(true);
            if(diagram.getDeviceType() == DeviceType.CIRCLE)
                Engine.getInstance().getPalette().getCircle().setSelected(true);
            if(diagram.getDeviceType() == DeviceType.STAR)
                Engine.getInstance().getPalette().getStar().setSelected(true);
        } else if(diagram.getCurrentState() instanceof LinkState){
            Engine.getInstance().getPalette().getLink().setSelected(true);
        } else if(diagram.getCurrentState() instanceof SelectState){
            Engine.getInstance().getPalette().getHandCursor().setSelected(true);
        }
        Engine.getInstance().getStatusBar().update();
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent arg0) {
        DiagramView.setOpenFrameCount(DiagramView.getOpenFrameCount() - 1);
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent arg0) {
        Diagram diagram = ((DiagramView)arg0.getInternalFrame()).getDiagram();
        diagram.setCurrentState(((DiagramView)arg0.getInternalFrame()).getStateManager().getCurrentState());
        diagram.setDeviceType(((DiagramView) arg0.getInternalFrame()).getStateManager().getDeviceState().getDeviceType());
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent arg0) {
        super.internalFrameClosing(arg0);
        Engine.getInstance().getWorkspaceTree().setSelectionPath(null);
        ((DiagramView)arg0.getInternalFrame()).hideView();
    }
}
