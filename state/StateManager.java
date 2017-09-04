package state;

import engine.Engine;
import model.DeviceType;
import view.DiagramView;

import java.io.Serializable;

/**
 * Created by Marko on 24-May-15.
 */
public class StateManager implements Serializable {

    private State currentState;

    private DeviceState deviceState;
    private LinkState linkState;
    private SelectState selectState;
    private LassoState lassoState;
    private LassoZoomState lassoZoomState;
    private MoveState moveState;
    private ResizeState resizeState;
    private State lastState = new State();

    public StateManager(DiagramView diagramView){
        deviceState = new DeviceState(diagramView);
        linkState = new LinkState(diagramView);
        selectState=new SelectState(diagramView);
        lassoState = new LassoState(diagramView);
        lassoZoomState = new LassoZoomState(diagramView);
        moveState = new MoveState(diagramView);
        resizeState = new ResizeState(diagramView);
        currentState = selectState;
    }

    public void setCircleState() {
        currentState.exitState();
        deviceState.setDeviceType(DeviceType.CIRCLE);
        currentState = deviceState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setLinkState(){
        currentState.exitState();
        currentState = linkState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setRectangleState(){
        currentState.exitState();
        deviceState.setDeviceType(DeviceType.RECTANGLE);
        currentState = deviceState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setTriangleState(){
        currentState.exitState();
        deviceState.setDeviceType(DeviceType.TRIANGLE);
        currentState = deviceState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setStarState(){
        currentState.exitState();
        deviceState.setDeviceType(DeviceType.STAR);
        currentState = deviceState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setSelectState(){
        currentState.exitState();
        currentState = selectState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setLassoState(){
        currentState.exitState();
        currentState = lassoState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setLassoZoomState() {
        lastState = currentState;
        currentState.exitState();
        currentState = lassoZoomState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setMoveState() {
        currentState = moveState;
        Engine.getInstance().getStatusBar().update();
    }

    public void setResizeState() {
        currentState.exitState();
        currentState = resizeState;
        Engine.getInstance().getStatusBar().update();
        resizeState.startState();
        Engine.getInstance().getStatusBar().update();
    }

    public void setLastState() {
        currentState.exitState();
        currentState = lastState;
        Engine.getInstance().getStatusBar().update();
    }

    public State getCurrentState() {
        return currentState;
    }

    public DeviceState getDeviceState() {
        return deviceState;
    }
}
