package actions;

import elements.DiagramElement;
import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Marko on 25-May-15.
 */
public class SearchAction extends AbstractAction {
    public SearchAction() {
        putValue(ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("search"));
        putValue(NAME, "Search for elements");
        putValue(SHORT_DESCRIPTION, "Search for elements");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView view = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if (view == null) return;
        String response = JOptionPane.showInputDialog(null,
                "Search for elements. Use wildcard (*).", "Enter your query",
                JOptionPane.QUESTION_MESSAGE);
        String regex = "";
        for (int i = 0; i < response.length(); i++) {
            if (response.charAt(i) == '*')
                regex += "\\p{ASCII}";
            regex += response.charAt(i);
        }
        ArrayList<DiagramElement> res = new ArrayList<DiagramElement>();
        for (int i = 0; i < view.getDiagram().getDiagramModel().getDiagramElements().size(); i++) {
            if (view.getDiagram().getDiagramModel().getDiagramElements().get(i).getName()
                    .matches(regex)) {
                res.add(view.getDiagram().getDiagramModel().getDiagramElements().get(i));
            }
        }
        view.getDiagram().getSelectionModel().removeAllFromSelectionList();
        if(res.size() == 0)
            JOptionPane.showMessageDialog(null,"\tZero elements found.");
        else
            view.getDiagram().getSelectionModel().addToSelectionList(res);
        view.getDiagram().getSelectionModel().updateJTree();
    }
}
