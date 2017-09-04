package utils;

import engine.Engine;
import model.Diagram;
import model.Project;
import view.DiagramView;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marko on 01-Apr-15.
 *
 * Singleton class helper for some stuff.
 */
public class Utils {
    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public DiagramView getDiagramViewWithDiagram(Diagram diagram) {
        JInternalFrame[] frames = Engine.getInstance().getDesktop().getAllFrames();
        for (JInternalFrame frame : frames)
            if (((DiagramView)frame).getDiagram() == diagram) return (DiagramView)frame;
        return null;
    }

    public ArrayList<DiagramView> getVisibleDiagramViews() {
        ArrayList<DiagramView> ret = new ArrayList<>();
        JInternalFrame[] frames = Engine.getInstance().getDesktop().getAllFrames();
        for (JInternalFrame frame : frames)
            if (((DiagramView)frame).isVisible()) ret.add((DiagramView)frame);
        return ret;
    }

    public Diagram getSelectedDiagram() {
        Object ret = Engine.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
        if (ret instanceof Diagram) {
            return (Diagram) ret;
        } else {
            return null;
        }
    }

    public Project getSelectedProject() {
        Object ret = Engine.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
        if (ret instanceof Project) {
            return (Project) ret;
        } else {
            return null;
        }
    }

    public ArrayList<DiagramView> getCreationSortedVisibleDiagramViews() {
        JInternalFrame[] array = ((Engine.getInstance().getDesktop()).getAllFrames());
        Arrays.sort(array);
        ArrayList<DiagramView> list = new ArrayList<>();
        for (JInternalFrame anArray : array)
            if (anArray.isVisible()) list.add((DiagramView) anArray);
        return list;
    }
}
