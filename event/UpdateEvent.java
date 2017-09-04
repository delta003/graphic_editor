package event;

import java.awt.*;
import java.util.EventObject;

/**
 * Created by Marko on 24-May-15.
 */
public class UpdateEvent extends EventObject {
    private Rectangle r;

    public UpdateEvent(Object arg0) {
        super(arg0);
    }

    public UpdateEvent(Object source, Rectangle r) {
        super(source);
        this.r = r;
    }

    public Rectangle getR() {
        return r;
    }
}
