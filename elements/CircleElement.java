package elements;

import painters.CirclePainter;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class CircleElement extends DiagramDevice {

    public CircleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color strokeColor) {
        super(position, size, stroke, paint, strokeColor, 3, 1);
        elementPainter = new CirclePainter(this);
    }

    public static DiagramDevice createDefault(Point2D pos, int elemNo) {
        Point2D position = (Point2D) pos.clone();
        Paint fill = Color.WHITE;
        CircleElement circleElement = new CircleElement(position, new Dimension(40, 40),
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_BEVEL), fill, Color.BLACK);
        circleElement.setName("Circle_" + elemNo);
        return circleElement;
    }

    public CircleElement(CircleElement circle){
        super(circle);
        setName(circle.getName() + "-");
    }

    @Override
    public DiagramElement clone() {
        return new CircleElement(this);
    }

}
