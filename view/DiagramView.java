package view;

import commands.CommandManager;
import controller.DiagramViewFrameListener;
import controller.DiagramViewMouseListener;
import elements.DiagramDevice;
import elements.DiagramElement;
import elements.LinkElement;
import engine.Engine;
import event.UpdateEvent;
import event.UpdateListener;
import model.Diagram;
import model.Project;
import model.Workspace;
import painters.ElementPainter;
import state.MoveState;
import state.StateManager;
import utils.Direction;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.beans.PropertyVetoException;
import java.util.Iterator;

/**
 * Created by Marko on 01-Apr-15.
 */
public class DiagramView extends JInternalFrame implements Comparable<DiagramView>, UpdateListener, AdjustmentListener {
    private static int openFrameCount = 0;
    public static final int xOffset = 30, yOffset = 30;
    public static final int defaultSizeX = 500, defaultSizeY = 300;
    private static int viewIDCouner = 0;
    private Diagram diagram;
    private JPanel framework;
    private StateManager stateManager = new StateManager(this);
    private Point2D lastPosition = null;
    private Rectangle2D selectionRectangle = new Rectangle2D.Double();

    public static int getOpenFrameCount() {
        return openFrameCount;
    }

    public enum Handle {
        SouthEast, SouthWest, NorthEast, NorthWest
    }

    static final int handleSize = 7;

    private JScrollBar sbVertical;
    private JScrollBar sbHorizontal;

    private int hScrollValue = 140;
    private int vScrollValue = 140;

    private CommandManager commandManager = new CommandManager();

    double translateX = 0;
    double translateY = 0;
    double scaling = 1;
    final static double translateFactor = 10;
    final static double scalingFactor = 1.1;

    private AffineTransform transformation = new AffineTransform();

    private int id;

