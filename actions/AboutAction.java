package actions;

import gui.Author;
import resources.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Marko on 08-Apr-15.
 */
public class AboutAction extends AbstractAction {
    private Author author;

    public AboutAction() {
        putValue(SMALL_ICON, Loader.getInstance().loadIcon("author"));
        putValue(NAME, "About author");
        putValue(SHORT_DESCRIPTION, "About author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (author != null) {
            author.setVisible(true);
            return;
        }
        author = new Author();
    }
}
