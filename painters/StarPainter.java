package painters;

import elements.DiagramElement;
import elements.StarElement;

import java.awt.geom.GeneralPath;

/**
 * Created by Marko on 24-May-15.
 */
public class StarPainter extends DevicePainter {

    public StarPainter(DiagramElement device) {
        super(device);
        StarElement star = (StarElement) device;
        double x = star.getSize().getWidth() / 2;
        double y = star.getSize().getHeight() / 2;
        double dx = star.getSize().getWidth() / 6;
        double dy = star.getSize().getHeight() / 4;
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(x, y - 2 * dy);
        ((GeneralPath) shape).lineTo(x + dx, y - dy);
        ((GeneralPath) shape).lineTo(x + 3 * dx, y - dy);
        ((GeneralPath) shape).lineTo(x + 2 * dx, y);
        ((GeneralPath) shape).lineTo(x + 3 * dx, y + dy);
        ((GeneralPath) shape).lineTo(x + dx, y + dy);
        ((GeneralPath) shape).lineTo(x, y + 2 * dy);
        ((GeneralPath) shape).lineTo(x - dx, y + dy);
        ((GeneralPath) shape).lineTo(x - 3 * dx, y + dy);
        ((GeneralPath) shape).lineTo(x - 2 * dx, y);
        ((GeneralPath) shape).lineTo(x - 3 * dx, y - dy);
        ((GeneralPath) shape).lineTo(x - dx, y - dy);
        ((GeneralPath) shape).lineTo(x, y - 2 * dy);
        ((GeneralPath) shape).closePath();
    }
}