    public DiagramView() {
        super("",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconable
        setSize(defaultSizeX, defaultSizeY);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addInternalFrameListener(new DiagramViewFrameListener());

        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        setVisible(true);
        ++openFrameCount;

        id = viewIDCouner++;
        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        this.startSelectState();
        Engine.getInstance().getPalette().getHandCursor().setSelected(true);
        setVisible(true);
        framework = new Framework();
        getContentPane().add(framework, BorderLayout.CENTER);

        sbHorizontal = new JScrollBar(JScrollBar.HORIZONTAL, 140, 20, 0, 300);
        sbVertical = new JScrollBar(JScrollBar.VERTICAL, 140, 20, 0, 300);
        sbHorizontal.addAdjustmentListener(this);
        sbVertical.addAdjustmentListener(this);
        this.add(sbHorizontal, BorderLayout.SOUTH);
        this.add(sbVertical, BorderLayout.EAST);

        MouseController mouseController = new MouseController();
        framework.addMouseListener(mouseController);
        framework.addMouseMotionListener(mouseController);
        framework.addMouseWheelListener(mouseController);
        framework.addMouseListener(new DiagramViewMouseListener());
    }

    public void setDiagram(Diagram diagram){
        this.diagram = diagram;
        this.setName(diagram.getLongName());
        this.diagram.getDiagramModel().addUpdateListener(this);
        this.diagram.getSelectionModel().addUpdateListener(this);
        setTitle(diagram.getLongName());
    }

    public void showView() {
        if (this.isVisible()) {
            this.toFront();
            try {
                setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            return;
        }
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        ++openFrameCount;
        setVisible(true);
        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void showView(int x, int y) {
        setLocation(x, y);
        if (this.isVisible()) {
            toFront();
            try {
                setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            return;
        }
        ++openFrameCount;
        setVisible(true);
        try {
            setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void hideView() {
        if (!this.isVisible()) return;
        --openFrameCount;
        setVisible(false);
        setSize(defaultSizeX, defaultSizeY);
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public int gethScrollValue() {
        return hScrollValue;
    }

    public int getvScrollValue() {
        return vScrollValue;
    }

    public JScrollBar getSbHorizontal() {
        return sbHorizontal;
    }

    public JScrollBar getSbVertical() {
        return sbVertical;
    }

    @Override
    public int compareTo(DiagramView o) {
        if (id < o.id) return -1;
        if (id > o.id) return 1;
        return 0;
    }

    public void startCircleState() {
        stateManager.setCircleState();
    }

    public void startSelectState() {
        stateManager.setSelectState();
    }

    public void startLinkState() {
        stateManager.setLinkState();
    }

    public void startRectangleState() {
        stateManager.setRectangleState();
    }

    public void startTriangleState() {
        stateManager.setTriangleState();
    }

    public void startStarState() {
        stateManager.setStarState();
    }

    public void startLassoState() {
        stateManager.setLassoState();
    }

    public void startResizeState(){
        stateManager.setResizeState();
    }

    public void startMoveState(){
        stateManager.setMoveState();
    }

    public void startLassoZoomState(){
        stateManager.setLassoZoomState();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public JPanel getFramework() {
        return framework;
    }

    public static void setOpenFrameCount(int openFrameCount) {
        DiagramView.openFrameCount = openFrameCount;
    }

    private class MouseController extends MouseAdapter implements MouseMotionListener {

        public void mousePressed(MouseEvent e) {
            lastPosition = e.getPoint();
            transformToUserSpace(lastPosition);
            stateManager.getCurrentState().mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) {
            stateManager.getCurrentState().mouseReleased(e);
        }

        public void mouseDragged(MouseEvent e) {
            Engine.getInstance().getStatusBar().setPosition("x : " + e.getPoint().x + " - y : " + e.getPoint().y);
            stateManager.getCurrentState().mouseDragged(e);
        }

        public void mouseMoved(MouseEvent e) {
            Engine.getInstance().getStatusBar().setPosition("x : " + e.getPoint().x + " - y : " + e.getPoint().y);
            stateManager.getCurrentState().mouseMoved(e);
        }

        public void mouseWheelMoved(MouseWheelEvent e) {
            if((e.getModifiers() & MouseWheelEvent.CTRL_MASK) != 0) {
                // Uz ctrl radimo zoom u ta?ki
                double newScaling = scaling;
                if(e.getWheelRotation()>0)
                    newScaling *= (double)e.getWheelRotation()*scalingFactor;
                else
                    newScaling /= -(double)e.getWheelRotation()*scalingFactor;
                if(newScaling < 0.2)
                    newScaling = 0.2;
                if(newScaling > 5)
                    newScaling = 5;

                Point2D oldPosition = e.getPoint();
                transformToUserSpace(oldPosition);

                scaling = newScaling;
                setupTransformation();

                Point2D newPosition = e.getPoint();
                transformToUserSpace(newPosition);

                translateX +=  newPosition.getX() - oldPosition.getX();
                translateY += newPosition.getY() - oldPosition.getY();

                sbVertical.setVisibleAmount((int) (20/scaling));
                sbHorizontal.setVisibleAmount((int) (20/scaling));

                setupTransformation();

                repaint();
            }
        }
    }

    public void transformToUserSpace(Point2D deviceSpace) {
        try {
            transformation.inverseTransform(deviceSpace, deviceSpace);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void setupTransformation() {
        transformation.setToIdentity();
        transformation.scale(scaling, scaling);
        transformation.translate(translateX, translateY);
    }

    public double transformLineToUserSpaceX(double deviceSpace) {
        return deviceSpace * transformation.getScaleX()
                + transformation.getTranslateX();
    }

    public double transformLineToUserSpaceY(double deviceSpace) {
        return deviceSpace * transformation.getScaleY()
                + transformation.getTranslateY();
    }

    public void updatePerformed(UpdateEvent e) {
        repaint();
    }

    public class Framework extends JPanel {
        public Framework() {
            setBackground(Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.transform(transformation);
            Iterator<DiagramElement> it = diagram.getDiagramModel().getDeviceIterator();
            while (it.hasNext()) {
                DiagramElement d = it.next();
                ElementPainter paint = d.getPainter();
                paint.paint(g2, d);
            }
            paintSelectionHandles(g2);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_BEVEL, 1f, new float[] { (float) 3,
                    (float) 6 }, 0));
            g2.draw(selectionRectangle);
        }

        public void stop(){
            if(DiagramView.this.getStateManager().getCurrentState().getThread() != null)
                DiagramView.this.getStateManager().getCurrentState().getThread().setScroll(false);;
        }

        public void start(){

        }
    }

    private void paintSelectionHandles(Graphics2D g2) {

        Iterator<DiagramElement> it = diagram.getSelectionModel()
                .getSelectionListIterator();
        while (it.hasNext()) {
            DiagramElement element = it.next();
            if (element instanceof DiagramDevice) {
                DiagramDevice device = (DiagramDevice) element;
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_BEVEL, 1f, new float[]{3, 6}, 0 ));
                g2.setPaint(Color.BLACK);
                g2.drawRect((int)device.getPosition().getX(), (int)device.getPosition().getY(),
                        (int)device.getSize().getWidth(), (int)device.getSize().getHeight());
                for (Handle e : Handle.values()) {
                    paintSelectionHandle(g2,getHandlePoint(device.getPosition(), device.getSize(), e, device));
                }
            } else {
                LinkElement link = (LinkElement) element;
                Point2D bp = link.getOutput().getPosition();
                g2.setPaint(Color.BLACK);
                g2.setStroke(new BasicStroke((float) 2, BasicStroke.CAP_SQUARE,
                        BasicStroke.JOIN_BEVEL));
                g2.drawRect((int) bp.getX() - handleSize / 2, (int) bp.getY()
                        - handleSize / 2, handleSize, handleSize);
                Iterator<Point2D> itp = link.getPointsIterator();
                while (itp.hasNext()) {
                    bp = (Point2D) itp.next();
                    g2.drawRect((int) bp.getX() - handleSize / 2,
                            (int) bp.getY() - handleSize / 2, handleSize,
                            handleSize);
                }
                bp = link.getInput().getPosition();
                g2.drawRect((int) bp.getX() - handleSize / 2, (int) bp.getY()
                        - handleSize / 2, handleSize, handleSize);
            }
        }
    }

    private void paintSelectionHandle(Graphics2D g2, Point2D position) {
        double size = handleSize;
        g2.fill(new Rectangle2D.Double(position.getX() - size / 2, position
                .getY() - size / 2, size, size));
    }

    private Point2D getHandlePoint(Point2D topLeft, Dimension2D size,Handle handlePosition, DiagramDevice device) {
        double x=0, y=0;
        double razlika=(device.getSize().getWidth()-device.getSize().getHeight())/2;
        if(handlePosition == Handle.NorthWest || handlePosition == Handle.NorthEast){
            y = topLeft.getY();
        }
        if(handlePosition == Handle.SouthWest || handlePosition == Handle.SouthEast){
            y = topLeft.getY() + size.getHeight();
        }
        if(handlePosition == Handle.NorthWest || handlePosition == Handle.SouthWest){
            x = topLeft.getX();
        }
        if(handlePosition == Handle.NorthEast || handlePosition == Handle.SouthEast){
            x = topLeft.getX() + size.getWidth();
        }
        return new Point2D.Double(x,y);
    }

    public void setMouseCursor(Point2D point) {
        Handle handle = getDeviceAndHandleForPoint(point);
        if (handle != null) {
            Cursor cursor = null;
            switch (handle) {
                case SouthEast:
                    cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
                    break;
                case NorthWest:
                    cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                    break;
                case SouthWest:
                    cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
                    break;
                case NorthEast:
                    cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                    break;
            }
            framework.setCursor(cursor);
        } else
            framework.setCursor(Cursor.getDefaultCursor());
    }

    public Handle getDeviceAndHandleForPoint(Point2D point) {
        DiagramElement element;
        Iterator<DiagramElement> it = diagram.getSelectionModel()
                .getSelectionListIterator();
        while (it.hasNext()) {
            element = it.next();
            if(getHandleForPoint(element, point) == null) continue;
            return getHandleForPoint(element, point);
        }
        return null;
    }

    public DiagramDevice getDeviceForHandlePoint(Point2D point) {
        DiagramElement element;
        DiagramDevice device;
        Iterator<DiagramElement> it = diagram.getSelectionModel()
                .getSelectionListIterator();
        while (it.hasNext()) {
            element = it.next();
            if(element instanceof DiagramDevice){
                device = (DiagramDevice)element;
                if(getHandleForPoint(element, point)!=null)
                    return device;
            }
        }
        return null;
    }

    private Handle getHandleForPoint(DiagramElement element, Point2D point) {
        for (Handle h : Handle.values()) {
            if (isPointInHandle(element, point, h)) {
                return h;
            }
        }
        return null;
    }

    private boolean isPointInHandle(DiagramElement element, Point2D point,
                                    Handle handle) {
        if (element instanceof DiagramDevice) {
            DiagramDevice device = (DiagramDevice) element;
            Point2D handleCenter = getHandlePoint(device.getPosition(),device.getSize(), handle, device);
            return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize ) && (Math
                    .abs(point.getY() - handleCenter.getY()) <= (double) handleSize));
        } else
            return false;
    }

    public Point2D getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Point2D lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Rectangle2D getSelectionRectangle() {
        return selectionRectangle;
    }

    public void setSelectionRectangle(Rectangle2D selectionRectangle) {
        this.selectionRectangle = selectionRectangle;
    }

    public TreePath getPath(DiagramElement de) {
        DefaultTreeModel dtm = Engine.getInstance().getWorkspaceModel();
        TreePath res = new TreePath(dtm.getRoot());
        Workspace root = (Workspace) dtm.getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            if (((Project) root.getChildAt(i)).getIndex(diagram) != -1)
                res = res.pathByAddingChild(((Project) root.getChildAt(i)));
        }
        res = res.pathByAddingChild(diagram);
        for (int i = 0; i < diagram.getChildCount(); i++) {
            TreeNode node = diagram.getChildAt(i);
            for (int j = 0; j < node.getChildCount(); j++) {
                if (node.getChildAt(j) == de) {
                    res = res.pathByAddingChild(node);
                    res = res.pathByAddingChild(de);
                    break;
                }
            }
        }
        return res;
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getAdjustable().getOrientation()==Adjustable.HORIZONTAL){
            if(hScrollValue<e.getValue()){
                translateX += ((e.getValue()-hScrollValue)*(-translateFactor))/transformation.getScaleX();
            }
            else{
                translateX +=((e.getValue()-hScrollValue)*(-translateFactor))/transformation.getScaleX();
            }
            hScrollValue=e.getValue();
        }
        else{
            if(vScrollValue<e.getValue()){
                translateY += ((e.getValue()-vScrollValue)*(-translateFactor))/transformation.getScaleX();
            }
            else{
                translateY += ((e.getValue()-vScrollValue)*(-translateFactor))/transformation.getScaleX();
            }
            vScrollValue=e.getValue();
        }
        setupTransformation();
        repaint();
    }

    public void zoomIn() {
        double newScaling = scaling;
        newScaling *= scalingFactor;
        if (newScaling < 0.2) {
            newScaling = 0.2;
        } else if (newScaling > 5) {
            newScaling = 5;
        }
        Point2D oldPosition = new Point2D.Double(getWidth() / 2, getHeight() / 2);
        transformToUserSpace(oldPosition);
        scaling = newScaling;
        setupTransformation();
        Point2D newPosition = new Point2D.Double(getWidth() / 2, getHeight() / 2);
        transformToUserSpace(newPosition);
        translateX += newPosition.getX() - oldPosition.getX();
        translateY += newPosition.getY() - oldPosition.getY();
        sbVertical.setVisibleAmount((int) (20 / scaling));
        sbHorizontal.setVisibleAmount((int) (20 / scaling));
        setupTransformation();
        repaint();
    }

    public void zoomOut() {
        double newScaling = scaling;
        newScaling /= scalingFactor;
        if (newScaling < 0.2) {
            newScaling = 0.2;
        } else if (newScaling > 5) {
            newScaling = 5;
        }
        Point2D oldPosition = new Point2D.Double(getWidth() / 2,
                getHeight() / 2);
        transformToUserSpace(oldPosition);
        scaling = newScaling;
        setupTransformation();
        Point2D newPosition = new Point2D.Double(getWidth() / 2,
                getHeight() / 2);
        transformToUserSpace(newPosition);
        translateX += newPosition.getX() - oldPosition.getX();
        translateY += newPosition.getY() - oldPosition.getY();
        sbVertical.setVisibleAmount((int) (20 / scaling));
        sbHorizontal.setVisibleAmount((int) (20 / scaling));
        setupTransformation();
    }

    public void bestFitZoom() {
        int margine = 10;
        double minX = 1000000000, minY = 1000000000, maxX = 0, maxY = 0;
        Iterator<DiagramElement> it = getDiagram().getDiagramModel()
                .getDeviceIterator();
        if (!it.hasNext())
            return;
        while (it.hasNext()) {
            DiagramElement element = it.next();
            if (element instanceof DiagramDevice) {
                DiagramDevice device = (DiagramDevice) element;
                if (device.getPosition().getX() < minX)
                    minX = device.getPosition().getX();
                if (device.getPosition().getY() < minY)
                    minY = device.getPosition().getY();
                if (device.getPosition().getX() + device.getSize().width > maxX)
                    maxX = device.getPosition().getX() + device.getSize().width;
                if (device.getPosition().getY() + device.getSize().height > maxY)
                    maxY = device.getPosition().getY()
                            + device.getSize().height;
            } else if (element instanceof LinkElement) {
                LinkElement link = (LinkElement) element;
                if (link.getMaxX() > maxX)
                    maxX = link.getMaxX();
                if (link.getMaxY() > maxY)
                    maxY = link.getMaxY();
                if (link.getMinX() < minX)
                    minX = link.getMinX();
                if (link.getMinY() < minY)
                    minY = link.getMinY();
            }
        }
        minX -= margine;
        minY -= margine;
        maxX += margine;
        maxY += margine;
        areaZoom(minX, maxX, minY, maxY);
    }

    public void areaZoom(double minX, double maxX, double minY, double maxY) {
        double maxXX = transformLineToUserSpaceX(maxX);
        double minXX = transformLineToUserSpaceX(minX);
        double maxYY = transformLineToUserSpaceY(maxY);
        double minYY = transformLineToUserSpaceY(minY);
        double regionWidth = maxXX - minXX;
        double regionHeight = maxYY - minYY;
        int panelWidth = this.framework.getWidth();
        int panelHeight = this.framework.getHeight();
        double newScaling = this.transformation.getScaleX();
        if ((regionWidth != 0.0) && (regionHeight != 0.0)) {
            double scaleX = panelWidth / regionWidth;
            double scaleY = panelHeight / regionHeight;
            if (scaleX < scaleY)
                newScaling *= scaleX;
            else
                newScaling *= scaleY;
            if (newScaling < 0.2)
                newScaling = 0.2;
            if (newScaling > 5.0)
                newScaling = 5.0;
            this.transformation.setToScale(newScaling, newScaling);
        }
        maxXX = transformLineToUserSpaceX(maxX);
        minXX = transformLineToUserSpaceX(minX);
        maxYY = transformLineToUserSpaceY(maxY);
        minYY = transformLineToUserSpaceY(minY);
        regionWidth = maxXX - minXX;
        regionHeight = maxYY - minYY;
        transformation.translate(
                (-minXX + (panelWidth - regionWidth) / 2.0)
                        / transformation.getScaleX(),
                (-minYY + (panelHeight - regionHeight) / 2.0)
                        / transformation.getScaleY());
        scaling = newScaling;
        translateX = ((-minXX + (panelWidth - regionWidth) / 2.0) / transformation
                .getScaleX());
        translateY = ((-minYY + (panelHeight - regionHeight) / 2.0) / transformation
                .getScaleY());
        sbVertical.setVisibleAmount((int) (20 / scaling));
        sbHorizontal.setVisibleAmount((int) (20 / scaling));
        repaint();
    }

    public void autoScroll(Direction direction) {

        DiagramView diagramView = this;
        if ((diagramView.getStateManager().getCurrentState() instanceof MoveState)) {
            MoveState moveState = (MoveState) diagramView.getStateManager().getCurrentState();
            moveState.mouseDragged(moveState.getE());
        }
        switch (direction) {
            case Left: {
                int valueScroll = sbHorizontal.getValue();
                if (valueScroll >= sbHorizontal.getMinimum()) {
                    sbHorizontal.setValue(sbHorizontal.getValue() - 2);
                    valueScroll = sbHorizontal.getValue();
                    hScrollValue = valueScroll;
                }
                break;
            }
            case Up: {
                int valueScroll = sbVertical.getValue();
                if (valueScroll <= sbVertical.getMaximum()) {
                    sbVertical.setValue(sbVertical.getValue() + 2);
                    valueScroll = sbVertical.getValue();
                    vScrollValue = valueScroll;
                }
                break;
            }
            case Right: {
                int valueScroll = sbHorizontal.getValue();
                if (valueScroll <= sbHorizontal.getMaximum()) {
                    sbHorizontal.setValue(sbHorizontal.getValue() + 2);
                    valueScroll = sbHorizontal.getValue();
                    hScrollValue = valueScroll;
                }
                break;
            }
            case Down: {
                int valueScroll = sbVertical.getValue();
                if (valueScroll >= sbVertical.getMinimum()) {
                    sbVertical.setValue(sbVertical.getValue() - 2);
                    valueScroll = sbVertical.getValue();
                    vScrollValue = valueScroll;
                }
                break;
            }
        }
    }
}
