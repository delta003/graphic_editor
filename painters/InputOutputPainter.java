package painters;

import elements.DiagramElement;
import elements.InputOutputElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * Created by Marko on 24-May-15.
 */
public class InputOutputPainter extends DevicePainter{

    public InputOutputPainter(InputOutputElement io) {
        super(io);
        shape = new GeneralPath();
        if (io.getType() == InputOutputElement.INPUT){
            ((GeneralPath)shape).moveTo(0,0);
            ((GeneralPath)shape).lineTo(0-5,0);
        }else if (io.getType() == InputOutputElement.OUTPUT){
            ((GeneralPath)shape).moveTo(0,0);
            ((GeneralPath)shape).lineTo(0+5,0);
        }
    }

    public void paint(Graphics2D g, DiagramElement element){
        AffineTransform oldTranform = g.getTransform();
        InputOutputElement io=(InputOutputElement) element;
        g.translate(io.getPosition().getX(), io.getPosition().getY());
        g.rotate(io.getDevice().getRotation());
        g.scale(io.getDevice().getScale(), io.getDevice().getScale());
        g.setPaint(element.getStrokeColor());
        g.setStroke(element.getStroke());
        g.draw(getShape());

        g.setPaint(element.getPaint());
        g.fill(getShape());

        g.setTransform(oldTranform);
    }
}
