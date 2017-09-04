package state;

import utils.AutoScrollThread;

import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by Marko on 24-May-15.
 */
public class State implements Serializable {

    protected AutoScrollThread thread;

    public void mousePressed(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void exitState() {}

    public AutoScrollThread getThread() {
        return thread;
    }
}
