package actions;

import commands.RotateCommand;
import engine.Engine;
import resources.Loader;
import view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 06-Jun-15.
 */
public class RotateRightAction extends AbstractAction {
    public RotateRightAction(){
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("rotate_right"));
        putValue(NAME, "Rotate right");
        putValue(SHORT_DESCRIPTION, "Rotate right");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = (DiagramView) Engine.getInstance().getDesktop().getSelectedFrame();
        if(diagramView != null){
            if(diagramView.getDiagram().getSelectionModel().getSelectionListSize() == 1)
                diagramView.getCommandManager().addCommand(new RotateCommand(diagramView,
                        RotateCommand.Direction.RIGHT));
        }
    }
}
