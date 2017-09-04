package painters;

import elements.DiagramElement;
import elements.TriangleElement;

import java.awt.geom.GeneralPath;

/**
 * Created by Marko on 24-May-15.
 */
public class TrianglePainter extends DevicePainter{

    public TrianglePainter(DiagramElement device) {
        super(device);
        TriangleElement triangle = (TriangleElement) device;
        double x = triangle.getSize().getWidth() / 2;
        double y = triangle.getSize().getHeight() / 2;
        double dx = triangle.getSize().getWidth() / 2;
        double dy = triangle.getSize().getHeight() / 2;
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(x, y - dy);
        ((GeneralPath) shape).lineTo(x - dx, y + dy);
        ((GeneralPath) shape).lineTo(x + dx, y + dy);
        ((GeneralPath) shape).lineTo(x, y - dy);
        ((GeneralPath) shape).closePath();
    }
}
