package elements;

import painters.StarPainter;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Marko on 24-May-15.
 */
public class StarElement extends DiagramDevice {

    public StarElement(Point2D position, Dimension size, Stroke stroke,
                       Paint paint, Color strokeColor) {
        super(position, size, stroke, paint, strokeColor, 3, 1);
        elementPainter = new StarPainter(this);
    }

    public StarElement(StarElement star){
        super(star);
        setName(star.getName() + "-");
    }

    public static DiagramDevice createDefault(Point2D pos, int elemNo) {
        Point2D position = (Point2D) pos.clone();

        Paint fill = Color.WHITE;
        StarElement starElement = new StarElement(position, new Dimension(50,
                52), new BasicStroke((float) (2), BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_BEVEL), fill, Color.BLACK);
        starElement.setName("Star_" + elemNo);
        return starElement;
    }

    @Override
    public DiagramElement clone() {
        return new StarElement(this);
    }
}
