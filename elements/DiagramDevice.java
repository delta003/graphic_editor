package elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Marko on 24-May-15.
 */
public abstract class DiagramDevice extends DiagramElement {
    protected Dimension size;
    protected Point2D position;
    protected int inputNo;
    protected int outputNo;
    protected boolean fliped;
    protected double scale;
    protected double rotation;
    protected ArrayList<InputOutputElement> inputs = new ArrayList<>();
    protected ArrayList<InputOutputElement> outputs = new ArrayList<>();

    public DiagramDevice(Point2D position, Dimension size, Stroke stroke,
                         Paint paint, Color strokeColor, int inputNo, int outputNo) {
        super(stroke, paint, strokeColor);
        this.size = size;
        position.setLocation(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2);
        this.position = position;
        this.inputNo = inputNo;
        this.outputNo = outputNo;
        this.strokeColor = strokeColor;
        this.scale = 1;
        this.rotation = 0;
        this.fliped = false;

        for (int i = 0; i < inputNo; i++) {
            Point2D ioPos = new Point2D.Double(0, 0
                    + (getSize().getHeight() / (getInputNo() + 1)) * (i + 1));
            addInput((InputOutputElement) InputOutputElement.createDefault(ioPos, stroke, paint, this, i + 1,
                    InputOutputElement.INPUT));
        }

        for (int i = 0; i < outputNo; i++) {
            Point2D ioPos = new Point2D.Double(size.width, +(getSize()
                    .getHeight() / (getOutputNo() + 1)) * (i + 1));
            addOutput((InputOutputElement) InputOutputElement.createDefault(ioPos, stroke, paint, this, i + 1,
                    InputOutputElement.OUTPUT));
        }
    }

    public DiagramDevice(DiagramDevice device) {
        super(device);
        this.size = device.size;
        this.position = device.position;

        this.inputNo = device.inputNo;
        this.outputNo = device.outputNo;

        this.scale = device.scale;
        this.rotation = device.rotation;

        for (int i = 0; i < device.inputs.size(); i++)
            this.inputs.add(new InputOutputElement(device.inputs.get(i), this));
        for (int i = 0; i < device.outputs.size(); i++)
            this.outputs.add(new InputOutputElement(device.outputs.get(i), this));
    }

    public void addInput(InputOutputElement p) {
        inputs.add(p);
    }

    public void removeInput(InputOutputElement p) {
        inputs.remove(p);
    }

    public InputOutputElement getInputAt(int i) {
        return inputs.get(i);
    }

    public int getInputCount() {
        return inputs.size();
    }

    public int getOutputCount() {
        return outputs.size();
    }

    public Iterator<InputOutputElement> getInputIterator() {
        return inputs.iterator();
    }

    public void addOutput(InputOutputElement p) {
        outputs.add(p);
    }

    public void removeOutput(InputOutputElement p) {
        outputs.remove(p);
    }

    public InputOutputElement getOutputAt(int i) {
        return outputs.get(i);
    }

    public int getIOutputCount() {
        return outputs.size();
    }

    public Iterator<InputOutputElement> getOutputIterator() {
        return outputs.iterator();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Dimension getInitSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getInputNo() {
        return inputNo;
    }

    public ArrayList<InputOutputElement> getInputs() {
        return inputs;
    }

    public ArrayList<InputOutputElement> getOutputs() {
        return outputs;
    }

    public int getOutputNo() {
        return outputNo;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getRotation() {
        return rotation;
    }

    public boolean isFliped() {
        return fliped;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
        if(Math.abs(rotation) == 2 * Math.PI)
            this.rotation = 0;
        fliped = !fliped;
    }

    public Dimension getSize() {
        Dimension tempSize = new Dimension();

        double width = size.getWidth() * getScale();
        double height = size.getHeight() * getScale();
        tempSize.setSize(width, height);
        return tempSize;
    }

    public boolean hasFreeInput() {
        for (InputOutputElement input : inputs) {
            if (input.isFree())
                return true;
        }
        return false;
    }

    public boolean hasFreeOutput() {
        for (InputOutputElement output : outputs) {
            if (output.isFree())
                return true;
        }
        return false;
    }

    public int inM(InputOutputElement e){
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).equals(e))
                return i;
        }
        return -1;
    }

    public int outM(InputOutputElement e){
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i).equals(e))
                return i;
        }
        return -1;
    }

    public InputOutputElement getFreeInput() {
        for (InputOutputElement input : inputs) {
            if (input.isFree())
                return input;
        }
        return null;
    }

    public InputOutputElement getFreeOutput() {
        for (InputOutputElement output : outputs) {
            if (output.isFree())
                return output;
        }
        return null;
    }

    public void setInputNo(int inputNoo) {
        this.inputNo = inputNoo;
        inputs.clear();
        for (int i = 0; i < inputNoo; i++) {
            Point2D ioPos = new Point2D.Double(0, 0
                    + (getSize().getHeight() / (inputNoo + 1)) * (i + 1));
            addInput((InputOutputElement) InputOutputElement.createDefault(ioPos, stroke, paint, this, i + 1,
                            InputOutputElement.INPUT));

        }
    }

    public void setOutputNo(int outputNoo) {
        outputs.clear();

        for (int i = 0; i < outputNoo; i++) {
            Point2D ioPos = new Point2D.Double(getSize().getWidth(), 0
                    + (getSize().getHeight() / (outputNoo + 1)) * (i + 1));
            addOutput((InputOutputElement) InputOutputElement.createDefault(ioPos, stroke, paint, this, i + 1,
                    InputOutputElement.OUTPUT));
        }

    }

    @Override
    public Rectangle getRepaintBounds(){
        int x = (int)position.getX()-20;
        int y = (int)position.getY()-20;
        if (x<0) x=0;
        if (y<0) y=0;
        return new Rectangle(x,y, (int)size.width+40, (int)size.height+40);
    }
}
