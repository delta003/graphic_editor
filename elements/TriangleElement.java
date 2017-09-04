package elements;

import painters.TrianglePainter;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class TriangleElement extends DiagramDevice {

    public TriangleElement(Point2D position, Dimension size, Stroke stroke,
                           Paint paint, Color strokeColor) {
        super(position, size, stroke, paint, strokeColor, 3, 1);
        elementPainter = new TrianglePainter(this);
    }

    public TriangleElement(TriangleElement triangle) {
        super(triangle);
        setName(triangle.getName() + "-");
    }

    public static DiagramDevice createDefault(Point2D pos, int elemNo) {
        Point2D position = (Point2D) pos.clone();
        Paint fill = Color.WHITE;
        TriangleElement triangleElement = new TriangleElement(position,
                new Dimension(46, 40), new BasicStroke((float) (2),
                BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL), fill,
                Color.BLACK);
        triangleElement.setName("Triangle_" + elemNo);
        return triangleElement;
    }

    @Override
    public DiagramElement clone() {
        return new TriangleElement(this);
    }
}
