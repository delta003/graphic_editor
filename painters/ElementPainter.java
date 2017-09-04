package painters;

import elements.DiagramElement;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Marko on 24-May-15.
 */
public abstract class ElementPainter implements Serializable {

    protected Shape shape;

    public ElementPainter(DiagramElement element) {	}

    public Shape getShape() {
        return shape;
    }

    public abstract void paint(Graphics2D g, DiagramElement element);

    public abstract boolean elementAt(DiagramElement element, Point pos);
}
