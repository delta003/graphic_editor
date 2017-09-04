package painters;

import elements.DiagramElement;
import elements.RectangleElement;

import java.awt.geom.GeneralPath;

/**
 * Created by Marko on 24-May-15.
 */
public class RectanglePainter extends DevicePainter{

    public RectanglePainter(DiagramElement device) {
        super(device);
        RectangleElement rectangle = (RectangleElement) device;
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(0,0);
        ((GeneralPath)shape).lineTo(0+rectangle.getSize().width,0);
        ((GeneralPath)shape).lineTo(0+rectangle.getSize().width,0+rectangle.getSize().height);
        ((GeneralPath)shape).lineTo(0,0+rectangle.getSize().height);
        ((GeneralPath)shape).closePath();
    }
}
