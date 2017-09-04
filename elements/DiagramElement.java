package elements;

import painters.ElementPainter;
import serialization.SerializableStrokeAdapter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Marko on 24-May-15.
 */
public abstract class DiagramElement extends DefaultMutableTreeNode implements Serializable {
    protected Paint paint;
    protected SerializableStrokeAdapter stroke;
    protected Color strokeColor;

    protected String name;
    protected String description;
    protected Color fillColor;
    protected ElementPainter elementPainter;

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public DiagramElement(DiagramElement element){
        this.stroke = element.stroke;
        this.paint = element.paint;
        this.strokeColor = element.strokeColor;
        this.name = element.name;
        this.description = element.description;
        this.elementPainter = element.elementPainter;
    }

    public DiagramElement(Stroke stroke, Paint paint, Color strokeColor) {
        setStroke(stroke);
        this.paint = paint;
        this.strokeColor = strokeColor;
    }

    abstract public DiagramElement clone();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementPainter getPainter() {
        return elementPainter;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setElementPainter(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
    }

    public ElementPainter getElementPainter() {
        return elementPainter;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = new SerializableStrokeAdapter(stroke);
    }

    public String toString() {
        return name;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    public Rectangle getRepaintBounds(){
        return null;
    }
}
