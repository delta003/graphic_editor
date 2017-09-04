package gui;

import engine.Engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 24-May-15.
 */
public class Palette extends JToolBar {
    private JToggleButton handCursor, rectangle, circle, star, triangle, link;
    private ButtonGroup group;

    public Palette() {
        super(JToolBar.VERTICAL);
        setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        group = new ButtonGroup();

        handCursor = new JToggleButton(Engine.getInstance().getActionManager().getpHandCursorAction());
        group.add(handCursor);
        add(handCursor);

        addSeparator(new Dimension(0, 10));

        rectangle = new JToggleButton(Engine.getInstance().getActionManager().getpRectangleAction());
        group.add(rectangle);
        add(rectangle);
        addSeparator(new Dimension(0, 5));

        circle = new JToggleButton(Engine.getInstance().getActionManager().getpCircleAction());
        group.add(circle);
        add(circle);
        addSeparator(new Dimension(0, 5));

        triangle = new JToggleButton(Engine.getInstance().getActionManager().getpTriangleAction());
        group.add(triangle);
        add(triangle);
        addSeparator(new Dimension(0, 5));

        star = new JToggleButton(Engine.getInstance().getActionManager().getpStarAction());
        group.add(star);
        add(star);

        addSeparator(new Dimension(0, 10));

        link = new JToggleButton(Engine.getInstance().getActionManager().getpLinkAction());
        group.add(link);
        add(link);
    }

    public JToggleButton getHandCursor() {
        return handCursor;
    }

    public JToggleButton getRectangle() {
        return rectangle;
    }

    public JToggleButton getCircle() {
        return circle;
    }

    public JToggleButton getStar() {
        return star;
    }

    public JToggleButton getLink() {
        return link;
    }

    public JToggleButton getTriangle() {
        return triangle;
    }
}
