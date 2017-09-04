package elements;

import painters.RectanglePainter;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class RectangleElement extends DiagramDevice {

    public RectangleElement(Point2D position, Dimension size, Stroke stroke,
                            Paint paint, Color strokeColor) {
        super(position, size, stroke, paint, strokeColor, 3, 1);
        elementPainter = new RectanglePainter(this);
    }


    public RectangleElement(RectangleElement rectangle){
        super(rectangle);
        setName(rectangle.getName() + "-");
    }

    public static DiagramDevice createDefault(Point2D pos, int elemNo){
        Point2D position = (Point2D) pos.clone();

        Paint fill = Color.WHITE;
        RectangleElement rectangleElement=new RectangleElement(position,
                new Dimension(64,32),
                new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL),
                fill,
                Color.BLACK);
        rectangleElement.setName("Rectangle_" + elemNo);
        return rectangleElement;
    }

    @Override
    public DiagramElement clone() {
        return new RectangleElement(this);
    }
}
