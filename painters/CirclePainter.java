package painters;

import elements.CircleElement;
import elements.DiagramElement;

import java.awt.geom.Ellipse2D;

/**
 * Created by Marko on 24-May-15.
 */
public class CirclePainter extends DevicePainter{
    public CirclePainter(DiagramElement device) {
        super(device);
        CircleElement circle = (CircleElement) device;
        shape = new Ellipse2D.Double(0, 0, circle.getSize().getHeight(), circle.getSize().getHeight());
    }
}
