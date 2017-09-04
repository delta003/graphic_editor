package elements;

import painters.InputOutputPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class InputOutputElement extends DiagramElement {

    public static final int INPUT = 0;
    public static final int OUTPUT = 1;

    protected Point2D position;
    protected DiagramDevice device;

    protected int ioNo;
    protected int type;

    boolean free = true;

    public InputOutputElement(Point2D position, Dimension size, Stroke stroke,
                              Paint paint, Color strokeColor, DiagramDevice device, int ioNo,
                              int type) {
        super(stroke, paint, strokeColor);
        this.device = device;
        this.ioNo = ioNo;
        this.type = type;
        this.position = position;
        elementPainter = new InputOutputPainter(this);
    }

    public InputOutputElement(InputOutputElement element, DiagramDevice device) {
        super(element);
        this.device = device;
        this.ioNo = element.ioNo;
        this.type = element.type;
        this.position = element.position;
    }

    public static DiagramElement createDefault(Point2D pos, Stroke stroke,
                                               Paint paint, DiagramDevice device, int ioNo, int type) {
        Point2D position = (Point2D) pos.clone();
        position.setLocation(position.getX(), position.getY());
        InputOutputElement io = new InputOutputElement(position, new Dimension(
                32, 20), stroke, paint, Color.BLACK, device, ioNo, type);
        io.setName("IO" + ioNo);
        return io;
    }

    public Point2D getPosition() {
        AffineTransform trans = new AffineTransform();
        trans.translate(device.getPosition().getX(), device.getPosition()
                .getY());
        trans.rotate(device.getRotation(), device.getSize().getWidth() / 2,
                device.getSize().getHeight() / 2);
        trans.scale(device.getScale(), device.getScale());
        return trans.transform(position, null);
    }

    public int getType() {
        return type;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public DiagramDevice getDevice() {
        return device;
    }

    @Override
    public DiagramElement clone() {
        return new InputOutputElement(this, device);
    }
}
